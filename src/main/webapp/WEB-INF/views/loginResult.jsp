<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %> 

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>로그인 테스트</title>
</head>
<body>
</body>
<script>
    //로그인 결과
    var data = '${data}';
    //결과값 전달
    window.opener.socialAuthData(data);
    //팝업 닫기
    window.close();
</script>
</html>