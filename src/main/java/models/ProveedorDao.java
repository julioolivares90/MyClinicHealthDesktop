package models;

import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDao implements Crud {
    Connection connection;
    Conexion conexion = new Conexion();
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    public List listar() {
        List<Proveedor> proveedors = new ArrayList<Proveedor>();
        String query = "select * from preveedores";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Proveedor proveedor = new Proveedor();
                proveedor.setId_proveedor(resultSet.getInt(1));
                proveedor.setNombre(resultSet.getString(2));
                proveedor.setCorreo_preoveedor(resultSet.getString(3));
                proveedor.setTelefono_proveedor(resultSet.getString(4));
                proveedors.add(proveedor);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return proveedors;
    }

    public int add(Object object) {
        Proveedor proveedor = (Proveedor) object;
        int res =0;
        String query = "insert into proveedores (nombre_proveedor,correo_proveedor,telefono_proveedor) value (?,?,?)";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,proveedor.getNombre());
            preparedStatement.setString(2, proveedor.getCorreo_preoveedor());
            preparedStatement.setString(3,proveedor.getTelefono_proveedor());
            res = preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int update(Object object) {
        Proveedor proveedor = (Proveedor) object;
        int res =0;
        String query = "update proveedores set nombre_proveedor=?,correo_proveedor=?,telefono_proveedor=? where id_proveedor=?";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,proveedor.getNombre());
            preparedStatement.setString(2, proveedor.getCorreo_preoveedor());
            preparedStatement.setString(3,proveedor.getTelefono_proveedor());
            preparedStatement.setInt(4,proveedor.getId_proveedor());
            res = preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public int delete(int id) {
        int res =0 ;
        String query = "delete from proveedores where id_proveedor =?";
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

    public List<ProveedorViewModel> getProveedoresComboBox(){
        List<ProveedorViewModel> proveedors = new ArrayList<ProveedorViewModel>();
        String query = "select id_proveedor , nombre_proveedor from proveedores";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ProveedorViewModel proveedorViewModel = new ProveedorViewModel();

                proveedorViewModel.setID(resultSet.getInt(1));
                proveedorViewModel.setNombre(resultSet.getString(2));

                proveedors.add(proveedorViewModel);
            }
        }catch (Exception e){

        }
        return proveedors;
    }
}
