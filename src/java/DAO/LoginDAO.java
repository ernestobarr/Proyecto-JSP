package DAO;

import BD.conexion;
import Model.UserModel;
import java.sql.ResultSet;

public class LoginDAO {
    int rspta=0;
    String sql="";
    ResultSet rs=null;
    conexion cn=new conexion();
    
    public int validarLogin(UserModel um) throws Exception {
        sql = "SELECT COUNT(id) AS cantidad FROM Users WHERE usuario='" + um.getUsuario() + "' AND clave='" + um.getClave() + "'";
        rs = cn.ejecutarConsulta(sql);
        while(rs.next()) {
            rspta = rs.getInt("cantidad");
        }
        return rspta;
    }   
}
