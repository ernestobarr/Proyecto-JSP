<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.UserModel" %>
<%@ page import="DAO.UsuarioDAO" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Usuarios</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
            padding: 40px;
        }
        h1 {
            text-align: center;
            margin-bottom: 30px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 30px;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: #fff;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        form {
            margin: 0;
        }
        input[type="text"] {
            width: 100%;
            padding: 5px;
            border-radius: 5px;
            border: 1px solid #ced4da;
        }
        input[type="submit"],
        .btn-link {
            padding: 5px 10px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }
        .btn-link {
            background-color: transparent;
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="menu.jsp" class="btn-link">Volver a Menu</a>
        <h1>Lista de Usuarios</h1>
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Usuario</th>
                    <th>Correo</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
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
                        <td><input type="text" name="correo" value="<%= usuario.getCorreo() %>"></td>
                        <input type="hidden" name="id" value="<%= usuario.getId() %>">
                        <td>
                            <input type="submit" name="action" value="editar" class="btn btn-primary btn-sm">
                            <a href="CRUDUsuarioServlet?action=borrar&id=<%= usuario.getId() %>" class="btn btn-danger btn-sm">Borrar</a>
                        </td>
                    </form>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
