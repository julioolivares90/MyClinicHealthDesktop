package utilidades;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PaginadorDeTabla <T> {
    private JTable tabla;
    private ProveedorDeDatosPaginacion<T> proveedorDeDatosPaginacion;
    private int [] arrayFilasPermitidas;
    private int filaPermitida;
    private ModeloDeTabla<T> modeloDeTabla;
    private int paginaActual=1;

    private JComboBox comboBoxFilasPermitidas;
    private JPanel panelPaginacionBotones;
    private int limiteBotonesPaginadores =9;

    public PaginadorDeTabla(JTable tabla,ProveedorDeDatosPaginacion<T> proveedorDeDatosPaginacion,
                            int[] arrayFilasPermitidas,int filaPermitida){
        this.tabla= tabla;
        this.proveedorDeDatosPaginacion = proveedorDeDatosPaginacion;
        this.arrayFilasPermitidas = arrayFilasPermitidas;
        this.filaPermitida = filaPermitida;

        init();
    }

    private void init() {
        inicializarModeloDeTabla();
        paginar();

    }

    private void inicializarModeloDeTabla(){
        try{
            this.modeloDeTabla = (ModeloDeTabla<T>) tabla.getModel();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    public   void crearListadoDeFilasPermitidas(JPanel panelPaginador){
        panelPaginacionBotones = new JPanel(new GridLayout(1,limiteBotonesPaginadores,3,3));
        panelPaginador.add(panelPaginacionBotones);
        if (arrayFilasPermitidas != null){
            Integer[] array = new Integer[arrayFilasPermitidas.length];
            for (int i = 0; i < arrayFilasPermitidas.length; i++) {
                array[i] = arrayFilasPermitidas[i];
            }
            comboBoxFilasPermitidas = new JComboBox(array);
            panelPaginador.add(Box.createHorizontalStrut(15));
            panelPaginador.add(new JLabel("Numero de filas"));
            panelPaginador.add(comboBoxFilasPermitidas);
        }
    }
    public void enventComboBox(JComboBox pageComboBox){
        int filaInicialPagina = ((paginaActual-1) * filaPermitida) + 1;
        filaPermitida = (Integer) pageComboBox.getSelectedItem();
        paginaActual = ((filaInicialPagina-1) / filaPermitida) + 1;
        paginar();
    }

    public void  actualizarBotonesPaginacion(){
        panelPaginacionBotones.removeAll();
        int totalFilas = proveedorDeDatosPaginacion.getTotalRowCount();
        int paginas =(int) Math.ceil((double) totalFilas/filaPermitida);
        if (paginas > limiteBotonesPaginadores){
            agregarBotonesPaginacion(panelPaginacionBotones,1);
            if (paginaActual <= (limiteBotonesPaginadores+1)/2){
                agregarRangoBotonesPaginacion(panelPaginacionBotones,2,limiteBotonesPaginadores-2);
                panelPaginacionBotones.add(crearElipses());
                agregarBotonesPaginacion(panelPaginacionBotones,paginas);
            }else if (paginaActual > (paginas-((limiteBotonesPaginadores+1)/2))){
                panelPaginacionBotones.add(crearElipses());
                agregarRangoBotonesPaginacion(panelPaginacionBotones,paginas-limiteBotonesPaginadores+3,paginas);
            }else {
                panelPaginacionBotones.add(crearElipses());
                int inicio = paginaActual - (limiteBotonesPaginadores -4) / 2;
                int finalizacion=inicio+limiteBotonesPaginadores-5;
                agregarRangoBotonesPaginacion(panelPaginacionBotones,inicio,finalizacion);
                panelPaginacionBotones.add(crearElipses());
                agregarBotonesPaginacion(panelPaginacionBotones,paginas);
            }
        }else {
            agregarRangoBotonesPaginacion(panelPaginacionBotones,1,paginas);
        }
        panelPaginacionBotones.getParent().validate();
        panelPaginacionBotones.getParent().repaint();
    }
    private JLabel crearElipses(){
        return new JLabel("...",SwingConstants.CENTER);
    }
    private void agregarRangoBotonesPaginacion(JPanel panelPadre , int inicio,int finalizacion){
        int init = inicio;
        for (inicio = init;inicio <= finalizacion;inicio++){
            agregarBotonesPaginacion(panelPadre,inicio);
        }
    }
    private void  agregarBotonesPaginacion(JPanel panelPadre,int numeroPagina){
        JToggleButton toggleButton = new JToggleButton(Integer.toString(numeroPagina));
        toggleButton.setMargin(new Insets(1,3,1,3));
        panelPadre.add(toggleButton);

        //veficar en pagina se encuentra
        if (numeroPagina == paginaActual){
            toggleButton.setSelected(true);
        }
        toggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                paginaActual = Integer.parseInt(actionEvent.getActionCommand());
                paginar();
            }
        });
    }
    private void paginar(){
        int inicio = (paginaActual-1) * filaPermitida; //pagina actual es uno al restarle 1 tendria valor de cero y al multiplicarlo por la fila permitida su valor siempre es cero
        int finalizacion =inicio + filaPermitida; // 0 + 10
        if (finalizacion > proveedorDeDatosPaginacion.getTotalRowCount()){
            finalizacion = proveedorDeDatosPaginacion.getTotalRowCount();
        }
        List<T> lista = proveedorDeDatosPaginacion.getRows(inicio,finalizacion);
        modeloDeTabla.setLista(lista);
        modeloDeTabla.fireTableDataChanged(); //permite saber si se han realizado cambios en el modelo de tabla
    }

    public JComboBox getComboBoxFilasPermitidas() {
        return comboBoxFilasPermitidas;
    }

    public void setComboBoxFilasPermitidas(JComboBox comboBoxFilasPermitidas) {
        this.comboBoxFilasPermitidas = comboBoxFilasPermitidas;
    }
}
