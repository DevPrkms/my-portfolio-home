<%@ page import="poly.util.CmmUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String userNm = CmmUtil.nvl((String) session.getAttribute("userName"));
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>My Portfolio</title>

    <!-- jQuery load -->
    <script
            src="https://code.jquery.com/jquery-3.5.0.js"
            integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
            crossorigin="anonymous"
    ></script>

    <!-- font-awesome -->
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"
    />

    <!-- bootstrap CSS load -->
    <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
            crossorigin="anonymous"
    />

    <!-- 타이핑 js -->
    <script src="/assets/js/type.js?v=1.1"></script>

    <!-- page CSS -->
    <link rel="stylesheet" href="/assets/css/home.css?v=1.3"/>

    <script>
        var sessionuserName = '<%=userNm%>';
    </script>
</head>
<body onload="typejs()">
<div class="wrapper">
    <!-- Main 타이핑 부분 -->
    <p class="typing-txt">My Portfolio</p>
    <p class="typing"></p>

    <!-- Modal login -->
    <div id="btn-wrapper">

        <button
                id="jbtn"
                class="btn btn-outline-light btn-round btn-lg"
                type="button"
                data-toggle="modal"
                data-target="#loginModal"
        >
            로그인
        </button>

        <button
                id="rbtn"
                class="btn btn-outline-light btn-round btn-lg"
                type="button"
                data-toggle="modal"
                data-target="#registerModal"
        >
            회원가입
        </button>
    </div>
</div>

<!-- 로그인 modal -->

<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="LoginModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header border-bottom-0">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-title text-center">
                    <h4 class="logintitle">로그인</h4>
                </div>
                <div class="d-flex flex-column text-center">
                    <form id="loginform" action="/userLogin.do" method="post">
                        <div class="form-group">
                            <input type="text" name="userId" class="form-control" id="userId"
                                   placeholder="계정을 입력해주세요.">
                            <span id="idm" class="vm"></span>
                        </div>
                        <div class="form-group">
                            <input type="password" name="userPassword" class="form-control" id="userPassword"
                                   placeholder="비밀번호를 입력해주세요.">
                            <span id="pwm" class="vm" style="display: none"></span>
                        </div>
                        <button type="button" class="btn btn-info btn-block btn-round" id="loginbtn">로그인</button>
                    </form>
                    <div class="d-flex justify-content-center social-buttons">
                        </di>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- 회원가입 modal -->

<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="RegisterModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header border-bottom-0">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-title text-center">
                    <h4 class="logintitle">회원가입</h4>
                </div>
                <div class="d-flex flex-column text-center">
                    <form id="registerform" action="/userReg.do" method="post">
                        <div class="form-group">
                            <input type="text" name="userId2" class="form-control" id="userId2"
                                   placeholder="계정을 입력해주세요.">
                            <span id="idm2" class="vm"></span>
                        </div>
                        <div class="form-group">
                            <input type="password" name="userPassword2" class="form-control" id="userPassword2"
                                   placeholder="비밀번호를 입력해주세요.">
                            <span id="pwm2" class="vm" style="display: none"></span>
                        </div>
                        <div class="form-group">
                            <input type="password" name="userPasswordcheck" class="form-control" id="userPassword-c"
                                   placeholder="비밀번호를 한번 더 입력해주세요.">
                            <span id="pwm2-c" class="vm" style="display: none"></span>
                        </div>
                        <div class="form-group">
                            <input type="text" name="userName" class="form-control" id="userName"
                                   placeholder="이름을 입력해주세요.">
                            <span id="nam" class="vm"></span>
                        </div>
                        <div class="form-group">
                            <input type="email" name="userEmail" class="form-control" id="userEmail"
                                   placeholder="이메일을 입력해주세요.">
                            <span id="emm" class="vm"></span>
                        </div>
                        <div class="form-group">
                            <input type="email" name="userPhone" class="form-control" id="userPhone"
                                   placeholder="휴대폰 번호를 입력해주세요.">
                            <span id="phm" class="vm"></span>
                        </div>
                        <button type="button" class="btn btn-info btn-block btn-round" id="registerbtn">회원가입</button>
                    </form>
                    <div class="d-flex justify-content-center social-buttons">
                        </di>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- vaild js -->
<script src="/assets/js/vaild.js?v=1.6"></script>

<!-- bootstrap js load -->
<script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"
></script>
<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"
></script>
</body>
</html>
