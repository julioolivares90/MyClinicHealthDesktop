package conexion;
import Constantes.Constantes;

import  java.sql.DriverManager;
import  java.sql.Connection;
import  java.sql.SQLException;
public class Conexion {
    Connection connection;
    private  static  final String DRIVER= "com.mysql.cj.jdbc.Driver";
    private static  final String DRIVER_DEPRECATED ="com.mysql.jdbc.Driver";

    public Connection getConnection(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(Constantes.url,Constantes.user,Constantes.pass);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  connection;
    }
}
