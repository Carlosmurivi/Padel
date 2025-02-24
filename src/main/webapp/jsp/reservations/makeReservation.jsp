<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.Pista" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Pistas</title>
<link rel="stylesheet" href="../css/registre.css">
</head>
<body>
    <h1>Lista de Pistas</h1>

    <%
        List<Pista> pistas = (List<Pista>) request.getAttribute("listaPistas");

        if (pistas != null && !pistas.isEmpty()) {
    %>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Ubicación</th>
                <th>Tipo</th>
                <th>Estado</th>
                <th>Precio por Hora</th>
            </tr>
        </thead>
        <tbody>
            <% for (Pista p : pistas) { %>
            <tr>
                <td><%= p.getId() %></td>
                <td><%= p.getNombre() %></td>
                <td><%= p.getDescripcion() %></td>
                <td><%= p.getUbicacion() %></td>
                <td><%= p.getTipo() %></td>
                <td><%= p.getEstado() %></td>
                <td>€<%= p.getPrecioPorHora() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <% } else { %>
    <p>No hay pistas disponibles</p>
    <% } %>
</body>
</html>
