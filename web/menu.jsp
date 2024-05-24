<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@ page import="DAO.UsuarioDAO" %>
<%
    // Verificar si el usuario ha iniciado sesión
    Boolean usuarioAutenticado = (Boolean) session.getAttribute("usuarioAutenticado");
    if (usuarioAutenticado != null && usuarioAutenticado) {
        // Usuario autenticado, mostrar el contenido de menu.jsp
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menú</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            margin-top: 50px;
        }
        .title {
            text-align: center;
            margin-bottom: 30px;
            color: #007bff;
        }
        .menu-item {
            list-style: none;
            padding: 10px 0;
        }
        .menu-link {
            text-decoration: none;
            color: #343a40;
            font-weight: bold;
        }
        .menu-link:hover {
            color: #007bff;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="title">¡Bienvenido al Menú!</h1>
        <ul class="list-group">
            <li class="list-group-item menu-item">
                <a href="listaUsuarios.jsp" class="menu-link">Lista de Usuarios</a>
            </li>
            <li class="list-group-item menu-item">
                <a href="formularioUsuario.jsp" class="menu-link">Crear Nuevo Usuario</a>
            </li>
            <li class="list-group-item menu-item">
                <a href="changePassword.jsp" class="menu-link">Cambiar Contraseña</a>
            </li>
            <li class="list-group-item menu-item">
                <form action="LogoutController" method="post">
                    <button type="submit" class="btn btn-danger">Cerrar Sesión</button>
                </form>
            </li>
        </ul>
    </div>
</body>
</html>


<%
    } else {
        // Usuario no autenticado, redirigir a la página de inicio de sesión
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
%>