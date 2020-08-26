let viewport = document.getElementById('map');
function getMinZoom() {
  let width = viewport.clientWidth;
  return Math.ceil(Math.LOG2E * Math.log(width / 256));
}

let attribution = new ol.control.Attribution({
  collapsible: true,
  collapsed: true,
});

let view = new ol.View({
  //setting default position on Warsaw, used for when tracking isn't enabled in browser for this page
  center: ol.proj.fromLonLat([21.1159131, 52.1992539]),
  minZoom: getMinZoom(),
  zoom: 10,
});

let map = new ol.Map({
  layers: [
    new ol.layer.Tile({
      source: new ol.source.OSM(),
    }) ],
  target: 'map',
  controls: ol.control.defaults({attribution: false}).extend([attribution]),
  view: view,
});

function checkSize() {
  if(map.getSize()[0] < 600){
    attribution.setCollapsed(true);
  }
}

window.addEventListener('resize', checkSize);
checkSize();

let maps = new ol.Geolocation({
  // enableHighAccuracy must be set to true to have the heading value.
  trackingOptions: {
    enableHighAccuracy: true,
  },
  projection: view.getProjection(),
  tracking: true,
});

// handle maps error.
maps.on('error', function (error) {
  let info = document.getElementById('info');
  info.innerHTML = 'WARNING! ' + error.message;
  info.style.display = '';
});

let accuracyFeature = new ol.Feature();
maps.on('change:accuracyGeometry', function () {
  accuracyFeature.setGeometry(maps.getAccuracyGeometry());
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
  let coordinates = maps.getPosition();
  positionFeature.setGeometry(coordinates ? new ol.geom.Point(coordinates) : null);
  map.getView().setCenter(coordinates);
}

let layer = new ol.layer.Vector({
  map: map,
  source: new ol.source.Vector({
    features: [accuracyFeature, positionFeature],
  }),
});

layer.once("change", showCurrentLocation);

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