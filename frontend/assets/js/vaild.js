var idReg = RegExp(/^[A-Za-z0-9_\-]{5,20}$/); // userName 정규식
var pwReg = RegExp(
  /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/
); // userPassword 정규식
var nidx = 0;
var pidx = 0;

$("#userName").keyup(function () {
  var userName = $("#userName").val();
  if (!idReg.test(userName)) {
    $("#userName").css("background-color", "#F6CECE");
    $("#userName").tooltip();
  } else {
    $("#userName").css("background-color", "#E1F5A9");
    nidx = 1;
  }
  console.log("userName : " + userName);
});

$("#userPassword").keyup(function () {
  var userPassword = $("#userPassword").val();
  if (!pwReg.test(userPassword)) {
    $("#userPassword").css("background-color", "#F6CECE");
  } else {
    $("#userPassword").css("background-color", "#E1F5A9");
    pidx = 1;
  }
});

// 로그인 버튼 눌렀을 시 동작
$("#loginbtn").click(function () {
  var userName = $("#userName").val();
  var userPassword = $("#userPassword").val();

  if (nidx == "1") {
    if (pidx == "0") {
      alert("비밀번호 입력좀");
      $("#userPassword").focus();
    } else if (pidx == "1") {
      alert("OK");
    }
  } else if (pidx == "1") {
    if (nidx == "0") {
      alert("아이디 입력좀");
      $("#userName").focus();
    } else if (nidx == "1") {
      alert("OK");
    }
  }

  console.log("userName : " + userName + ", userPassword : " + userPassword);
});
