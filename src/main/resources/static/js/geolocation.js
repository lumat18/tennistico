let viewport = document.getElementById('map');
function getMinZoom() {
  let width = viewport.clientWidth;
  return Math.ceil(Math.LOG2E * Math.log(width / 256));
}

let view = new ol.View({
  //setting default position on Warsaw, used for when tracking isn't enabled in browser for this page
  center: ol.proj.transform([21.1159131, 52.1992539], 'EPSG:4326', 'EPSG:3857'),
  minZoom: getMinZoom(),
  zoom: 10,
});

let map = new ol.Map({
  layers: [
    new ol.layer.Tile({
      source: new ol.source.OSM(),
    }) ],
  target: 'map',
  view: view,
});

let geolocation = new ol.Geolocation({
  // enableHighAccuracy must be set to true to have the heading value.
  trackingOptions: {
    enableHighAccuracy: true,
  },
  projection: view.getProjection(),
  tracking: true,
});

// handle geolocation error.
geolocation.on('error', function (error) {
  let info = document.getElementById('info');
  info.innerHTML = error.message;
  info.style.display = '';
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

geolocation.on('change:position', function () {
  let coordinates = geolocation.getPosition();
  positionFeature.setGeometry(coordinates ? new ol.geom.Point(coordinates) : null);
  map.getView().setCenter(coordinates);
});

new ol.layer.Vector({
  map: map,
  source: new ol.source.Vector({
    features: [accuracyFeature, positionFeature],
  }),
});

let courtName = document.getElementById("courtName");
let courtAddress = document.getElementById("courtAddress");
let courtCity = document.getElementById("courtCity");
let courtPhone = document.getElementById("courtPhone");

let hitTolerance = 10;

let circleCanvas = document.getElementById('circle');
let size = 2 * hitTolerance + 2;
circleCanvas.width = size;
circleCanvas.height = size;
let ctx = circleCanvas.getContext('2d');
ctx.clearRect(0,0, size ,size);
ctx.beginPath();
ctx.arc(
    hitTolerance + 1,
    hitTolerance + 1,
    hitTolerance + 0.5,
    0,
    2*Math.PI
);
ctx.fill();
ctx.stroke();

map.on('singleclick', function (event) {
  let hit = false;
  map.forEachFeatureAtPixel(
      event.pixel,
      function () {
        hit = true;
      },
      {
        hitTolerance: hitTolerance,
      }
  );

  if(hit){
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
})