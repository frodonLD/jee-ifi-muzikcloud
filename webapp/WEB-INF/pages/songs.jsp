
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="fr">
<head>
	<title>Toute la musique</title>
</head>
<body>

Affichage de mes sons
<table>
	<c:forEach var="song" items="${songs}">
		<tr>
		    <td>
		    	<c:out value="${song.getTitre())}" /><br>
		    </td>
		</tr>
	</c:forEach>
</table>
	
</body>
</html>
