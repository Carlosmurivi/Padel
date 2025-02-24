<%@page import="Model.Tip"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Padel Page - Tips</title>
    <link rel="stylesheet" href="../../css/tips.css">
    <link rel="icon" href="../../images/raqueta-de-padel.png">
</head>
<body>

	<div class="container">
        <h1>Ayudas Padel</h1>
        <table border=1>
			<tr>
				<th>ID</th>
				<th>TITULO</th>
			</tr>
        	
        	<%
        	List<Tip> tips = (List<Tip>)request.getAttribute("lista");
        	int categoria_id = (Integer)request.getAttribute("categoria_id");
        	
        	for(Tip tip: tips){
        		if(tip.getCategoria_id() == categoria_id){
	        		%>
    	    		<tr>
						<td><%=tip.getId()%></td>
						<td><%=tip.getTitulo()%></td>
					</tr>
	        		<%
        		}
        	}
        	%>
        	</table>
        </div>
    </div>

</body>
</html>