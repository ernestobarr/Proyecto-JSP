package DAO;

import BD.conexion;
import Model.UserModel;
import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.ResultSet;

public class LoginDAO {
    int rspta=0;
    String clave ="";
    String sql="";
    ResultSet rs=null;
    conexion cn=new conexion();
    
    public int validarLogin(UserModel um) throws Exception {
        sql = "SELECT clave FROM Users WHERE usuario='" + um.getUsuario() + "'";
        rs = cn.ejecutarConsulta(sql);
        while(rs.next()) {
            clave = rs.getString("clave");
            if (!clave.isEmpty()) {
                BCrypt.Result verifyer = BCrypt.verifyer().verify(um.getClave().toCharArray(), clave);
                System.out.println(verifyer.verified==true);
                if (verifyer.verified==true) {
                    rspta = 1;
                }
            }
        }
        return rspta;
    }   
}
