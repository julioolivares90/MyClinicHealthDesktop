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

    public List<ProductoViewModel> showData(){
        List<ProductoViewModel> productos = new ArrayList<ProductoViewModel>();
        String query = "select id_producto, nombre ,costo_publico,cantidad from producto";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ProductoViewModel productoViewModel = new ProductoViewModel();
                productoViewModel.setId_producto(resultSet.getInt(1));
                productoViewModel.setNombre(resultSet.getString(2));
                productoViewModel.setCosto_publico(resultSet.getDouble(3));
                productoViewModel.setCantidad(resultSet.getInt(4));

                productos.add(productoViewModel);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return productos;
    }
    public ProductoViewModel findByName(String name){
        String query = "select id_producto, nombre ,costo_publico,cantidad from producto where nombre like '%' ? '%' ";
        ProductoViewModel productoViewModel = new ProductoViewModel();
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            resultSet = preparedStatement.executeQuery();

            resultSet.first();

            productoViewModel.setId_producto(resultSet.getInt(1));
            productoViewModel.setNombre(resultSet.getString(2));
            productoViewModel.setCosto_publico(resultSet.getDouble(3));
            productoViewModel.setCantidad(resultSet.getInt(4));

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return productoViewModel;
    }
    public List<ProductoForTable> listaProductos(){
        List<ProductoForTable> productos = new ArrayList<ProductoForTable>();
        String query = "select p.id_producto , p.nombre , p.costo_por_unidad , p.costo_publico,p.ganancia,p.cantidad, t.tipo_producto,pro.nombre_proveedor from producto as p " +
                "inner join tipo_producto as t on p.id_tipo_producto = t.id_tipo inner join proveedores as pro on p.id_proveedor = pro.id_proveedor";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ProductoForTable productoForTable = new ProductoForTable();
                productoForTable.setID(resultSet.getInt(1));
                productoForTable.setNombre(resultSet.getString(2));
                productoForTable.setCostoPoUnidad(resultSet.getDouble(3));
                productoForTable.setCostoPublico(resultSet.getDouble(4));
                productoForTable.setGanancia(resultSet.getDouble(5));
                productoForTable.setCantidad(resultSet.getInt(6));
                productoForTable.setTipoProducto(resultSet.getString(7));
                productoForTable.setProveedor(resultSet.getString(8));

                productos.add(productoForTable);

            }
        }catch (Exception ex){
           System.out.println(ex.getMessage());
        }
        return productos;
    }
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
                producto.setIdProveedor(resultSet.getInt(8));
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
        String query = "insert into producto (nombre,costo_por_unidad,costo_publico,ganancia,cantidad,id_tipo_producto,id_proveedor) value (?,?,?,?,?,?,?)";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,producto.getNombre());
            preparedStatement.setDouble(2,producto.getCostoPoUnidad());
            preparedStatement.setDouble(3,producto.getCostoPublico());
            preparedStatement.setDouble(4,producto.getGanancia());
            preparedStatement.setInt(5,producto.getCantidad());
            preparedStatement.setInt(6,producto.getIdTipoProducto());
            preparedStatement.setInt(7,producto.getIdProveedor());
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

    public int updateStock(int cantidad,int idProducto){
        int rs = 0;
        String sql = "update producto set cantidad=? where id_producto=? ";
        try {
            connection = conexion.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,cantidad);
            preparedStatement.setInt(2,idProducto);
            rs = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }
}
