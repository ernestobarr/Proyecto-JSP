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
<html>
<head>
    <meta charset="UTF-8">
    <title>Menú</title>
</head>
<body>
    <h1>¡Bienvenido al Menú!</h1>
    <ul>
        <a href="listaUsuarios.jsp">Lista de Usuarios</a>
<a href="formularioUsuario.jsp">Crear Nuevo Usuario</a>

            <form action="LogoutController" method="post">
                <input type="submit" value="Logout">
            </form>
        </li>
    </ul>
</body>
</html>
<%
    } else {
        // Usuario no autenticado, redirigir a la página de inicio de sesión
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
%>