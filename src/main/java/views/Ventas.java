package views;

import controllers.VentasController;
import models.VentaViewModel;
import utilidades.ModeloDeTabla;

import javax.swing.*;
import javax.swing.table.TableModel;

public class Ventas extends JFrame {
    private JPanel principal;
    private JTable tblVentas;
    private JPanel paginacion;
    private JComboBox comboBoxFilasPermitidas;

    private final VentasController controller;

    Ventas(){

        controller = new VentasController(this);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        tblVentas = new JTable();
        principal = new JPanel();
        paginacion = new JPanel();
        comboBoxFilasPermitidas = new JComboBox();
        initTable();
    }



    //inicializar tabla
    private void initTable() {
        this.tblVentas.setModel(crearModeloDeTabla());
    }

    private TableModel crearModeloDeTabla() {
        return new ModeloDeTabla<VentaViewModel>() {
            @Override
            public Object getValueAt(VentaViewModel venta, int columna) {
                switch (columna){
                    case 0:
                        return venta.getId_venta();
                    case 1:
                        return venta.getFecha_venta();
                    case 2:
                        return venta.getMonto();
                    case 3:
                        return venta.getDescuento();
                    case 4:
                        return venta.getNombreVendedor();
                }
                return null;
            }

            @Override
            public int getColumnCount() {
                return 5;
            }

            @Override
            public String getColumnName(int columna) {
                switch (columna){
                    case 0:
                        return "ID";
                    case 1:
                        return "Fecha_venta";
                    case 2:
                        return "Monto";
                    case 3:
                        return "Descuento";
                    case 4:
                        return "Usuario";
                }
                return null;
            }
        };
    }

    //setter y getter
    public JComboBox getComboBoxFilasPermitidas() {
        return comboBoxFilasPermitidas;
    }

    public void setComboBoxFilasPermitidas(JComboBox comboBoxFilasPermitidas) {
        this.comboBoxFilasPermitidas = comboBoxFilasPermitidas;
    }

    public JPanel getPaginacion() {
        return paginacion;
    }

    public JTable getTblVentas() {
        return tblVentas;
    }

    public void setTblVentas(JTable tblVentas) {
        this.tblVentas = tblVentas;
    }

    public JPanel getPrincipal() {
        return principal;
    }
}
