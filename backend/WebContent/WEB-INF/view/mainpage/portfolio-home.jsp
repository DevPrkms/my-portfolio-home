<%@ page import="poly.util.CmmUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userNm = CmmUtil.nvl((String) session.getAttribute("userName"));
    String userProfile = CmmUtil.nvl((String) session.getAttribute("userProfile"));
    String userId = CmmUtil.nvl((String) session.getAttribute("userId"));
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title><%=userNm%> Portfolio</title>
    <meta content="" name="descriptison">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="/assets/portfolio-home/img/favicon.png" rel="icon">
    <link href="/assets/portfolio-home/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
          rel="stylesheet">

    <!-- jQuery load -->
    <script
            src="https://code.jquery.com/jquery-3.5.0.js"
            integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
            crossorigin="anonymous"
    ></script>

    <!-- Vendor CSS Files -->
    <link href="/assets/portfolio-home/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/assets/portfolio-home/vendor/icofont/icofont.min.css" rel="stylesheet">
    <link href="/assets/portfolio-home/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="/assets/portfolio-home/vendor/venobox/venobox.css" rel="stylesheet">
    <link href="/assets/portfolio-home/vendor/aos/aos.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="/assets/portfolio-home/css/style.css" rel="stylesheet">

    <script>
        $(window).on('load', function () {
            $.ajax({
                type: 'post',
                data: {'userId': '<%=userId%>'},
                url: "/getSNS.do",
                success: function (json) {
                    console.log(json);
                    for (var i = 0; i < json.length; i++) {
                        if (json[i].user_git != "") {
                            $("#sns").append(
                                "<a target='_blank' href='" + json[i].user_git + "'><i class='bx bxl-github'></i></a>"
                            );
                        }
                        if (json[i].user_facebook != "") {
                            $("#sns").append(
                                "<a target='_blank' href='" + json[i].user_facebook + "'><i class='bx bxl-facebook'></i></a>"
                            );
                        }
                        if (json[i].user_instagram != "") {
                            $("#sns").append(
                                "<a target='_blank' href='" + json[i].user_instagram + "'><i class='bx bxl-instagram'></i></a>"
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
    <!-- Progress bar -->
    <link rel="stylesheet" href="/assets/css/jquery.lineProgressbar.css"/>

    <!-- font -->
    <link rel="stylesheet" href="/assets/main-assets/assets/css/font.css"/>

    <style>
        html {
            font-family: "Do Hyeon", sans-serif !important;
        }

        #sns a {
            color: #1d2b36;
        }

        #sns a:active {
            color: #1d2b36;
        }

        #sns a:visited {
            color: #1d2b36;
        }

        #sns a:hover {
            color: #1d2b36;
        }

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
            font-family: "Do Hyeon", sans-serif !important;
        }

        .card-text {
            margin: 10px;
        }
    </style>
</head>

<body>

<!-- ======= Mobile nav toggle button ======= -->
<button type="button" class="mobile-nav-toggle d-xl-none"><i class="icofont-navigation-menu"></i></button>

<!-- ======= Header ======= -->
<header id="header">
    <div class="d-flex flex-column">

        <div class="profile">
            <% if (userProfile != null) { %>
            <img style="width: 150px; height: 150px;" src="/profileimg/<%=userProfile%>" alt=""
                 class="img-fluid rounded-circle">
            <% } else { %>
            <img style="width: 150px; height: 150px;" src="/profileimg/noimage/No_image.png" alt=""
                 class="img-fluid rounded-circle">
            <% } %>
            <h1 class="text-light"><%=userNm%>
            </h1>
            <div id="sns" class="social-links mt-3 text-center">
            </div>
        </div>

        <nav class="nav-menu">
            <ul>
                <li class="active"><a href="#about"><i class="bx bx-user"></i> <span>About</span></a></li>
                <li><a href="#skills"><i class="bx bx-pencil"></i> <span>Skills</span></a></li>
                <li><a href="#portfolio"><i class="bx bx-book-content"></i> Portfolio</a></li>
            </ul>
        </nav><!-- .nav-menu -->
        <button type="button" class="mobile-nav-toggle d-xl-none"><i class="icofont-navigation-menu"></i></button>

    </div>
</header><!-- End Header -->

<main id="main">

    <!-- ======= About Section ======= -->
    <section id="about" class="about">
        <div class="container">

            <div class="section-title">
                <h2>About</h2>
            </div>

            <div class="row">
                <div class="col-lg-4" data-aos="fade-right">
                    <% if (userProfile != null) { %>
                    <img style="width: 350px; height: 350px;" src="/profileimg/<%=userProfile%>" alt=""
                         class="img-fluid rounded-circle">
                    <% } else { %>
                    <img style="width: 350px; height: 350px;" src="/profileimg/noimage/No_image.png" alt=""
                         class="img-fluid rounded-circle">
                    <% } %>
                </div>
                <div class="col-lg-8 pt-4 pt-lg-0 content" data-aos="fade-left">
                    <div class="row">
                        <div>
                            <ul>
                                <li><i class="icofont-rounded-right"></i> <strong>Name :</strong> <%=userNm%>
                                </li>
                                <li><i class="icofont-rounded-right"></i> <strong>E-mail
                                    :</strong> <%=session.getAttribute("userEmail")%>
                                </li>
                                <li><i class="icofont-rounded-right"></i> <strong>Phone
                                    :</strong> <%=session.getAttribute("userPhone")%>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </section><!-- End About Section -->

    <!-- ======= Skills Section ======= -->
    <section id="skills" class="skills section-bg">
        <div class="container">

            <div class="section-title">
                <h2>Skills</h2>
            </div>

            <div id="prg_level">
            </div>
            <script>
                $(window).on("load", function () {
                    var userId = '<%=userId%>';
                    var gotocnt = 0;
                    $.ajax({
                        type: 'POST',
                        data: {'userId': userId},
                        url: "/getPrograming.do",
                        success: function (json) {
                            if (json.length == 0) {
                                gotocnt = 0;
                                $("#prg_level").append(
                                    "<h4 class='card-title' style='color: red'> 정보가 없습니다. </h4>"
                                );
                            } else {
                                for (i = 0; i < json.length; i++) {
                                    var prg_name = json[i].program_name;
                                    var prg_score = json[i].program_score;

                                    $("#prg_level").append(
                                        "<p>" + prg_name + "</p>"
                                    );

                                    $("#prg_level").append(
                                        "<div id='prg_bar" + i + "'></div>"
                                    );

                                    $("#prg_bar" + i).LineProgressbar({
                                        percentage: prg_score,
                                        fillBackgroundColor: ("#" + Math.round(Math.random() * 0xffffff).toString(16)),
                                        radius: '15px'
                                    });
                                }
                                gotocnt = 1;
                            }
                        },
                        error: function (error) {
                            alert("error : " + error);
                        }
                    })

                    $("#goto").click(function () {
                        if (gotocnt == 0) {
                            alert("프로그래밍 수준 입력 후 이용가능합니다.");
                            return null;
                        } else {
                            alert("이동함");
                        }
                    })
                });
            </script>


        </div>
    </section><!-- End Skills Section -->

    <!-- ======= Portfolio Section ======= -->
    <section id="portfolio" class="portfolio section-bg">
        <div class="container">

            <div class="section-title">
                <h2>Portfolio</h2>
            </div>

            <div id="prjResult" class="row match-height">
            </div>
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
                                    json[i].project_contents = json[i].project_contents.replace(/\<br\/\>/gi, "\n").replace(/</g, "&lt;").replace(/>/g, "&gt;");

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
                                    prjResult += ("<h4 class='card-title' style='font-size: 1rem'  escapeXml='false'>" + json[i].project_name + "</h4>");
                                    prjResult += ("<p class='card-text' style='height: 100px; overflow: hidden; font-size: 0.9rem'>" + json[i].project_contents + "</p>");
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

        </div>
    </section><!-- End Portfolio Section -->


</main><!-- End #main -->
<div class="hero-container" data-aos="fade-in" style="text-align: center; height: 200px; margin-top: 100px; margin-left: 5%;">
        <a href="/main.do"><h1>My Portfolio</h1></a>
        <p><span class="typed" data-typed-items="FrontEnd Developer, BackEnd Developer, FullStack Devloper, Freelancer"></span> Portfolio</p>
</div>

<!-- ======= Footer ======= -->
<footer id="footer">
    <div class="container">
        <div class="copyright">
            &copy; Copyright <strong><span>MS-Park</span></strong>
        </div>
        <div class="credits">
        </div>
    </div>
</footer><!-- End  Footer -->

<a href="#" class="back-to-top"><i class="icofont-simple-up"></i></a>

<!-- progress bar -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/waypoints/4.0.1/jquery.waypoints.js"></script>
<script src="/assets/js/jquery.lineProgressbar.js"></script>

<!-- Vendor JS Files -->
<script src="/assets/portfolio-home/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/assets/portfolio-home/vendor/php-email-form/validate.js"></script>
<script src="/assets/portfolio-home/vendor/counterup/counterup.min.js"></script>
<script src="/assets/portfolio-home/vendor/isotope-layout/isotope.pkgd.min.js"></script>
<script src="/assets/portfolio-home/vendor/venobox/venobox.min.js"></script>
<script src="/assets/portfolio-home/vendor/owl.carousel/owl.carousel.min.js"></script>
<script src="/assets/portfolio-home/vendor/typed.js/typed.min.js"></script>
<script src="/assets/portfolio-home/vendor/aos/aos.js"></script>
<script src="/assets/portfolio-home/vendor/jquery.easing/jquery.easing.min.js"></script>

<!-- Template Main JS File -->
<script src="/assets/portfolio-home/js/main.js"></script>

</body>

</html>
