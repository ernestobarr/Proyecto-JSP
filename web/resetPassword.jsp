<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <%  String rspta="";
        if(request.getParameter("rspta")!=null){
            int r=Integer.parseInt(request.getParameter("rspta"));
            if(r==0){
                rspta="Error: datos incorrectos";
            }
        }
    %>
    <body>
    <center>
        <div class="container" style="margin-top: 80px;">
            
            <a href="login.jsp">Volver a Login</a>
             <form action="/Login_user/CRUDUsuarioServlet" method="post">

                 
                 
        <!-- Campo oculto para indicar la acción a realizar -->
        <input type="hidden" name="action" value="resetPassword"> 
               
        
        Usuario: <input type="text" name="usuario" ><br>
        

        <input type="submit" value="Reiniciar Contraseña">
    </form>
        </div>
    </center>
</body>
</html>
