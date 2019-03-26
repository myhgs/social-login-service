<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>로그인 테스트</title>
</head>
<script src="/static/js/jquery-1.12.1.min.js"></script>
<body>
<button onclick="login('facebook');">페이스북 연동</button><br>
<button onclick="login('naver');">네이버 연동</button><br>
<button onclick="login('kakao');">카카오톡 연동</button>

</body>
<script>
    var facebookAuthUrl = "";
    var naverAuthUrl = "";
    var kakaoAuthUrl = "";

    //로그인 url 정보 가져오는 부분
    $(document).ready(function(){
        $.ajax({
            url: "/api/getAuthUrl",
            method : "GET"
        }).done(function(data) {
            facebookAuthUrl = data.facebook;
            naverAuthUrl = data.naver;
            kakaoAuthUrl = data.kakao;
        });
    });

    //로그인 결과 리턴
    function socialAuthData(data){
        var data = JSON.parse(data);
        console.log(data);
    }

    //소셜 로그인 팝업
    function login(type){
        if(type == 'facebook'){
            window.open(facebookAuthUrl, "SocialLogin", specs);
        }else if(type == 'naver'){
            window.open(naverAuthUrl, "SocialLogin", specs);
        }else if(type == 'kakao'){
            window.open(kakaoAuthUrl, "SocialLogin", specs);
        }
    }
</script>
</html>