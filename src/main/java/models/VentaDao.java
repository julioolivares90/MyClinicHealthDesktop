package models;

import conexion.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDao  implements Crud{
    Connection connection;
    Conexion conexion = new Conexion();

    PreparedStatement preparedStatement;
    ResultSet resultSet;


    public List<VentaViewModel> listOfSales(){
        List<VentaViewModel> ventas = new ArrayList<>();
        String query = "select v.id_venta, v.fecha_venta,v.monto,v.descuento, u.nombre from ventas as v inner join usuarios as u on u.id = v.id_usuario";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                VentaViewModel venta = new VentaViewModel();
                venta.setId_venta(resultSet.getInt(1));
                venta.setFecha_venta(resultSet.getString(2));
                venta.setMonto(resultSet.getDouble(3 ));
                venta.setDescuento(resultSet.getInt(4));
                venta.setNombreVendedor(resultSet.getString(5));
                ventas.add(venta);
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return ventas;
    }
    public List listar() {
        List<VentaViewModel> ventas = new ArrayList<VentaViewModel>();
        String query = "select v.id_venta, v.fecha_venta,v.monto,v.descuento, u.nombre from ventas as v inner join usuarios as u on u.id = v.id_usuario";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                VentaViewModel venta = new VentaViewModel();
                venta.setId_venta(resultSet.getInt(1));
                venta.setFecha_venta(resultSet.getDate(2).toString());
                venta.setMonto(resultSet.getDouble(3 ));
                venta.setNombreVendedor(resultSet.getString(4));
                ventas.add(venta);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ventas;
    }

    public int add(Object object) {
        Venta venta = (Venta) object;
        String query = "insert into ventas (fecha_venta,monto,descuento,id_usuario) value(?,?,?,?)";
        int rs = 0;
        try {

            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,venta.getFecha_venta());
            preparedStatement.setDouble(2,venta.getMonto());
            preparedStatement.setInt(3,venta.getDescuento());
            preparedStatement.setInt(4,venta.getId_usuario());
            rs = preparedStatement.executeUpdate();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public int update(Object object) {
        Venta venta = (Venta) object;
        String query = "update  ventas  set monto=?,descuento=?,id_usuario=? where id_venta=?";
        int rs = 0;
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1,venta.getMonto());
            preparedStatement.setInt(2,venta.getDescuento());
            preparedStatement.setInt(3,venta.getId_usuario());

            rs = preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public int delete(int id) {
        String query ="delete from ventas where id_venta = ?";
        int res = 0;
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

    public  String IdVentas(){
        String id ="";
        String query ="select MAX(id_venta) from ventas";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                id = resultSet.getString(1);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return id;
    }

    public int GuardarVenta(Venta venta){
        int rs =0;
        String query = "insert into fecha_venta,monto,descuento,id_usuario";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);

        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
}
