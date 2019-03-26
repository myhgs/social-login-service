<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
</body>
<script>

    var data = '${data}';
    window.opener.socialAuthData(data);
    window.close();

</script>
</html>