package models;

import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class VentaDao  implements Crud{
    Connection connection;
    Conexion conexion = new Conexion();

    PreparedStatement preparedStatement;
    ResultSet resultSet;


    public List listar() {
        List<Venta> ventas = new ArrayList<Venta>();
        String query = "select v.id_venta, v.fecha_venta,v.monto,v.descuento, u.nombre from ventas as v inner join usuarios as u on u.id = v.id_usuario";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                VentaViewModel venta = new VentaViewModel();
                venta.setId_venta(resultSet.getInt(1));
                venta.setFecha_venta(resultSet.getDate(2));
                venta.setMonto(resultSet.getDouble(3 ));
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
            preparedStatement.setDate(1,Date.valueOf(venta.getFecha_venta().toString()));
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
}
