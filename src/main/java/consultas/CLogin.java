package consultas;

import conexion.Conexion;
import models.LoginViewModel;
import models.UserLogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CLogin {


    public static UserLogin Login(LoginViewModel loginViewModel){
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String  sql = "select u.id, u.nombre , u.pass , u.username, r.rol from usuarios as u inner join rol as r on u.id_rol = r.id_rol where u.username = ? and u.pass = ?;";
        Conexion conexion = new Conexion();
        UserLogin userLogin = new UserLogin();
        try {

            Connection connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,loginViewModel.getUsername());
            preparedStatement.setString(2,loginViewModel.getPassword());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                userLogin.setId(resultSet.getInt(1));
                userLogin.setNombre(resultSet.getString(2));
                userLogin.setUsername(resultSet.getString(3));
                userLogin.setRol(resultSet.getString(4));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return  userLogin;
    }
}
