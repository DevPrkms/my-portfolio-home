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
        $("#idm").show();
        $("#idm").html("5~20자 영문자 또는 숫자와 일부 특수문자만 사용가능합니다.");
        nidx = 0;
    } else {
        $("#userName").css("background-color", "#E1F5A9");
        $("#idm").hide();
        nidx = 1;
    }
    console.log("userName : " + userName);
});

$("#userPassword").keyup(function () {
    var userPassword = $("#userPassword").val();
    if (!pwReg.test(userPassword)) {
        $("#userPassword").css("background-color", "#F6CECE");
        $("#pwm").show();
        $("#pwm").html("특수문자와 대문자, 소문자, 숫자를 모두 사용한 8~16자만 가능합니다.");
        pidx = 0;
    } else {
        $("#userPassword").css("background-color", "#E1F5A9");
        $("#pwm").hide();
        pidx = 1;
    }
});

// 로그인 버튼 눌렀을 시 동작
$("#loginbtn").click(function () {
    var userName = $("#userName").val();
    var userPassword = $("#userPassword").val();

    if (nidx == 0) {
        alert("계정이 올바르지 않습니다.");
        $("#userName").focus();
    } else if (nidx == 1 && pidx == 0) {
        alert("비밀번호가 올바르지 않습니다.");
        $("#userPassword").focus();
    } else if (nidx == 1 && pidx == 1) {
        $.ajax({
            async: true,
            type: 'POST',
            data: {'userName': userName, 'userPassword': userPassword},
            url: "/userLogin.do",
            success: function (data) {
                if (data == null) {
                    alert("입력하신 정보가 서버에 없습니다.");
                    $("#userName").focus();
                } else {
                    var userNm = sessionuserName;
                    alert(userNm + "님 환영합니다.");
                    location.href="/main.do";
                }
            }
            ,
            error: function (error) {
                alert("error : " + error);
            }
        });
    }

    console.log("userName : " + userName + ", userPassword : " + userPassword);
});
