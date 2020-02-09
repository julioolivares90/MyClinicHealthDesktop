package models;

public class ProductoViewModel {
   private int id_producto;
   private String  nombre ;
   private double costo_publico ;
   private int cantidad;

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto_publico() {
        return costo_publico;
    }

    public void setCosto_publico(double costo_publico) {
        this.costo_publico = costo_publico;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
