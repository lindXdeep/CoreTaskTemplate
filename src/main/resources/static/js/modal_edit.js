let modal_backgraund = document.getElementById('win-block-edit');
let modal_edit_win = document.getElementById('modal-edit');

let btn_close = document.getElementById('btn-close');

let btn_edit = document.getElementById('btn-edit');


btn_edit.onclick = () => {

  modal_backgraund.style.display = "block";
  modal_edit_win.style.display = "block";
}

btn_close.onclick = () => {
  modal_backgraund.style.display = "none";
  modal_edit_win.style.display = "none";
}

window.onclick = (event) => {
  if (event.target == modal_backgraund) {
    modal_backgraund.style.display = "none";
    modal_edit_win.style.display = "none";
  }
}


function getPropId(id) {

  modal_backgraund.style.display = "block";
  modal_edit_win.style.display = "block";

  $.ajax({
    url: "/admin/user/" + id + "/edit",
    success: function (response) {
      $('#modal-edit').html(response);
   
      modal_backgraund.style.display = "block";
      modal_edit_win.style.display = "block";
    }
  });
}

function viewUser(id) {
  alert(id);
}



// <a th:onclick="'getPropId(\'' +  ${user.getId()}  + '\');'">btn1</a>
// <a th:onclick="'javascript:viewUser(\'' + ${user.id} +'\');'">btn2</a>
