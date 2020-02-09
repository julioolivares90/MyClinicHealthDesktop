package utilidades;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class PaginadorDeTabla <T> {
    private JTable table;
    private JTableProveedorDeDatos<T> proveedorDeDatos;
    private int[] arrayFilasPermitidas;
    private int filaPermitida;
    private JTablePaginator<T> paginator;
    private int paginaActual = 1;

    private JComboBox listaLimiteDeFilas;
    private JPanel panelPaginacionBotones;

    private int filasPermitidasPorDefecto;

    private final  int limiteBotonesPaginadores = 9;

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
        TableModel model  = table.getModel();
        if (!(model instanceof  JTablePaginator)){
            throw new IllegalArgumentException("Table model debe ser un subclase de objectdatamodel");
        }
        model =  model;
       /* try {
            this.paginator = (JTablePaginator<T>) this.table.getModel();
        }catch (Exception ex){
            System.out.println("Error metodo inicializarModeloDeTabla()"+ex.getMessage());
        }

        */
    }

    public  void crearListadoDeFilasPermitidas(JPanel panelPaginador){
        panelPaginacionBotones = new JPanel(new GridLayout(1,limiteBotonesPaginadores,3,3));
        panelPaginador.add(panelPaginacionBotones);
        if (arrayFilasPermitidas != null){
            Integer arrary [] = new Integer[arrayFilasPermitidas.length];
            for (int i =0;i<arrayFilasPermitidas.length;i++){
                arrary[i] = arrayFilasPermitidas[i];
            }
            listaLimiteDeFilas = new JComboBox(arrary);
            panelPaginador.add(Box.createHorizontalStrut(15));
            panelPaginador.add(new JLabel("Número de Filas: "));
            panelPaginador.add(listaLimiteDeFilas);
        }
    }
    public void enventComboBox(JComboBox pageComboBox){

        int currentPageStartRow = ((paginaActual - 1) * filasPermitidasPorDefecto) + 1;
        filasPermitidasPorDefecto = (Integer) pageComboBox.getSelectedItem();
        paginaActual = ((currentPageStartRow - 1) / filasPermitidasPorDefecto) + 1;
        paginar();
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
