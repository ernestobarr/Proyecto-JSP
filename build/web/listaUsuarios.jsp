<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.UserModel" %>
<%@ page import="DAO.UsuarioDAO" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuarios</title>
</head>
<body>
    <h1>Lista de Usuarios</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Usuario</th>
            <th>Acciones</th>
        </tr>
        <% 
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            List<UserModel> usuarios = usuarioDAO.obtenerTodosUsuarios();
            for (UserModel usuario : usuarios) {
        %>
        <tr>
            <form action="CRUDUsuarioServlet" method="post">
                <td><%= usuario.getId() %></td>
                <td><input type="text" name="nombre" value="<%= usuario.getNombre() %>"></td>
                <td><input type="text" name="apellido" value="<%= usuario.getApellido() %>"></td>
                <td><input type="text" name="usuario" value="<%= usuario.getUsuario() %>"></td>
                <input type="hidden" name="id" value="<%= usuario.getId() %>">
                <td>
                    <input type="submit" name="action" value="editar">
                    <a href="CRUDUsuarioServlet?action=borrar&id=<%= usuario.getId() %>">Borrar</a>
                </td>
            </form>
        </tr>
        <% } %>
    </table>
</body>
</html>
