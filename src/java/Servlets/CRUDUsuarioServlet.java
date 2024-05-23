package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import Model.UserModel;
import DAO.UsuarioDAO;
import java.security.SecureRandom;
import javax.servlet.annotation.WebServlet;


import javax.mail.internet.*;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.activation.DataHandler;



@WebServlet("/CRUDUsuarioServlet")
public class CRUDUsuarioServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "editar":
                    editarUsuario(request, response);
                    break;
                case "borrar":
                    borrarUsuario(request, response);
                    break;
                default:
                    // Manejar otros casos si es necesario
                    break;
            }
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "crear":
                    crearUsuario(request, response);
                    break;
                case "editar":
                    editarUsuario(request, response);
                    break;
                case "borrar":
                    borrarUsuario(request, response); // Utilizamos otro método para la acción de borrar
                    break;
                case "resetPassword":
                    resetPassword(request, response);
                    break;
                default:
                    // Manejar caso no válido
                    break;
            }
        }
    }

    private void crearUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String usuario = request.getParameter("usuario");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        
        // Crear un objeto UserModel con los datos recibidos
        UserModel nuevoUsuario = new UserModel(0, nombre, apellido, correo, usuario, clave);

        // Llamar al método en UsuarioDAO para crear un nuevo usuario en la base de datos
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.crearUsuario(nuevoUsuario);

        // Redirigir a alguna página de confirmación o volver al formulario de usuario
        response.sendRedirect("listaUsuarios.jsp");
    }

    private void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Obtener los parámetros del formulario de edición
    int id = Integer.parseInt(request.getParameter("id"));
    String nombre = request.getParameter("nombre");
    String apellido = request.getParameter("apellido");
    String usuario = request.getParameter("usuario");
    String correo = request.getParameter("correo");
    
    // Recuperar la contraseña actual del usuario desde la base de datos
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    String clave = usuarioDAO.obtenerClaveUsuarioPorId(id); 
    
    // Crear un objeto UserModel con los datos editados y la contraseña recuperada
    UserModel usuarioEditado = new UserModel(id, nombre, apellido, usuario);

    // Llamar al método en UsuarioDAO para actualizar el usuario en la base de datos
    usuarioDAO.actualizarUsuario(usuarioEditado);

    // Redirigir a alguna página de confirmación o volver al formulario de usuario
    response.sendRedirect("listaUsuarios.jsp");
}



    private void borrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID del usuario a borrar desde los parámetros de la solicitud
        int id = Integer.parseInt(request.getParameter("id"));
        
        // Llamar al método en UsuarioDAO para borrar el usuario de la base de datos
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.borrarUsuario(id);
        
        // Redirigir a la lista de usuarios después de borrar
        response.sendRedirect("listaUsuarios.jsp");
    }
    
    private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                
        // Verificar si la solicitud es nula
        if (request == null) {
            // Redirigir al inicio de sesión si no hay solicitud
            response.sendRedirect("resetPassword.jsp");
            return; // 
        }
        
        // Obtener User
        String usuario = request.getParameter("usuario");
        
        // Si no se proporciona ningún usuario, redirigir al inicio de sesión
        if(usuario.isEmpty() || usuario.isBlank()) {
            response.sendRedirect("login.jsp");
            return; // ¡
        }
        
        // Generar una nueva contraseña aleatoria
        String newPassword = generarContraseñaAleatoria();              
        
        UserModel userModel = new UserModel(0, "", "", "", "", "");
        
        // Llamar al método en UsuarioDAO para Obtener Usuario
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        userModel = usuarioDAO.obtenerPorUsuario(usuario);
        
        //si no tiene correo no hacemos cambio
        if(userModel.getCorreo().isEmpty() || userModel.getCorreo().isBlank()){
            response.sendRedirect("login.jsp");
            return; //
        }
        
        //actualizar password
        usuarioDAO.actualizarPassword(userModel.getId(), newPassword);
        
        //Enviar Correo
        enviarCorreo(userModel.getCorreo(), newPassword);
        
        // Redirigir a alguna página de confirmación o volver a la lista de usuarios
        response.sendRedirect("login.jsp");
    }

    
     // Método para generar una contraseña aleatoria
    private String generarContraseñaAleatoria() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }
        return sb.toString();
    }
    
    // Método para enviar un correo electrónico con la nueva contraseña
    private void enviarCorreo(String destinatario, String newPassword) {
     // Datos quemados del remitente
        String remitente = "pruebas.aspnet2023@gmail.com";
        String asunto = "Reinicio Password";

        // Configurar las propiedades del servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        // Crear una sesión de correo electrónico con autenticación
        Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(remitente, "lhntrqontbhaqlct");
                }
            });

        try {
            // Crear un objeto Message
            Message message = new MimeMessage(session);
            // Configurar el remitente
            message.setFrom(new InternetAddress(remitente));
            // Configurar el destinatario
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            // Configurar el asunto
            message.setSubject(asunto);
            // Configurar el contenido del mensaje
            message.setText("Su contraseña temporal es: " + newPassword);

            // Enviar el mensaje
            Transport.send(message);

            System.out.println("¡El correo electrónico ha sido enviado correctamente!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
}
