package models;

public class Proveedor {
    private int id_proveedor;
    private String nombre;
    private  String correo_preoveedor;
    private String telefono_proveedor;

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo_preoveedor() {
        return correo_preoveedor;
    }

    public void setCorreo_preoveedor(String correo_preoveedor) {
        this.correo_preoveedor = correo_preoveedor;
    }

    public String getTelefono_proveedor() {
        return telefono_proveedor;
    }

    public void setTelefono_proveedor(String telefono_proveedor) {
        this.telefono_proveedor = telefono_proveedor;
    }
}
