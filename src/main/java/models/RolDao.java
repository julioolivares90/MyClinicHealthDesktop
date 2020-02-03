package models;

import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RolDao  implements Crud{
    Connection connection;
    Conexion conexion = new Conexion();
    PreparedStatement preparedStatement;
    ResultSet resultSet;


    public List listar() {
        List<Rol> rols = new ArrayList<Rol>();
        String query = "select * from  rol";
        try{
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Rol rol = new Rol();
                rol.setId_rol(resultSet.getInt(1));
                rol.setRol(resultSet.getString(2));

                rols.add(rol);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return rols;
    }

    public int add(Object object) {
        Rol rol = (Rol)object;
        int rs = 0;
        String query = "insert into rol (rol) value(?)";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,rol.getRol());
            rs = preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public int update(Object object) {
        Rol rol = (Rol)object;
        int rs = 0;
        String query = "update from rol set rol = ? where id_rol = ?";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,rol.getRol());
            preparedStatement.setInt(2,rol.getId_rol());
            rs = preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public int delete(int id) {

        int rs = 0;
        String query = "delete from rol  where id_rol = ?";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return rs;
    }
}
