package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import Model.UserModel;
import DAO.UsuarioDAO;
import javax.servlet.annotation.WebServlet;

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
        String clave = request.getParameter("clave");
        
        // Crear un objeto UserModel con los datos recibidos
        UserModel nuevoUsuario = new UserModel(0, nombre, apellido, usuario, clave);

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
    
    // Recuperar la contraseña actual del usuario desde la base de datos
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    String clave = usuarioDAO.obtenerClaveUsuarioPorId(id); 
    
    // Crear un objeto UserModel con los datos editados y la contraseña recuperada
    UserModel usuarioEditado = new UserModel(id, nombre, apellido, usuario, clave);

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
}
