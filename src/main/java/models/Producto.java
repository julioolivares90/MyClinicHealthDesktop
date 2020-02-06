package models;

public class Producto {
    private int ID;
    private String nombre;
    private Double costoPoUnidad;
    private Double costoPublico;
    private Double ganancia;
    private int cantidad;
    private int IdTipoProducto;
    private int idProveedor;

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCostoPoUnidad() {
        return costoPoUnidad;
    }

    public void setCostoPoUnidad(Double costoPoUnidad) {
        this.costoPoUnidad = costoPoUnidad;
    }

    public Double getCostoPublico() {
        return costoPublico;
    }

    public void setCostoPublico(Double costoPublico) {
        this.costoPublico = costoPublico;
    }

    public Double getGanancia() {
        return ganancia;
    }

    public void setGanancia(Double ganancia) {
        this.ganancia = ganancia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdTipoProducto() {
        return IdTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        IdTipoProducto = idTipoProducto;
    }
}
