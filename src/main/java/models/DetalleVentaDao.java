package models;

import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDao implements Crud {
    Connection connection;
    Conexion conexion = new Conexion();

    PreparedStatement preparedStatement;
    ResultSet resultSet;
    public List listar() {
        List<DetalleVenta> detalleVentas = new ArrayList<DetalleVenta>();
        String sql = "select * from detalle_venta";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DetalleVenta detalleVenta = new DetalleVenta();
                detalleVenta.setId_venta(resultSet.getInt(1));
                detalleVenta.setId_venta(resultSet.getInt(2));
                detalleVenta.setId_producto(resultSet.getInt(3));
                detalleVenta.setCantidad(resultSet.getInt(4));
                detalleVenta.setPrecioVenta(resultSet.getDouble(5));

                detalleVentas.add(detalleVenta);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return detalleVentas;
    }

    public int add(Object object) {
        DetalleVenta detalleVenta = (DetalleVenta) object;
        int res =0;
        String query = "insert into detalle_venta (id_venta,id_producto,cantidad,precio_venta) value (?,?,?,?)";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,detalleVenta.getId_venta());
            preparedStatement.setInt(2,detalleVenta.getId_producto());
            preparedStatement.setInt(3,detalleVenta.getCantidad());
            preparedStatement.setDouble(4,detalleVenta.getPrecioVenta());
            res = preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int update(Object object) {
        DetalleVenta detalleVenta = (DetalleVenta) object;
        int res =0;
        String query = "update  detalle_venta set id_venta=?,id_producto=?,cantidad=?,precio_venta=? where id_detalle=?";
        try {
            connection = conexion.getConnection();

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,detalleVenta.getId_venta());
            preparedStatement.setInt(2,detalleVenta.getId_producto());
            preparedStatement.setInt(3,detalleVenta.getCantidad());

            preparedStatement.setDouble(4,detalleVenta.getPrecioVenta());
            res = preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int delete(int id) {
        int res = 0;
        String query = "delete from detalle_venta where id_detalle=?";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            res = preparedStatement.executeUpdate();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }
}
