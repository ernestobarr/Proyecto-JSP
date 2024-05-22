package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import BD.conexion;
import Model.UserModel;

public class UsuarioDAO {
    private static final String INSERT_USUARIO_SQL = "INSERT INTO Users (nombre, apellido, usuario, clave) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_USUARIOS = "SELECT * FROM Users";
      private static final String UPDATE_USUARIO_SQL = "UPDATE Users SET nombre=?, apellido=?, usuario=?, clave=? WHERE id=?";
    private static final String DELETE_USUARIO_SQL = "DELETE FROM Users WHERE id=?";
    
    
    public void crearUsuario(UserModel usuario) {
        try (Connection connection = conexion.conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USUARIO_SQL)) {
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellido());
            preparedStatement.setString(3, usuario.getUsuario());
            preparedStatement.setString(4, usuario.getClave());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Manejar excepción
            e.printStackTrace();
        }
    }
    
    // Método para obtener la contraseña de un usuario por su ID
    public String obtenerClaveUsuarioPorId(int id) {
        String clave = null;
        String sql = "SELECT clave FROM Users WHERE id = ?";
        
        try (Connection connection = conexion.conectar();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    clave = rs.getString("clave");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return clave;
    }

    public List<UserModel> obtenerTodosUsuarios() {
        List<UserModel> usuarios = new ArrayList<>();
        try (Connection connection = conexion.conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USUARIOS);
            ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String usuario = rs.getString("usuario");
                String clave = rs.getString("clave");
                usuarios.add(new UserModel(id, nombre, apellido, usuario, clave));
            }
        } catch (SQLException e) {
            // Manejar excepción
            e.printStackTrace();
        }
        return usuarios;
    }
    
    // Método para actualizar un usuario en la base de datos
    public void actualizarUsuario(UserModel usuario) {
        try (Connection connection = conexion.conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USUARIO_SQL)) {
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellido());
            preparedStatement.setString(3, usuario.getUsuario());
            preparedStatement.setString(4, usuario.getClave());
            preparedStatement.setInt(5, usuario.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Manejar excepción
            e.printStackTrace();
        }
    }

    // Método para borrar un usuario de la base de datos
    public void borrarUsuario(int id) {
        try (Connection connection = conexion.conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USUARIO_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Manejar excepción
            e.printStackTrace();
        }
    }
    
}
