package models;

import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao  implements Crud{

    Connection connection;
    Conexion conexion = new Conexion();
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public List listar() {
        List<Producto> productos = new ArrayList<Producto>();
        String query = "select * from producto";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                Producto producto = new Producto();

                producto.setID(resultSet.getInt(1));
                producto.setNombre(resultSet.getString(2));
                producto.setCostoPoUnidad(resultSet.getDouble(3));
                producto.setCostoPublico(resultSet.getDouble(4));
                producto.setGanancia(resultSet.getDouble(5));
                producto.setCantidad(resultSet.getInt(6));
                producto.setIdTipoProducto(resultSet.getInt(7));
                productos.add(producto);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return productos;
    }

    public int add(Object object) {
        Producto producto = (Producto) object;
        int res =0;
        String query = "insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto) value (?,?,?,?,?,?)";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,producto.getNombre());
            preparedStatement.setDouble(2,producto.getCostoPoUnidad());
            preparedStatement.setDouble(3,producto.getCostoPublico());
            preparedStatement.setDouble(4,producto.getGanancia());
            preparedStatement.setInt(5,producto.getCantidad());
            preparedStatement.setInt(6,producto.getIdTipoProducto());
            res = preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int update(Object object) {
        Producto producto = new Producto();
        int res =0;
        String query = "update producto set nombre =? , costo_por_unidad =?,costo_publico =?,ganancia=?,cantidad =?,id_tipo_producto =? where id_producto =? ";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,producto.getNombre());
            preparedStatement.setDouble(2,producto.getCostoPoUnidad());
            preparedStatement.setDouble(3,producto.getCostoPublico());
            preparedStatement.setDouble(4,producto.getGanancia());
            preparedStatement.setInt(5,producto.getCantidad());
            preparedStatement.setInt(6,producto.getIdTipoProducto());
            preparedStatement.setInt(7,producto.getID());
            res = preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int delete(int id) {
        String query = "delete from producto where id=?";
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
}
