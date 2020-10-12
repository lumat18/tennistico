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
let tileLayer = new ol.layer.Tile({
  source: new ol.source.OSM(),
});

let loader = document.createElement("div");
loader.innerHTML = "<div class='lds-roller'><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div>";
loader.className = "loader-container";

let map = new ol.Map({
  interactions: interactions,
  layers: [tileLayer],
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

let afterSearch = true;
let startedSearch = true;
// courts layer
let tennisLayer;
let vectorSource;
let clusterSource;
let styleCache = {};
let mapInteractions = map.getInteractions();

function freezeMap() {
  map.getViewport().appendChild(loader);
  mapInteractions.forEach(interaction => interaction.setActive(false));
  $('.ol-control').find('button').each(function () {
    $(this).attr('disabled', 'disabled');
  });
  $('.ol-zoomslider-thumb').each(function () {
    $(this).css('display', 'none');
  });
  map.getViewport().style.opacity = 0.5;
  startedSearch = true;
}

function unfreezeMap() {
  map.getViewport().removeChild(loader);
  mapInteractions.forEach(interaction => interaction.setActive(true));
  $('.ol-control').find('button').each(function () {
    $(this).removeAttr('disabled');
  });
  $('.ol-zoomslider-thumb').each(function () {
    $(this).css('display', 'block');
  });
  map.getViewport().style.opacity = 1.0;
  afterSearch = true;
  startedSearch = false;
}

function handleErrors(response) {
  if (!response.ok) {
    unfreezeMap();
    throw Error(response.statusText);
  }
  return response;
}

function newTennisLayer(){
  info.style.display = 'none';
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
          'out center;';

      const overpassRequest = async () => {
        freezeMap();
        const overpassResponse = await fetch('https://overpass-api.de/api/interpreter', {
          method: "POST",
          body: query
        }).then(handleErrors).catch(function (error) {
          console.log(error);
          info.innerHTML = 'WARNING! Failed to load courts - Try again!';
          info.style.display = '';
        });
        const overpassJson = await overpassResponse.json()

        const geoJSON = osmtogeojson(overpassJson, {
          flatProperties: true
        });
        let features = new ol.format.GeoJSON().readFeatures(geoJSON, {
          featureProjection: map.getView().getProjection()
        });
        features.forEach(feature => {
          let featureCoordinates = feature.getGeometry().getFlatCoordinates();
          feature.setGeometry(new ol.geom.Point(featureCoordinates));
        })
        vectorSource.addFeatures(features);
        unfreezeMap();
      };
      overpassRequest();
    },
    strategy: ol.loadingstrategy.bbox,
  });

  clusterSource = new ol.source.Cluster({
    distance: 24,
    source: vectorSource,
  });

  if(tennisLayer){
    map.removeLayer(tennisLayer);
  }
  tennisLayer = new ol.layer.Vector({
    renderMode: 'image',
    source: clusterSource,
    style: function (feature) {
      let size = feature.get('features').length;
      let style = styleCache[size];
      if (!style) {
        let sizeText = size.toString();
        let ballStyle = new ol.style.Style({
              image: new ol.style.Icon({
                src: "/img/tennis_ball_icon.png",
                scale: 0.05,
                anchor: [0.5, 0.48],
              }),
            });
        let multipleStyle;
        if(sizeText > 1){
          multipleStyle = new ol.style.Style({
            image: new ol.style.Icon({
              src: "/img/map_toggle_slider.png",
              scale: 0.08,
              anchor: [0.3, 0.50]
            }),
            text: new ol.style.Text({
              text: sizeText,
              font: '14px Teko',
              fill: new ol.style.Fill({
                color: '#ffffff',
              }),
              offsetX: 18,
              offsetY: 2,
            }),
          });
        } else {
          multipleStyle = new ol.style.Style({
            image: new ol.style.Circle({
              radius: 12,
              fill: new ol.style.Fill({
                color: 'rgba(0,60,136,.7)',
              }),
            })
          });
        }
        style = [multipleStyle, ballStyle];
        styleCache[size] = style;
      }
      return style;
    },
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
  afterSearch = false;
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

//app starts searching courts after 2 seconds of inactivity on the map, to be changed if needed
map.on("rendercomplete", debounced(2000, function () {
  if(!afterSearch && !startedSearch && map.getView().getZoom() > 10) newTennisLayer();
}))

//temp functions for logging geolocation data in console
function fillFormFieldsFromOsmFeature(feature){
  let type = feature.id_.charAt(0).toUpperCase();
  let id = feature.id_.split('/')[1];
  let endpoint = 'https://nominatim.openstreetmap.org/lookup?osm_ids='+type+id+'&format=json&addressdetails=1';

  fetch(endpoint)
      .then(response => response.json())
      .then(function (json) {
        let object = JSON.parse(JSON.stringify(json));
        const {house_number, country, postcode, state, road, city} = object[0].address;
        courtHouseNumber.value = house_number !== undefined ? house_number : null;
        courtAddress.value = road !== undefined ? road : null;
        courtCity.value = city !== undefined ? city : null;
        courtState.value = state !== undefined ? state : null;
        courtCountry.value = country !== undefined ? country : null;
        courtPostcode.value = postcode !== undefined ? postcode : null;
        console.log(object);
  });

  let {email, name, phone, website} = feature.getProperties();
  courtName.value = name !== undefined ? name : null;
  courtEmail.value = email !== undefined ? email : null;
  courtWebsite.value = website !== undefined ? website : null;
  courtPhone.value = phone !== undefined ? phone : null;

  $("#buttonSubmit").prop("disabled", false);
  return feature.id_;
}

let displayFeatureInfo = function(pixel) {
  let feature = map.forEachFeatureAtPixel(pixel, function(feature) {
    return feature;
  });
  if (feature) {
    console.log(feature.get('features').length > 1 ? 'number of features: ' + feature.get('features').length : fillFormFieldsFromOsmFeature(feature.get('features')[0]));
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

let info = document.getElementById('info');

let courtName = document.getElementById("courtName");
let courtEmail = document.getElementById("courtEmail");
let courtWebsite = document.getElementById("courtWebsite");
let courtHouseNumber = document.getElementById("courtHouseNumber");
let courtAddress = document.getElementById("courtStreet");
let courtCity = document.getElementById("courtCity");
let courtState = document.getElementById("courtState");
let courtCountry = document.getElementById("courtCountry");
let courtPostcode = document.getElementById("courtPostcode");
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

function isAllRequiredFilled() {
  let required = $('.form-control').filter('[required]:visible');
  let allFilled = true;
  required.each(function () {
    if (!$(this).val()) {
      allFilled = false;
    }
  });
  return allFilled;
}

function showRequiredInputFields() {
  let required = $('.form-control').filter('[required]:visible');
  required.each(function () {
    if (!$(this).val()) {
      this.setAttribute("placeholder", "This field cannot be empty!")
    }
  });
  let error = document.getElementById('errorMessage');
  error.innerHTML = "Insufficient data. Please choose another court.";
  error.style.display = '';
}

function debounced(delay, fn) {
  let timerId;
  return function (...args) {
    if (timerId) {
      clearTimeout(timerId);
    }
    timerId = setTimeout(() => {
      fn(...args);
      timerId = null;
    }, delay);
  }
}