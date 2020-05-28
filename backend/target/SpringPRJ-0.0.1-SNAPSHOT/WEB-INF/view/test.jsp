<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script
            src="https://code.jquery.com/jquery-3.5.0.js"
            integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
            crossorigin="anonymous"
    ></script>
</head>
<body>

<script>
    navigator.geolocation.getCurrentPosition(function(position){

        var corlat = position.coords.latitude;
        var corlon = position.coords.longitude;

        var address = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=uYjPTFwPcbr77jc2GAssNa4cUqXGYHlB5CvomJDMlP97Qtlb0R4dmbZxRH6otoRW7s%2BHljMw7QGLTZ1gJkaN9w%3D%3D&numOfRows=20&pageNo=1&base_date=20200528&base_time=0815&nx=" + corlat + "&ny=" + corlon + "&dataType=json";
        console.log(address);

        $.ajax({
            url: address,
            dataType: "json",
            type: "GET",
            success: function(resp) {
                alert(resp);
                // console.log("카테고리 : "+ (resp.main.temp- 273.15) );
                // console.log("현재습도 : "+ resp.main.humidity);
                // console.log("날씨 : "+ resp.weather[0].main );
                // console.log("상세날씨설명 : "+ resp.weather[0].description );
                // console.log("날씨 이미지 : "+ resp.weather[0].icon );
                // console.log("바람   : "+ resp.wind.speed );
                // console.log("나라   : "+ resp.sys.country );
                // console.log("도시이름  : "+ resp.name );
                // console.log("구름  : "+ (resp.clouds.all) +"%" );
            }
        })

    })


</script>


</body>
</html>
