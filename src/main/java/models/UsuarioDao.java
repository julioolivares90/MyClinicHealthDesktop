package models;

import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements Crud {
    private Connection connection;
    private Conexion conexion = new Conexion();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public List listar() {
        List<UsuarioViewModel> usuarios = new ArrayList<UsuarioViewModel>();
        String query = "select usuarios.id, usuarios.nombre , usuarios.apellido, usuarios.username , rol.rol from usuarios  inner join rol  on rol.id_rol = usuarios.id_rol;";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.toString());
            while (resultSet.next()){
                UsuarioViewModel usuario = new UsuarioViewModel();
                usuario.setID(resultSet.getInt(1));
                usuario.setNombre(resultSet.getString(2));
                usuario.setApellido(resultSet.getString(3));
                usuario.setUserName(resultSet.getString(4));
                usuario.setRol(resultSet.getString(5));

                usuarios.add(usuario);
            }
        }
        catch (Exception e ){
            System.out.println(e.getMessage());
        }
        return usuarios;
    }

    public Usuario findUserByID(int id){
        Usuario usuario = new Usuario();
        String query = "select * from usuarios where id=? ";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                usuario.setID(resultSet.getInt(1));
                usuario.setNombre(resultSet.getString(2));
                usuario.setApellido(resultSet.getString(3));
                usuario.setUserName(resultSet.getString(4));
                usuario.setPass(resultSet.getString(5));
                usuario.setId_rol(resultSet.getInt(6));
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return usuario;
    }
    public int add(Object object) {
        Usuario usuario = (Usuario) object;
        int res=0;
        String query = "insert into usuarios (nombre,apellido,username,pass,id_rol) value(?,?,?,?,?)";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,usuario.getNombre());
            preparedStatement.setString(2,usuario.getApellido());
            preparedStatement.setString(3,usuario.getUserName());
            preparedStatement.setString(4,usuario.getPass());
            preparedStatement.setInt(5,usuario.getId_rol());
            res = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int update(Object object) {
        Usuario usuario = (Usuario) object;
        int res=0;
        String query = "update usuarios set nombre=?,apellido=?,username=?,id_rol=? where id=?";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1,usuario.getNombre());
            preparedStatement.setString(2,usuario.getApellido());
            preparedStatement.setString(3,usuario.getUserName());
            preparedStatement.setInt(4,usuario.getId_rol());
            preparedStatement.setInt(5,usuario.getID());

            res = preparedStatement.executeUpdate();

        } catch (SQLException e) {
           System.out.println(e.getMessage());
        }
        return res;
    }

    public int delete(int id) {
        int res = 0;
        String query = "delete from usuarios where id=?";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            res =preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
