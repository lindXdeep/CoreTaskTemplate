
$(document).ready(function () {

  $('.table .btn-edit').on('click', function (event) {

    event.preventDefault();           // отменить действие браузера по умолчанию 

    var href = $(this).attr('href');

    jQuery.get(href, function (user, status) {
      $('.formEdit #id').val(user.id);
      $('.formEdit #firstName').val(user.firstName);
      $('.formEdit #lastName').val(user.lastName);
      $('.formEdit #age').val(user.age);
      $('.formEdit #email').val(user.email);
      $('.formEdit #password').val(user.password);
      $('.formEdit #roles').val(user.title);
    });

    $('.formEdit #exampleModal').modal();
  });
});



// let modal_backgraund = document.getElementById('win-block-edit');
// let modal_edit_win = document.getElementById('modal-edit');

// let btn_close = document.getElementById('btn-close');

// let btn_edit = document.getElementById('btn-edit');

// btn_close.onclick = () => {
//   modal_backgraund.style.display = "none";
//   modal_edit_win.style.display = "none";
// };

// window.onclick = (event) => {
//   if (event.target == modal_backgraund) {
//     modal_backgraund.style.display = "none";
//     modal_edit_win.style.display = "none";
//   }
// };


// $('.allusers .mybtn-edit').on('click', function (event) {

//   event.preventDefault();

//   alert('asdas');

// });

// function fillingModal(userId, firstName, lastName, age, email, password, userRoles) {

//   $("#modal-id").val(userId);
//   $("#modal-firstName").val(firstName);
//   $("#modal-lastName").val(lastName);
//   $("#modal-age").val(age);
//   $("#modal-email").val(email);
//   $("#modal-password").val(password);
//   $("#modal-roles").val(roles);
// };
