<%@page import="Controllers.ServletTips"%>
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
        <div class="buttons">
            <a href="../../ServletTips?action=listTips&categoria_id=1" class="btn tecnica"><span>TECNICA</span></a>
            <a href="../../ServletTips?action=listTips&categoria_id=3" class="btn tactica"><span>TACTICA</span></a>
        </div>
    </div>

</body>
</html>