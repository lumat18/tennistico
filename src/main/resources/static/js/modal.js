// Get the modal
let modal = document.getElementById("playerModal");

// Get the <span> element that closes the modal
let span = document.getElementsByClassName("close")[0];

let modalTitle = document.getElementById("modalTitle");
let modalAge = document.getElementById("modalAge");
let modalGender = document.getElementById("modalGender");
let modalPoints = document.getElementById("modalPoints");
let modalPhoto = document.getElementById("modalPhoto");

function showPlayerModalByPlayerId(id) {
   fetchDataForPlayerModal('/getPlayerDto?id=' + id);
   modal.style.display = "block";
}

function showPlayerModalByMatchId(id){
  fetchDataForPlayerModal('/getPlayerDtoByMatchId?id=' + id);
  modal.style.display = "block";
}

function fetchDataForPlayerModal(endpoint){
  fetch(endpoint)
      .then(response => response.json())
      .then(function (json) {
        let player = JSON.parse(JSON.stringify(json));
        modalTitle.textContent = player.fullName;
        modalAge.textContent = player.age;
        modalGender.textContent = player.gender;
        modalPoints.textContent = player.rankingPoints;
        if(player.photoUrl == null || player.photoUrl === ''){
          if(player.gender === 'MALE'){
            modalPhoto.setAttribute('src', "/img/silhouette-male.png")
          }
          if(player.gender === 'FEMALE'){
            modalPhoto.setAttribute('src', "/img/silhouette-female.png")
          }
        } else {
          modalPhoto.setAttribute('src', player.photoUrl);
        }
      })
}

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
   modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
   if (event.target === modal) {
      modal.style.display = "none";
   }
}