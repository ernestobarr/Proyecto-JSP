<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Usuario</title>
</head>
<body>
    
    <a href="menu.jsp">Volver a Menu</a>
    
    <h1>Formulario de Usuario</h1>
    <form action="/Login_user/CRUDUsuarioServlet" method="post">

        <!-- Campo oculto para indicar la acción a realizar -->
        <input type="hidden" name="action" value="crear"> <!-- Valor "crear" para crear un nuevo usuario -->

        <%-- Campo oculto para almacenar el ID del usuario en caso de edición --%>
        <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
        
        Nombre: <input type="text" name="nombre" value="<%= request.getParameter("nombre") != null ? request.getParameter("nombre") : "" %>"><br>
Apellido: <input type="text" name="apellido" value="<%= request.getParameter("apellido") != null ? request.getParameter("apellido") : "" %>"><br>
Usuario: <input type="text" name="usuario" value="<%= request.getParameter("usuario") != null ? request.getParameter("usuario") : "" %>"><br>
Correo: <input type="text" name="correo" value="<%= request.getParameter("correo") != null ? request.getParameter("correo") : "" %>"><br>
Clave: <input type="password" name="clave" value="<%= request.getParameter("clave") != null ? request.getParameter("clave") : "" %>"><br>

        <input type="submit" value="Guardar">
    </form>
</body>
</html>
