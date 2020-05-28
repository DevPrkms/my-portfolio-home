var idReg = RegExp(/^[A-Za-z0-9_\-]{5,20}$/); // userId 정규식
var pwReg = RegExp(
    /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/
); // userPassword 정규식
var nmReg = RegExp(/^[0-9a-zA-Z가-힣]{2,10}$/); // userName 정규식
var emReg = RegExp(/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/); // userEmail 정규식
var pnReg = RegExp(/^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/); // userPhone 정규식


var nidx = 0;
var pidx = 0;

var nridx = 0;
var pridx = 0;
var pridxc = 0;
var mridx = 0;
var eridx = 0;
var hridx = 0;

$("#userId").keyup(function () {
    var userId = $("#userId").val();
    if (!idReg.test(userId)) {
        $("#userId").css("background-color", "#F6CECE");
        $("#idm").show();
        $("#idm").html("5~20자 영문자 또는 숫자와 일부 특수문자만 사용가능합니다.");
        nidx = 0;
    } else {
        $("#userId").css("background-color", "#E1F5A9");
        $("#idm").hide();
        nidx = 1;
    }
    console.log("userId : " + userId);
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
    var userId = $("#userId").val();
    var userPassword = $("#userPassword").val();

    if (nidx == 0) {
        alert("계정이 올바르지 않습니다.");
        $("#userId").focus();
    } else if (nidx == 1 && pidx == 0) {
        alert("비밀번호가 올바르지 않습니다.");
        $("#userPassword").focus();
    } else if (nidx == 1 && pidx == 1) {
        $("#loginform").submit();
    }

    console.log("userId : " + userId + ", userPassword : " + userPassword);
});


$("#userId2").keyup(function () {
    var userId = $("#userId2").val();
    if (!idReg.test(userId)) {
        $("#userId2").css("background-color", "#F6CECE");
        $("#idm2").show();
        $("#idm2").html("5~20자 영문자 또는 숫자와 일부 특수문자만 사용가능합니다.");
    } else {
        $.ajax({
            async: true,
            type: 'POST',
            data: {'userId': userId},
            url: "/idCheck.do",
            success: function (data) {
                if (data > 0) {
                    $("#idm2").html("이미 사용중인 아이디입니다.");
                } else {
                    $("#userId2").css("background-color", "#E1F5A9");
                    $("#idm2").hide();
                    nridx = 1;
                }
            }
            ,
            error: function (error) {
                alert("error : " + error);
            }
        });
    }
});

$("#userPassword2").keyup(function () {
    var userPassword = $("#userPassword2").val();
    if (!pwReg.test(userPassword)) {
        $("#userPassword2").css("background-color", "#F6CECE");
        $("#pwm2").show();
        $("#pwm2").html("특수문자와 대문자, 소문자, 숫자를 모두 사용한 8~16자만 가능합니다.");
        pridx = 0;
    } else {
        $("#userPassword2").css("background-color", "#E1F5A9");
        $("#pwm2").hide();
        pridx = 1;
    }
});

$("#userPassword-c").keyup(function () {
    var userPassword = $("#userPassword2").val();
    var userPasswordC = $("#userPassword-c").val();
    if (userPassword != userPasswordC) {
        $("#userPassword-c").css("background-color", "#F6CECE");
        $("#pwm2-c").show();
        $("#pwm2-c").html("비밀번호가 서로 일치하지 않습니다.");
        pridxc = 0;
    } else {
        $("#userPassword-c").css("background-color", "#E1F5A9");
        $("#pwm2-c").hide();
        pridxc = 1;
    }
});

$("#userName").keyup(function () {
    var userName = $("#userName").val();
    if (!nmReg.test(userName)) {
        $("#userName").css("background-color", "#F6CECE");
        $("#nam").show();
        $("#nam").html("한글 5자, 영문 10자, 숫자만 가능합니다.");
        mridx = 0;
    } else {
        $("#userName").css("background-color", "#E1F5A9");
        $("#nam").hide();
        mridx = 1;
    }
});

$("#userEmail").keyup(function () {
    var userEmail = $("#userEmail").val();
    if(!emReg.test(userEmail)){
        $("#userEmail").css("background-color", "#F6CECE");
        $("#emm").show();
        $("#emm").html("이메일 형식에 맞게 입력해주세요.");
        eridx = 0;
    } else {
        $("#emm").hide();
        $("#userEmail").css("background-color", "#FFFFFF");
    }
});

$("#userEmail").focusout(function(){
    var userEmail = $("#userEmail").val();
    $.ajax({
        async: true,
        type: 'POST',
        data: {'userEmail': userEmail},
        url: "/emCheck.do",
        success: function (data) {
            if (data > 0) {
                $("#emm").show();
                $("#emm").html("이미 사용중인 이메일입니다.");
            } else {
                $("#userEmail").css("background-color", "#E1F5A9");
                $("#emm").hide();
                eridx = 1;
            }
        }
        ,
        error: function (error) {
            alert("error : " + error);
        }
    });
})

$("#userPhone").keyup(function(){
   var userPhone = $("#userPhone").val();
   if(!pnReg.test(userPhone)){
       $("#userPhone").css("background-color", "#F6CECE");
       $("#phm").show();
       $("#phm").html("000-0000-0000 형식으로 입력해주세요.");
       hridx = 0;
   } else {
       $("#userPhone").css("background-color", "#E1F5A9");
       $("#phm").hide();
       hridx = 1;
   }
});

$("#registerbtn").click(function(){
   var idx = nridx + pridx + pridxc + mridx + eridx + hridx;

   if(idx == 6) {
       $("#registerform").submit();
   } else {
       alert("모든 항목을 올바르게 입력해주세요.");
       return null;
   }
});