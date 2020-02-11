package models;

import Constantes.Constantes;
import conexion.Conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoDao implements Crud {
    Connection connection;
    Conexion conexion = new Conexion();
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    public List listar() {
        List<Tipo> tipos = new ArrayList<Tipo>();
        String sql = "select * from tipo_producto";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Tipo t = new Tipo();
                t.setId(resultSet.getInt(1));
                t.setTipoProducto(resultSet.getString(2));
                t.setDescripcion(resultSet.getString(3));

                tipos.add(t);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return tipos;
    }

    public int add(Object object) {
        Tipo tipo = (Tipo) object;
        int res=0;
        String query = "insert into tipo_producto (tipo_producto,descripcion) value(?,?)";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,tipo.getTipoProducto());
            preparedStatement.setString(2,tipo.getDescripcion());
            res = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int update(Object object) {
        Tipo tipo = (Tipo) object;
        int res=0;
        String query = "update tipo_producto set tipo_producto =? , descripcion=? where id_tipo = ?";

        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,tipo.getTipoProducto());
            preparedStatement.setString(2,tipo.getDescripcion());
            preparedStatement.setInt(3,tipo.getId());

            res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int delete(int id) {

        String query ="delete from tipo_producto where id_tipo=?";
        int rs=0;
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            rs =preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public List<Tipo> getTiposComboBox(){
        List<Tipo> tipos = new ArrayList<Tipo>();
        String query = "select id_tipo , tipo_producto from tipo_producto";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Tipo tipo = new Tipo();
                tipo.setId(resultSet.getInt(1));
                tipo.setTipoProducto(resultSet.getString(2));

                tipos.add(tipo);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return tipos;
    }
}
