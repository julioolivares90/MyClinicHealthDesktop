package controllers;

import models.Producto;
import models.ProductoDao;
import models.ProductoForTable;
import utilidades.ModeloDeTabla;
import utilidades.PaginadorDeTabla;
import utilidades.ProveedorDeDatosPaginacion;
import views.Medicamentos;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductosController implements ActionListener , TableModelListener {
    private final Medicamentos view;

    private final ProductoDao model;
    private final PaginadorDeTabla<ProductoForTable> paginadorDeTabla;

    public ProductosController(Medicamentos view){
        this.view = view;
        model = new ProductoDao();
        //view.setComboBoxFilasPermitidas(new JComboBox());
        ProveedorDeDatosPaginacion<ProductoForTable> proveedorDeDatosPaginacion = crearProveedorDeDatos();

        paginadorDeTabla = new PaginadorDeTabla<ProductoForTable>(view.getTblMedicamentos(),proveedorDeDatosPaginacion,new int[]{5,10,20,50,75,100},10);
        paginadorDeTabla.crearListadoDeFilasPermitidas(view.getPaginadorPanel());

        view.setComboBoxFilasPermitidas(paginadorDeTabla.getComboBoxFilasPermitidas());
        paginadorDeTabla.setComboBoxFilasPermitidas(view.getComboBoxFilasPermitidas());
        paginadorDeTabla.actualizarBotonesPaginacion();

        events();

        //view.getComboBoxFilasPermitidas().setSelectedItem(Integer.parseInt("20"));

    }

    private  void events() {
        view.getComboBoxFilasPermitidas().addActionListener(this);
        view.getTblMedicamentos().getModel().addTableModelListener(this);
    }
    private ProveedorDeDatosPaginacion<ProductoForTable> crearProveedorDeDatos(){
        final List<ProductoForTable> lista = model.listaProductos();

        return new ProveedorDeDatosPaginacion<ProductoForTable>() {
            public int getTotalRowCount() {
                return lista.size();
            }

            public List<ProductoForTable> getRows(int startIndex, int endIndex) {
                return lista.subList(startIndex,endIndex);
            }
        };
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object evt = actionEvent.getSource();
        if (evt.equals(view.getComboBoxFilasPermitidas())){
            paginadorDeTabla.enventComboBox(view.getComboBoxFilasPermitidas());
        }

    }

    public void tableChanged(TableModelEvent tableModelEvent) {
        paginadorDeTabla.actualizarBotonesPaginacion();
    }
}
