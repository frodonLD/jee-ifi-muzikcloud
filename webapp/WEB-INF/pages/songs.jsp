
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="fr">
<head>
	<title>Toute la musique</title>
</head>
<body>

<table>
	<c:forEach var="album" items="${albums}">
		<tr>
		    <td>
		    	<c:out value="${album.getTitre()}" /><br>
		    </td>
		</tr>
	</c:forEach>
</table>
	
</body>
</html>
