package models;

//sirve para mostrar unva vista personalizada de los producto
public class ProductoForTable {

    private int ID;
    private String nombre;
    private Double costoPoUnidad;
    private Double costoPublico;
    private Double ganancia;
    private int cantidad;
    private String TipoProducto;
    private String Proveedor;

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

    public String getTipoProducto() {
        return TipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        TipoProducto = tipoProducto;
    }

    public String getProveedor() {
        return Proveedor;
    }

    public void setProveedor(String proveedor) {
        Proveedor = proveedor;
    }
}
