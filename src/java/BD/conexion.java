package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conexion {
    public static String url = "jdbc:mysql://localhost:3306/dbuser";
    public static String usuario = "root";
    public static String clave = "admin";
    public static String clase = "com.mysql.jdbc.Driver";
    private static Connection conexion = null;

    public static Connection conectar() {
    try {
        if (conexion == null || conexion.isClosed()) {
            Class.forName(clase);
            conexion = (Connection) DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión establecida con la base de datos: " + url);
        }
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println(e);
    }
    return conexion;
}


    public ResultSet ejecutarConsulta(String sql) throws Exception {
        Statement st = null;
        st = conectar().createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public int ejecutarActualizacionP(String sql, Object[] params) throws SQLException {
        PreparedStatement ps = conectar().prepareStatement(sql);
        //definir los valores para los argumentos
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
        int r = ps.executeUpdate(); //retorna la cantidad de registros actualizados    
        return r;
    }

    public void desconectar() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }
}
