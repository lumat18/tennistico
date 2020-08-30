let attribution = new ol.control.Attribution({
  collapsible: true,
  collapsed: true,
});

let view = new ol.View({
  //setting default position on Warsaw, used for when tracking isn't enabled in browser for this page
  center: ol.proj.fromLonLat([21.1159131, 52.1992539]),
  zoom: 11,
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

function checkSize() {
  if (map.getSize()[0] < 600) {
    attribution.setCollapsed(true);
  }
}

window.addEventListener('resize', checkSize);
checkSize();

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
  button.disabled = true;
});

let accuracyFeature = new ol.Feature();
geolocation.on('change:accuracyGeometry', function () {
  accuracyFeature.setGeometry(geolocation.getAccuracyGeometry());
});

let positionFeature = new ol.Feature();
positionFeature.setStyle(
    new ol.style.Style({
      image: new ol.style.Circle({
        radius: 6,
        fill: new ol.style.Fill({
          color: '#3399CC',
        }),
        stroke: new ol.style.Stroke({
          color: '#fff',
          width: 2,
        }),
      }),
    })
);

function showCurrentLocation() {
  let coordinates = geolocation.getPosition();
  positionFeature.setGeometry(coordinates ? new ol.geom.Point(coordinates) : null);
  map.getView().setCenter(coordinates);
  map.getView().setZoom(11);
}

let button = document.createElement('button');
button.innerHTML = "<i class='material-icons' data-toggle='tooltip' title='Current Location'" +
    " style='color: white'>my_location</i>";

let handleCenterLocation = function () {
  showCurrentLocation();
};

button.addEventListener('click', handleCenterLocation, false);

let element = document.createElement('div');
element.className = 'location ol-unselectable ol-control';

element.appendChild(button);

let CenterLocation = new ol.control.Control({
  element: element,
});
map.addControl(CenterLocation);

let layer = new ol.layer.Vector({
  map: map,
  source: new ol.source.Vector({
    features: [accuracyFeature, positionFeature],
  }),
});

layer.once("change", showCurrentLocation);

// courts layer
let vectorSource = new ol.source.Vector({
  format: new ol.format.GeoJSON(),
  loader: function(extent, resolution, projection) {
    let epsg4326Extent = ol.proj.transformExtent(extent, projection, 'EPSG:4326');
    let stringExtent = epsg4326Extent[1] + ',' + epsg4326Extent[0] + ',' + epsg4326Extent[3] + ',' + epsg4326Extent[2];
    let query = '[out:json][bbox:'+ stringExtent +'];' +
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
          vectorSource.addFeatures(features);
        });
  },
  strategy: ol.loadingstrategy.bbox,
});

let vectorLayer = new ol.layer.Vector({
  renderMode: 'image',
  source: vectorSource,
})
map.addLayer(vectorLayer);

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
});
//end of courts layer

let courtName = document.getElementById("courtName");
let courtAddress = document.getElementById("courtAddress");
let courtCity = document.getElementById("courtCity");
let courtPhone = document.getElementById("courtPhone");

map.on('click', function (evt) {
  let feature = map.forEachFeatureAtPixel(evt.pixel, function (feature) {
    return feature;
  });

  //logging click coordinate for testing
  let coordinates = evt.coordinate;
  console.log(coordinates);

  if (feature) {
    courtName.value = "test";
    courtAddress.value = "test";
    courtCity.value = "test";
    courtPhone.value = "test";
  } else {
    courtName.value = "";
    courtAddress.value = "";
    courtCity.value = "";
    courtPhone.value = "";
  }
});

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