<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MainPage</title>
	<style>
		h1{
		text-align: center;
		}
		a{width: 50px; height: 50px}
		a:link {text-decoration: none; color: black;}
		a:visited {text-decoration: none; color: black;}
		a:hover {background-color:black; color: white;}
		ul{
		text-align: center;
		padding-right: 100px;
		padding-left: 100px
		
		}
		ul li{
		display: inline-block;
		border: 1px;
		padding : 0px 10px;
		margin: 10px 20px;
		}		
	</style>
</head>
<body>
<h1>클라우드 서비스입니다.</h1>
<ul>
	<li><p><a href='<c:url value="/upload/new"/>'>업로드</a></p></li>
	<li><p><a href='<c:url value="/upload/list"/>'>파일  전체 목록</a></p></li>
	<li><p><a href='<c:url value="/upload/list/"/>'>루트 디렉토리 목록</a></p></li>
	<li><p><a href='<c:url value="/upload/gallery"/>'>갤러리</a></p></li>	
</ul>

<%-- <p><a href='<c:url value="/upload/new"/>'>업로드</a></p>
<p><a href='<c:url value="/upload/list"/>'>파일  전체 목록</a></p>
<p><a href='<c:url value="/upload/list/"/>'>루트 디렉토리 목록</a></p>
<p><a href='<c:url value="/upload/gallery"/>'>갤러리</a></p> --%>
</body>
</html>