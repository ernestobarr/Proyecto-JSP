<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Usuario</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        .container {
            max-width: 600px;
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
        form {
            margin-top: 20px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            font-weight: bold;
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ced4da;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: none;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }
        a {
            display: block;
            margin-bottom: 20px;
            text-decoration: none;
            color: #007bff;
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="menu.jsp">Volver a Menu</a>
        <h1>Formulario de Usuario</h1>
        <form action="/Login_user/CRUDUsuarioServlet" method="post">
            <!-- Campo oculto para indicar la acción a realizar -->
            <input type="hidden" name="action" value="crear"> <!-- Valor "crear" para crear un nuevo usuario -->
            <!-- Campo oculto para almacenar el ID del usuario en caso de edición -->
            <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
            <div class="form-group">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" class="form-control" value="<%= request.getParameter("nombre") != null ? request.getParameter("nombre") : "" %>">
            </div>
            <div class="form-group">
                <label for="apellido">Apellido:</label>
                <input type="text" id="apellido" name="apellido" class="form-control" value="<%= request.getParameter("apellido") != null ? request.getParameter("apellido") : "" %>">
            </div>
            <div class="form-group">
                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario" class="form-control" value="<%= request.getParameter("usuario") != null ? request.getParameter("usuario") : "" %>">
            </div>
            <div class="form-group">
                <label for="correo">Correo:</label>
                <input type="text" id="correo" name="correo" class="form-control" value="<%= request.getParameter("correo") != null ? request.getParameter("correo") : "" %>">
            </div>
            <div class="form-group">
                <label for="clave">Clave:</label>
                <input type="password" id="clave" name="clave" class="form-control" value="<%= request.getParameter("clave") != null ? request.getParameter("clave") : "" %>">
            </div>
            <input type="submit" value="Guardar" class="btn btn-primary">
        </form>
    </div>
</body>
</html>