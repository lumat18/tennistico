// Get the modal
let modal = document.getElementById("playerModal");

// Get the <span> element that closes the modal
let span = document.getElementsByClassName("close")[0];

let modalTitle = document.getElementById("modalTitle");
let modalAge = document.getElementById("modalAge");
let modalGender = document.getElementById("modalGender");
let modalPoints = document.getElementById("modalPoints");
let modalPhoto = document.getElementById("modalPhoto");

function showModal(id) {
   fetch('/getPlayerDto?id=' + id)
       .then(response => response.json())
       .then(function (json) {
          let {age, fullName, gender, photoUrl, rankingPoints} = JSON.parse(JSON.stringify(json));
          modalTitle.textContent = fullName;
          modalAge.textContent = age;
          modalGender.textContent = gender;
          modalPoints.textContent = rankingPoints;
          if(photoUrl == null || photoUrl === ''){
             if(gender === 'MALE'){
                modalPhoto.setAttribute('src', "/img/silhouette-male.png")
             }
             if(gender === 'FEMALE'){
                modalPhoto.setAttribute('src', "/img/silhouette-female.png")
             }
          } else {
             modalPhoto.setAttribute('src', photoUrl);
          }
       })

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