$(document).ready(function () {
   $("#setHost1").prop("required", true).prop("placeholder", "Input score");
   $("#setGuest1").prop("required", true).prop("placeholder", "Input score");

   for(let idx=2; idx<=5; idx++){
      $('#setHost'+idx).prop("disabled", true);
      $('#setGuest'+idx).prop("disabled", true);
   }

   $(document).change(function () {
      if (checkIfAllRequiredFilled()) {
         $(document).ready(function () {
            $("#btnAddSet").prop("disabled", false);
         })
      } else {
         $(document).ready(function () {
            $("#btnAddSet").prop("disabled", true);
         })
      }
   })

   function checkIfAllRequiredFilled() {
      let required = $('.gruzini-input-check').filter('[required]:visible');
      let isValid = true;
      required.each(function () {
         if (!$(this).val()) {
            isValid = false;
         }
      });
      if (!$("#setHost5").prop("disabled")) {
         isValid = false;
      }
      return isValid;
   }

   //function for adding sets with button
   $("#btnAddSet").click(function () {
      if ($("#setHost2").prop("disabled")) {
         $("#setHost1").prop("readonly", true);
         $("#setGuest1").prop("readonly", true);
         $("#setHost2").prop("disabled", false).prop("required", true).prop("placeholder", "Input score");
         $("#setGuest2").prop("disabled", false).prop("required", true).prop("placeholder", "Input score");
         $("#btnRemoveSet").prop("disabled", false);
      } else if ($("#setHost3").prop("disabled")) {
         $("#setHost2").prop("readonly", true);
         $("#setGuest2").prop("readonly", true);
         $("#setHost3").prop("disabled", false).prop("required", true).prop("placeholder", "Input score");
         $("#setGuest3").prop("disabled", false).prop("required", true).prop("placeholder", "Input score");
      } else if ($("#setHost4").prop("disabled")) {
         $("#setHost3").prop("readonly", true);
         $("#setGuest3").prop("readonly", true);
         $("#setHost4").prop("disabled", false).prop("required", true).prop("placeholder", "Input score");
         $("#setGuest4").prop("disabled", false).prop("required", true).prop("placeholder", "Input score");
      } else {
         $("#setHost4").prop("readonly", true);
         $("#setGuest4").prop("readonly", true);
         $("#setHost5").prop("disabled", false).prop("required", true).prop("placeholder", "Input score");
         $("#setGuest5").prop("disabled", false).prop("required", true).prop("placeholder", "Input score");
         $("#btnAddSet").prop("disabled", true);
      }
   });

   //function for removing sets with button
   $("#btnRemoveSet").click(function () {
      if ($("#setHost3").prop("disabled")) {
         $("#setHost1").prop("readonly", false);
         $("#setGuest1").prop("readonly", false);
         $("#setHost2").val('').prop("disabled", true).prop("required", false).prop("placeholder", "");
         $("#setGuest2").val('').prop("disabled", true).prop("required", false).prop("placeholder", "");
         $("#btnRemoveSet").prop("disabled", true);
      } else if ($("#setHost4").prop("disabled")) {
         $("#setHost2").prop("readonly", false);
         $("#setGuest2").prop("readonly", false);
         $("#setHost3").val('').prop("disabled", true).prop("required", false).prop("placeholder", "");
         $("#setGuest3").val('').prop("disabled", true).prop("required", false).prop("placeholder", "");
      } else if ($("#setHost5").prop("disabled")) {
         $("#setHost3").prop("readonly", false);
         $("#setGuest3").prop("readonly", false);
         $("#setHost4").val('').prop("disabled", true).prop("required", false).prop("placeholder", "");
         $("#setGuest4").val('').prop("disabled", true).prop("required", false).prop("placeholder", "");
      } else {
         $("#setHost4").prop("readonly", false);
         $("#setGuest4").prop("readonly", false);
         $("#setHost5").val('').prop("disabled", true).prop("required", false).prop("placeholder", "");
         $("#setGuest5").val('').prop("disabled", true).prop("required", false).prop("placeholder", "");
         $("#btnAddSet").prop("disabled", false);
      }
   })
});