<%@ page import="poly.util.CmmUtil" %>
<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userNm = CmmUtil.nvl((String) session.getAttribute("userName"));
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
    <link rel="stylesheet" type="text/css" href="/assets/main-assets/app-assets/css/app.css"/>
    <link rel="stylesheet" type="text/css" href="/assets/main-assets/app-assets/css/colors.css"/>
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
    <link rel="stylesheet" type="text/css" href="/assets/main-assets/assets/css/style.css"/>
    <!-- END Custom CSS-->

    <script>
        $(window).on("load", function () {
            $.ajax({
                url: "/Project/getProjectInfo.do",
                type: "post",
                dataType: "JSON",
                success: function (json) {

                    if (json.length > 0) {

                        var prjResult = "";

                        for (var i = 0; i < json.length; i++) {
                            json[i].project_name = json[i].project_name.replace(/</g, "&lt;").replace(/>/g, "&gt;");
                            json[i].project_contents = json[i].project_contents.replace(/\<br\/\>/gi,"\n").replace(/</g, "&lt;").replace(/>/g, "&gt;");

                            prjResult += ("<div class='col-xl-4 col-md-6 col-sm-12'>")
                            prjResult += ("<a class='card-hyper' href='/project/projectdetail.do?project_seq=" + json[i].project_seq + "'>");
                            prjResult += ("<div class='card' style='height: auto;'>");
                            prjResult += ("<div class='card-body'>");
                            if (json[i].img_save_path != null) {
                                prjResult += ("<img class='card-img-top img-fluid' src='/prjimg/" + json[i].img_save_path + "' alt='Card image cap'");
                            } else {
                                prjResult += ("<img class='card-img-top img-fluid' style='display: block; margin: 0px auto;' src='/prjimg/noimage/No_image.png' alt='Card image cap'");
                            }
                            prjResult += ("<div class='card-block'>");
                            prjResult += ("<h4 class='card-title'  escapeXml='false'>" + json[i].project_name + "</h4>");
                            prjResult += ("<p class='card-text' style='height: 100px; overflow: hidden;'>" + json[i].project_contents + "</p>");
                            prjResult += ("</div>");
                            prjResult += ("</div>");
                            prjResult += ("</div>");
                            prjResult += ("</a>");
                            prjResult += ("</div>");
                        }

                        $("#prjResult").html(prjResult);
                    } else {
                        var prjResult = "";
                        prjResult += ("<h1 style='color: gray; text-align: center'> 등록된 프로젝트가 없습니다. </h1>");

                        $("#prjResult").html(prjResult);
                    }
                },
                error: function (error) {
                    alert("error : " + error);
                }
            })
        })
    </script>

    <style>
        .card-hyper:active {
            color: black;
        }

        .card-hyper:hover {
            color: black;
        }

        .card-hyper:link {
            color: black;
        }

        .card-hyper:visited {
            color: black;
        }

        .card-title {
            margin: 10px;
        }

        .card-text {
            margin: 10px;
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
                                alt="avatar"/><i></i></span
                        ><span class="user-name"><%=userNm%></span></a
                        >
                        <div class="dropdown-menu dropdown-menu-right">
                            <a href="/mypage.do" class="dropdown-item"
                            ><i class="icon-head"></i> 내 정보 수정 </a
                            >
                            <div class="dropdown-divider"></div>
                            <a href="/logout.do" class="dropdown-item text-danger"
                            ><i class="icon-power3"></i> 로그아웃 </a
                            >
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
            <li class="nav-item has-sub open">
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
                    <li class="active">
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
                <h2 class="content-header-title">프로젝트 목록</h2>
            </div>
            <div class="content-header-right breadcrumbs-right breadcrumbs-top col-md-6 col-xs-12">
                <div class="breadcrumb-wrapper col-xs-12">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="/main.do">메인</a>
                        </li>
                        <li class="breadcrumb-item">
                            <a href="/plist.do">프로젝트 목록</a>
                        </li>
                    </ol>
                </div>
            </div>
        </div>

        <div class="content-body">
            <div id="prjResult" class="row match-height">

            </div>
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
<script src="/assets/main-assets/app-assets/js/core/app.js" type="text/javascript"></script>
<!-- END ROBUST JS-->
<!-- BEGIN PAGE LEVEL JS-->
<script
        src="/assets/main-assets/app-assets/js/scripts/pages/dashboard-lite.js"
        type="text/javascript"
></script>
<!-- END PAGE LEVEL JS-->
</body>
</html>
