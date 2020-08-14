// Get the modal
let modal = document.getElementById("playerModal");

// Get the <span> element that closes the modal
let span = document.getElementsByClassName("close")[0];

let modalTitle = document.getElementById("modalTitle");
let modalAge = document.getElementById("modalAge");
let modalGender = document.getElementById("modalGender");
let modalPoints = document.getElementById("modalPoints");
let modalPhoto = document.getElementById("modalPhoto");

function showPlayerModal(id){
   console.log("modal should open");
}
function showModal(fullName, points, age, gender, photo) {
   modalTitle.textContent = fullName;
   modalAge.textContent = age;
   modalGender.textContent = gender;
   modalPoints.textContent = points;
   if(photo == null || photo === ''){
      if(gender === 'MALE'){
         modalPhoto.setAttribute('src', "/img/silhouette-male.png")
      }
      if(gender === 'FEMALE'){
         modalPhoto.setAttribute('src', "/img/silhouette-female.png")
      }
   } else {
      modalPhoto.setAttribute('src', photo);
   }

   modal.style.display = "block";
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