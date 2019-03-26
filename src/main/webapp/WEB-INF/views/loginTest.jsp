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

    function socialAuthData(data){
        var data = JSON.parse(data);
        console.log(data);
    }


    function login(type){
        var specs = 'titlebar=1, resizable=1, scrollbars=yes, width=600, height=550';

        var UserAgent = navigator.userAgent;
        /* 모바일 접근 체크*/
        // 모바일일 경우 (변동사항 있을경우 추가 필요)
        if (UserAgent.match(/iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null
                || UserAgent.match(/LG|SAMSUNG|Samsung/) != null) {
            if(type == 'facebook'){
                location.href = facebookAuthUrl;
            }else if(type == 'naver'){
                location.href = naverAuthUrl;
            }else if(type == 'kakao'){
                location.href = kakaoAuthUrl;
            }
        }else{
            if(type == 'facebook'){
                window.open(facebookAuthUrl, "SocialLogin", specs);
            }else if(type == 'naver'){
                window.open(naverAuthUrl, "SocialLogin", specs);
            }else if(type == 'kakao'){
                window.open(kakaoAuthUrl, "SocialLogin", specs);
            }
        }
    }
</script>
</html>