package utilidades;

import javax.swing.*;
import java.util.List;

public class PaginadorDeTabla <T> {
    private JTable table;
    private JTableProveedorDeDatos<T> proveedorDeDatos;
    private int[] arrayFilasPermitidas;
    private int filaPermitida;
    private JTablePaginator<T> paginator;
    private int paginaActual = 1;

    public PaginadorDeTabla(JTable table,JTableProveedorDeDatos<T> proveedorDeDatos,int[] arrayFilasPermitidas,int filaPermitida){
        this.table= table;
        this.proveedorDeDatos = proveedorDeDatos;
        this.arrayFilasPermitidas = arrayFilasPermitidas;
        this.filaPermitida = filaPermitida;
        init();
    }

    private void init() {
        inicializarModeloDeTabla();
        paginar();
    }

    private void inicializarModeloDeTabla(){
        try {
            this.paginator = (JTablePaginator<T>) this.table.getModel();
        }catch (Exception ex){
            System.out.println("Error metodo inicializarModeloDeTabla()"+ex.getMessage());
        }
    }

    private void paginar(){
        int inicio = (paginaActual -1) * filaPermitida;
        int finalizacion= inicio + filaPermitida;
        if (finalizacion > proveedorDeDatos.getTotalRowCount()){
            finalizacion = proveedorDeDatos.getTotalRowCount();
        }
        List<T> lista = proveedorDeDatos.getRows(inicio,finalizacion);
        paginator.setLisadoDeFilas(lista);
        paginator.fireTableDataChanged();
    }
}
