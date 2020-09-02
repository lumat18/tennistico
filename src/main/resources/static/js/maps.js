let attribution = new ol.control.Attribution({
  collapsible: true,
  collapsed: true,
});

let view = new ol.View({
  //setting default position on Warsaw, used for when tracking isn't enabled in browser for this page
  center: ol.proj.fromLonLat([21.1159131, 52.1992539]),
  zoom: 12,
  minZoom: 2,
  maxZoom: 20,
});

let interactions = ol.interaction.defaults({
  altShiftDragRotate:false,
  pinchRotate:false,
});

let zoomSlider = new ol.control.ZoomSlider();

let map = new ol.Map({
  interactions: interactions,
  layers: [
    new ol.layer.Tile({
      source: new ol.source.OSM(),
    })],
  target: 'map',
  controls: ol.control.defaults({attribution: false}).extend([attribution, zoomSlider]),
  view: view,
});

//collapsing attribution button on window resize
function checkSize() {
  if (map.getSize()[0] < 600) {
    attribution.setCollapsed(true);
  }
}

window.addEventListener('resize', checkSize);
checkSize();
//end of collapsing attribution button

// courts layer
let tennisLayer;
let vectorSource;
let circleStyle = new ol.style.Style({
  image: new ol.style.Circle({
    radius: 6,
    fill: new ol.style.Fill({
      color: '#3399cc',
    }),
    stroke: new ol.style.Stroke({
      color: '#fff',
      width: 2,
    }),
  }),
});

function newTennisLayer(){
  vectorSource = new ol.source.Vector({
    format: new ol.format.GeoJSON(),
    loader: function(extent, resolution, projection) {
      let epsg4326Extent = ol.proj.transformExtent(extent, projection, 'EPSG:4326');
      let stringExtent = epsg4326Extent[1] + ',' + epsg4326Extent[0] + ',' + epsg4326Extent[3] + ',' + epsg4326Extent[2];
      let query = '[out:json][timeout:25][bbox:'+ stringExtent +'];' +
          '(nwr[leisure="sports_centre"][sport="tennis"][access!="private"];' +
          'nwr[leisure="pitch"][sport="tennis"][access!="private"];' +
          'nwr[leisure="stadium"][sport="tennis"][access!="private"];);' +
          '(._;>;);' +
          'out body;';
      fetch('https://overpass-api.de/api/interpreter', {
        method: "POST",
        body: query
      })
          .then(response => response.json())
          .then(json => {
            const geoJSON = osmtogeojson(json, {
              flatProperties: true
            });
            let features = new ol.format.GeoJSON().readFeatures(geoJSON, {
              featureProjection: map.getView().getProjection()
            });
            features.forEach(feature => console.log('1: ' + feature.getGeometry().getCoordinates()));
            features.forEach(feature => {
              let featureCoordinates = feature.getGeometry().getCoordinates();
              feature.setGeometry(new ol.geom.Point(featureCoordinates));
              feature.setStyle(circleStyle);
            })
            features.forEach(feature => console.log('2: ' + feature.getGeometry().getCoordinates()));
            vectorSource.addFeatures(features);
            vectorSource.getFeatures().forEach(feature => console.log('3: ' + feature.getGeometry().getCoordinates()));
          });
    },
    strategy: ol.loadingstrategy.bbox,
  });

  if(tennisLayer){
    map.removeLayer(tennisLayer);
  }
  tennisLayer = new ol.layer.Vector({
    renderMode: 'image',
    source: vectorSource,
  })
  map.addLayer(tennisLayer);
}
//end of courts layer

//clearing feature loader from vectorSource so there are no further fetch requests to overpass api when map view is changing
map.on('movestart', function () {
  if(vectorSource){
    vectorSource.setLoader(function(extent, resolution, projection){
      console.log('cleared loader');
    });
  }
  searchButton.disabled = map.getView().getZoom() < 11;
});

let geolocation = new ol.Geolocation({
  // enableHighAccuracy must be set to true to have the heading value.
  trackingOptions: {
    enableHighAccuracy: true,
  },
  projection: view.getProjection(),
  tracking: true,
});

// handle maps error.
geolocation.on('error', function (error) {
  let info = document.getElementById('info');
  info.innerHTML = 'WARNING! ' + error.message;
  info.style.display = '';
  locationButton.disabled = true;
});

function showCurrentLocation() {
  let coordinates = geolocation.getPosition();
  map.getView().setCenter(coordinates);
  map.getView().setZoom(12);
}

geolocation.once("change", function () {
  showCurrentLocation();
  newTennisLayer();
});

//adding button for centering on current location
let locationButton = document.createElement('button');
locationButton.innerHTML = "<i class='material-icons' data-toggle='tooltip' title='Current Location'" +
    " style='color: white'>my_location</i>";

locationButton.addEventListener('click', showCurrentLocation, false);

let centerLocationElement = document.createElement('div');
centerLocationElement.className = 'location ol-unselectable ol-control';

centerLocationElement.appendChild(locationButton);

let centerLocation = new ol.control.Control({
  element: centerLocationElement,
});
map.addControl(centerLocation);
//end of center on current location button

//adding button for searching for courts on current map view
let searchButton = document.createElement('button');
searchButton.innerHTML = "<i class='material-icons' data-toggle='tooltip' title='Search courts'" +
    " style='color: white'>search</i>";

searchButton.addEventListener('click', newTennisLayer, false);

let searchCourtsElement = document.createElement('div');
searchCourtsElement.className = 'search-courts ol-unselectable ol-control';

searchCourtsElement.appendChild(searchButton);

let searchCourts = new ol.control.Control({
  element: searchCourtsElement,
});
map.addControl(searchCourts);
//end of search courts button

//temp functions for logging geolocation data in console
let displayFeatureInfo = function(pixel) {
  let feature = map.forEachFeatureAtPixel(pixel, function(feature) {
    return feature;
  });
  if (feature) {
    console.log(feature.getProperties());
  } else {
    console.log('Nothing around');
  }
};

map.on('click', function(evt) {
  displayFeatureInfo(evt.pixel);
  let coordinates = evt.coordinate;
  console.log(coordinates);
});
//end of logging functions

let courtName = document.getElementById("courtName");
let courtAddress = document.getElementById("courtAddress");
let courtCity = document.getElementById("courtCity");
let courtPhone = document.getElementById("courtPhone");

map.on('pointermove', function (evt) {
  map.getTargetElement().style.cursor = map.hasFeatureAtPixel(evt.pixel)
      ? 'pointer'
      : '';
});

$('.ol-zoom-in, .ol-zoom-out').tooltip({
  placement: 'right',
  container: '#map',
});
$('.ol-rotate-reset, .ol-attribution button[title]').tooltip({
  placement: 'bottom',
  container: '#map',
});
$('.location').ready(function(){
  $('[data-toggle="tooltip"]').tooltip({
    placement: 'right',
    container: '#map',
  });
});