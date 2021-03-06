<%@ page import="poly.util.CmmUtil" %>
<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userId = CmmUtil.nvl((String) session.getAttribute("userId"));
    String userNm = CmmUtil.nvl((String) session.getAttribute("userName"));
    String userEmail = CmmUtil.nvl((String) session.getAttribute("userEmail"));
    String userPhone = CmmUtil.nvl((String) session.getAttribute("userPhone"));
    String userProfile = CmmUtil.nvl((String) session.getAttribute("userProfile"));
%>
<!DOCTYPE html>
<html lang="en" data-textdirection="ltr" class="loading">
<head>
    <!-- jQuery load -->
    <script
            src="https://code.jquery.com/jquery-3.5.0.js"
            integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
            crossorigin="anonymous"
    ></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui"
    />
    <meta
            name="description"
            content="Robust admin is super flexible, powerful, clean &amp; modern responsive bootstrap 4 admin template with unlimited possibilities."
    />
    <meta
            name="keywords"
            content="admin template, robust admin template, dashboard template, flat admin template, responsive admin template, web app"
    />
    <meta name="author" content="PIXINVENT"/>
    <title>My Portfolio</title>
    <link rel="stylesheet" href="/assets/main-assets/assets/css/font.css"/>
    <link
            rel="apple-touch-icon"
            sizes="60x60"
            href="/assets/main-assets/app-assets/images/ico/apple-icon-60.png"
    />
    <link
            rel="apple-touch-icon"
            sizes="76x76"
            href="/assets/main-assets/app-assets/images/ico/apple-icon-76.png"
    />
    <link
            rel="apple-touch-icon"
            sizes="120x120"
            href="/assets/main-assets/app-assets/images/ico/apple-icon-120.png"
    />
    <link
            rel="apple-touch-icon"
            sizes="152x152"
            href="/assets/main-assets/app-assets/images/ico/apple-icon-152.png"
    />
    <link
            rel="shortcut icon"
            type="image/x-icon"
            href="/assets/main-assets/app-assets/images/ico/favicon.ico"
    />
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-touch-fullscreen" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="default"/>
    <!-- BEGIN VENDOR CSS-->
    <link
            rel="stylesheet"
            type="text/css"
            href="/assets/main-assets/app-assets/css/bootstrap.css"
    />
    <!-- font icons-->
    <link
            rel="stylesheet"
            type="text/css"
            href="/assets/main-assets/app-assets/fonts/icomoon.css"
    />
    <link
            rel="stylesheet"
            type="text/css"
            href="/assets/main-assets/app-assets/fonts/flag-icon-css/css/flag-icon.min.css"
    />
    <link
            rel="stylesheet"
            type="text/css"
            href="/assets/main-assets/app-assets/vendors/css/extensions/pace.css"
    />
    <!-- END VENDOR CSS-->
    <!-- BEGIN ROBUST CSS-->
    <link
            rel="stylesheet"
            type="text/css"
            href="/assets/main-assets/app-assets/css/bootstrap-extended.css"
    />
    <link
            rel="stylesheet"
            type="text/css"
            href="/assets/main-assets/app-assets/css/app.css"
    />
    <link
            rel="stylesheet"
            type="text/css"
            href="/assets/main-assets/app-assets/css/colors.css"
    />
    <!-- END ROBUST CSS-->
    <!-- BEGIN Page Level CSS-->
    <link
            rel="stylesheet"
            type="text/css"
            href="/assets/main-assets/app-assets/css/core/menu/menu-types/vertical-menu.css"
    />
    <link
            rel="stylesheet"
            type="text/css"
            href="/assets/main-assets/app-assets/css/core/menu/menu-types/vertical-overlay-menu.css"
    />
    <link
            rel="stylesheet"
            type="text/css"
            href="/assets/main-assets/app-assets/css/core/colors/palette-gradient.css"
    />
    <!-- END Page Level CSS-->
    <!-- BEGIN Custom CSS-->
    <link
            rel="stylesheet"
            type="text/css"
            href="/assets/main-assets/assets/css/style.css"
    />
    <!-- END Custom CSS-->

    <style>
        #profile_label {
            margin: 0 auto;
            width: 200px;
            display: block;
            padding: 10px 20px;
            color: #999;
            vertical-align: middle;
            background-color: #fdfdfd;
            cursor: pointer;
            border: 1px solid #ebebeb;
            border-radius: 5px;
        }

        .sns-icon {
            margin-right: 10px;
        }

        .sns-wrapper {
            margin-top: 25%;
            text-align: center;
            font-size: 3rem;
        }

        .sns-wrapper a{
            color: #1d2b36;
        }
        .sns-wrapper a:active{
            color: #1d2b36;
        }
        .sns-wrapper a:visited{
            color: #1d2b36;
        }
        .sns-wrapper a:hover{
            color: #1d2b36;
        }

        .sns-wrapper i{
            margin-right: 30px;
        }
    </style>
</head>

<body
        data-open="click"
        data-menu="vertical-menu"
        data-col="2-columns"
        class="vertical-layout vertical-menu 2-columns fixed-navbar"
>
<!-- navbar-fixed-top-->
<nav
        class="header-navbar navbar navbar-with-menu navbar-fixed-top navbar-semi-dark navbar-shadow"
>
    <div class="navbar-wrapper">
        <div class="navbar-header">
            <ul class="nav navbar-nav">
                <li class="nav-item mobile-menu hidden-md-up float-xs-left">
                    <a class="nav-link nav-menu-main menu-toggle hidden-xs"
                    ><i class="icon-menu5 font-large-1"></i
                    ></a>
                </li>
                <li class="nav-item">
                    <a href="/main.do" class="navbar-brand nav-link"
                    ><img
                            alt="branding logo"
                            src="/assets/main-assets/app-assets/images/logo/my_portfolio_logo.png"
                            data-expand="/assets/main-assets/app-assets/images/logo/my_portfolio_logo.png"
                            data-collapse="/assets/main-assets/app-assets/images/logo/my_portfolio_logo_small.png"
                            class="brand-logo"
                    /></a>
                </li>
                <li class="nav-item hidden-md-up float-xs-right">
                    <a
                            data-toggle="collapse"
                            data-target="#navbar-mobile"
                            class="nav-link open-navbar-container"
                    ><i class="icon-ellipsis pe-2x icon-icon-rotate-right-right"></i
                    ></a>
                </li>
            </ul>
        </div>
        <div class="navbar-container content container-fluid">
            <div id="navbar-mobile" class="collapse navbar-toggleable-sm">
                <ul class="nav navbar-nav">
                    <li class="nav-item hidden-sm-down">
                        <a class="nav-link nav-menu-main menu-toggle hidden-xs"
                        ><i class="icon-menu5"> </i
                        ></a>
                    </li>
                </ul>
                <ul class="nav navbar-nav float-xs-right">
                    <li class="dropdown dropdown-user nav-item">
                        <a
                                href="#"
                                data-toggle="dropdown"
                                class="dropdown-toggle nav-link dropdown-user-link"
                        ><span class="avatar avatar-online"
                        ><img style="width: 30px; height: 30px;"
                                <%
                                    if (userProfile != "") {
                                %>
                              src="/profileimg/<%=userProfile%>"
                                <%
                                } else {
                                %>
                              src="/profileimg/noimage/No_image.png"
                                <%
                                    }
                                %>
                                alt="avatar"/><i></i></span
                        ><span class="user-name"><%=userNm%></span></a
                        >
                        <div class="dropdown-menu dropdown-menu-right">
                            <a href="/mypage.do" class="dropdown-item"
                            ><i class="icon-head"></i> 내 정보 수정
                            </a>
                            <div class="dropdown-divider"></div>
                            <a href="/logout.do" class="dropdown-item text-danger"
                            ><i class="icon-power3"></i> 로그아웃
                            </a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</nav>

<!-- ////////////////////////////////////////////////////////////////////////////-->

<!-- main menu-->
<div
        data-scroll-to-active="true"
        class="main-menu menu-fixed menu-dark menu-accordion menu-shadow"
>
    <!-- main menu header-->
    <!-- <div class="main-menu-header">
    <input type="text" placeholder="Search" class="menu-search form-control round"/>
  </div> -->
    <!-- / main menu header-->
    <!-- main menu content-->
    <div class="main-menu-content">
        <ul
                id="main-menu-navigation"
                data-menu="menu-navigation"
                class="navigation navigation-main"
        >
            <li class="nav-item">
                <a href="/main.do"
                ><i class="icon-paper"></i
                ><span
                        data-i18n="nav.form_layouts.form_layout_basic"
                        class="menu-title"
                >대시보드</span
                ></a
                >
            </li>
            <li class="nav-item">
                <a href="#"
                ><i class="icon-stack-2"></i
                ><span data-i18n="nav.page_layouts.main" class="menu-title"
                >프로젝트 관리</span
                ></a
                >
                <ul class="menu-content">
                    <li>
                        <a
                                href="/padd.do"
                                data-i18n="nav.page_layouts.1_column"
                                class="menu-item"
                        >프로젝트 추가</a
                        >
                    </li>
                    <li>
                        <a
                                href="/plist.do"
                                data-i18n="nav.page_layouts.2_columns"
                                class="menu-item"
                        >프로젝트 목록</a
                        >
                    </li>
                </ul>
            </li>

            <li class="navigation-header">
            <span data-i18n="nav.category.support">포트폴리오</span
            ><i
                    data-toggle="tooltip"
                    data-placement="right"
                    data-original-title="포트폴리오"
                    class="icon-ellipsis icon-ellipsis"
            ></i>
            </li>
            <li class="nav-item">
                <a href="/portfolio.do"
                ><i class="icon-folder3"></i
                ><span
                        data-i18n="nav.form_layouts.form_layout_basic"
                        class="menu-title"
                >포트폴리오 제작</span
                ></a
                >
            </li>
        </ul>
    </div>

    <!-- /main menu content-->
    <!-- main menu footer-->
    <!-- include includes/menu-footer-->
    <!-- main menu footer-->
</div>
<!-- / main menu-->

<div class="app-content content container-fluid">
    <div class="content-wrapper">
        <div class="content-header row">
            <div class="content-header-left col-md-6 col-xs-12 mb-1">
                <h2 class="content-header-title">마이페이지</h2>
            </div>
            <div
                    class="content-header-right breadcrumbs-right breadcrumbs-top col-md-6 col-xs-12"
            >
                <div class="breadcrumb-wrapper col-xs-12">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="/main.do">메인</a>
                        </li>
                        <li class="breadcrumb-item">
                            <a href="#">마이페이지</a>
                        </li>
                    </ol>
                </div>
            </div>
        </div>

        <div class="content-body">
            <section id="card-actions">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">내 정보</h4>
                                <a class="heading-elements-toggle">
                                    <i class="icon-ellipsis font-medium-3"></i>
                                </a>
                                <div class="heading-elements">
                                    <ul class="list-inline mb-0">
                                        <li>
                                            <a data-action="collapse">
                                                <i class="icon-minus4"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="card-body collapse in">
                                <div class="card-block">
                                    <div class="row">
                                        <div class="col-xl-6">
                                            <div class="card-header no-border text-xs-center">
                                                <img style="width: 200px; height: 200px; border-radius: 50%; margin-bottom: 10px;"
                                                    <%
                                                        if(userProfile != ""){
                                                    %>
                                                     src="/profileimg/<%=userProfile%>"
                                                    <%
                                                        } else {
                                                    %>
                                                     src="/profileimg/noimage/No_image.png"
                                                    <%
                                                        }
                                                    %>
                                                     class="rounded-circlr img-fluid center-block">
                                                <h4 style="margin-bottom: 10px;" class="card-title"><%=userNm%>
                                                </h4>
                                                <form id="insertProfile" action="/insertProfile.do"
                                                      enctype="multipart/form-data"
                                                      method="post">
                                                    <label id="profile_label" for="profileimg">업로드</label>
                                                    <input type="file" style="display: none;" id="profileimg"
                                                           name="profileimg" accept="image/*"
                                                           onchange="chk_file_type(this)"/>
                                                </form>
                                                <script>
                                                    function chk_file_type(obj) {
                                                        var file_kind = obj.value.lastIndexOf('.');
                                                        var file_name = obj.value.substring(file_kind + 1, obj.length);
                                                        var file_type = file_name.toLowerCase();

                                                        var check_file_type = new Array();
                                                        check_file_type = ['jpg', 'gif', 'png', 'jpeg', 'bmp'];

                                                        if (check_file_type.indexOf(file_type) == -1) {
                                                            alert("이미지 파일만 선택할 수 있습니다.");
                                                            var parent_Obj = obj.parentNode
                                                            var node = parent_Obj.replaceChild(obj.cloneNode(true), obj);
                                                            return false;
                                                        } else {
                                                            $("#insertProfile").submit();
                                                        }
                                                    }
                                                </script>
                                            </div>
                                        </div>
                                        <div class="col-xl-6">
                                            <form class="form">
                                                <div class="form-group">
                                                    <label for="id">계정</label>
                                                    <input type="text" id="id" class="form-control" readonly
                                                           value="<%=userId%>">
                                                </div>
                                                <div class="form-group">
                                                    <label for="name">이름</label>
                                                    <input type="text" id="name" class="form-control" readonly
                                                           value="<%=userNm%>">
                                                </div>
                                                <div class="form-group">
                                                    <label for="email">이메일</label>
                                                    <input type="text" id="email" class="form-control" readonly
                                                           value="<%=userEmail%>">
                                                </div>
                                                <div class="form-group">
                                                    <label for="phone">휴대폰</label>
                                                    <input type="text" id="phone" class="form-control" readonly
                                                           value="<%=userPhone%>">
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">SNS 주소</h4>
                                <a class="heading-elements-toggle">
                                    <i class="icon-ellipsis font-medium-3"></i>
                                </a>
                                <div class="heading-elements">
                                    <ul class="list-inline mb-0">
                                        <li>
                                            <a data-action="collapse">
                                                <i class="icon-minus4"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="card-body collapse in">
                                <div class="card-block">
                                    <div class="row">
                                        <div class="col-xl-6">
                                            <h4 class="card-title">추가/변경</h4>
                                            <hr/>
                                            <form id="sns_form" class="form" action="/insertSNS.do">
                                                <div class="form-group">
                                                    <label for="github"><i
                                                            class="icon-github sns-icon"></i>GITHUB</label>
                                                    <input type="text" name="github" class="form-control" id="github">
                                                </div>
                                                <div class="form-group">
                                                    <label for="facebook"><i class="icon-facebook-f sns-icon"></i>FACEBOOK</label>
                                                    <input type="text" name="facebook" class="form-control"
                                                           id="facebook">
                                                </div>
                                                <div class="form-group">
                                                    <label for="instagram"><i class="icon-instagram sns-icon"></i>INSTAGRAM</label>
                                                    <input type="text" name="instagram" class="form-control"
                                                           id="instagram">
                                                </div>
                                                <div class="form-actions right">
                                                    <button type="button" class="btn btn-warning mr-1">
                                                        <i class="icon-cross2"></i>
                                                        취소
                                                    </button>
                                                    <button
                                                            type="button"
                                                            id="sns_save"
                                                            class="btn btn-primary"
                                                    >
                                                        <i class="icon-check2"></i>
                                                        저장
                                                    </button>
                                                </div>
                                            </form>

                                            <script>
                                                $("#sns_save").click(function () {
                                                    if ($("#github").val() == "" && $("#facebook").val() == "" && $("#instagram").val() == "") {
                                                        alert("입력값이 하나라도 존재해야 합니다.");
                                                        return null;
                                                    } else {
                                                        $("#sns_form").submit();
                                                    }
                                                })
                                            </script>
                                        </div>

                                        <div class="col-xl-6">
                                            <div id="sns" class="sns-wrapper">
                                            </div>
                                        </div>
                                        <script>
                                            $(window).on('load', function () {
                                                $.ajax({
                                                    type: 'post',
                                                    data: {'userId': '<%=userId%>'},
                                                    url: "/getSNS.do",
                                                    success: function (json) {
                                                        for(var i=0; i<json.length; i++){
                                                            if(json[i].user_git != "") {
                                                                $("#github").val(json[i].user_git);
                                                                $("#sns").append(
                                                                    "<a target='_blank' href='" + json[i].user_git + "'><i class='icon-github'></i></a>"
                                                                );
                                                            }
                                                            if(json[i].user_facebook != "") {
                                                                $("#facebook").val(json[i].user_facebook);
                                                                $("#sns").append(
                                                                    "<a target='_blank' href='" + json[i].user_facebook + "'><i class='icon-facebook-f'></i></a>"
                                                                );
                                                            }
                                                            if(json[i].user_instagram != "") {
                                                                $("#instagram").val(json[i].user_instagram);
                                                                $("#sns").append(
                                                                    "<a target='_blank' href='" + json[i].user_instagram + "'><i class='icon-instagram'></i></a>"
                                                                );
                                                            }
                                                        }
                                                    },
                                                    error: function (error) {
                                                        alert("error : " + error);
                                                    }
                                                })
                                            })
                                        </script>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </section>
        </div>
    </div>
</div>
<!-- ////////////////////////////////////////////////////////////////////////////-->

<footer class="footer footer-static footer-light navbar-border">
    <p class="clearfix text-muted text-sm-center mb-0 px-2">
        <span class="float-md-left d-xs-block d-md-inline-block"
        >Copyright &copy; 2020 KOPO-MSPARK , All rights reserved.
        </span>
    </p>
</footer>

<!-- BEGIN VENDOR JS-->
<script
        src="/assets/main-assets/app-assets/js/core/libraries/jquery.min.js"
        type="text/javascript"
></script>
<script
        src="/assets/main-assets/app-assets/vendors/js/ui/tether.min.js"
        type="text/javascript"
></script>
<script
        src="/assets/main-assets/app-assets/js/core/libraries/bootstrap.min.js"
        type="text/javascript"
></script>
<script
        src="/assets/main-assets/app-assets/vendors/js/ui/perfect-scrollbar.jquery.min.js"
        type="text/javascript"
></script>
<script
        src="/assets/main-assets/app-assets/vendors/js/ui/unison.min.js"
        type="text/javascript"
></script>
<script
        src="/assets/main-assets/app-assets/vendors/js/ui/blockUI.min.js"
        type="text/javascript"
></script>
<script
        src="/assets/main-assets/app-assets/vendors/js/ui/jquery.matchHeight-min.js"
        type="text/javascript"
></script>
<script
        src="/assets/main-assets/app-assets/vendors/js/ui/screenfull.min.js"
        type="text/javascript"
></script>
<script
        src="/assets/main-assets/app-assets/vendors/js/extensions/pace.min.js"
        type="text/javascript"
></script>
<!-- BEGIN VENDOR JS-->
<!-- BEGIN PAGE VENDOR JS-->
<script
        src="/assets/main-assets/app-assets/vendors/js/charts/chart.min.js"
        type="text/javascript"
></script>
<!-- END PAGE VENDOR JS-->
<!-- BEGIN ROBUST JS-->
<script
        src="/assets/main-assets/app-assets/js/core/app-menu.js"
        type="text/javascript"
></script>
<script
        src="/assets/main-assets/app-assets/js/core/app.js"
        type="text/javascript"
></script>
<!-- END ROBUST JS-->
<!-- BEGIN PAGE LEVEL JS-->
<script
        src="/assets/main-assets/app-assets/js/scripts/pages/dashboard-lite.js"
        type="text/javascript"
></script>
<!-- END PAGE LEVEL JS-->
</body>
</html>
