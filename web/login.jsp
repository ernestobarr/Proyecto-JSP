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
   <style>
        body {
             margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
           /* background-image: url('https://img.freepik.com/vector-premium/elegante-fondo-dorado-azul-marino-capa-superpuesta-traje-seminarios-charlas-festivas-fiesta-institucion-corporativa-empresarial_181182-23856.jpg?w=1060');*/
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }
        .card {
            width: 30rem;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h2 class="text-center mb-4">Iniciar Sesión</h2>
                        <form method="post" action="/Login_user/LoginController">
                            <div class="form-group">
                                <input type="text" name="txtusuario" class="form-control" placeholder="Usuario" required>
                            </div>
                            <div class="form-group">
                                <input type="password" name="txtclave" class="form-control" placeholder="Contraseña" required>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-primary btn-block" name="btn-login" value="Ingresar">
                            </div>
                            <p class="text-danger text-center"><%= rspta %></p>
                        </form>
                        <p class="text-center"><a href="resetPassword.jsp">¿Olvidaste tu contraseña?</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>