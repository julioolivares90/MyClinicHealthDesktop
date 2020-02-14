package controllers;

import models.Venta;
import models.VentaDao;
import models.VentaViewModel;
import utilidades.PaginadorDeTabla;
import utilidades.ProveedorDeDatosPaginacion;
import views.Ventas;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentasController implements ActionListener, TableModelListener {
    private final Ventas view;
    private final VentaDao model;

    private final PaginadorDeTabla<VentaViewModel> paginadorDeTabla;

    public VentasController(Ventas view){
        this.view = view;
        model = new VentaDao();
        ProveedorDeDatosPaginacion<VentaViewModel> proveedorDeDatosPaginacion = crearProveedorDeDatos();
        paginadorDeTabla = new PaginadorDeTabla<>(view.getTblVentas(),proveedorDeDatosPaginacion,new int[]{5,10,20,50,75,100},10);


        ////creo el listado de las finlas
        paginadorDeTabla.crearListadoDeFilasPermitidas(view.getPaginacion());

        //asigno el valor del combobox de la paginacion al combobox de la vista
        view.setComboBoxFilasPermitidas(paginadorDeTabla.getComboBoxFilasPermitidas());
        paginadorDeTabla.setComboBoxFilasPermitidas(view.getComboBoxFilasPermitidas());
        paginadorDeTabla.actualizarBotonesPaginacion();
        events();
    }

    private void events() {
        view.getComboBoxFilasPermitidas().addActionListener(this);
        view.getTblVentas().getModel().addTableModelListener(this);
    }

    private ProveedorDeDatosPaginacion<VentaViewModel> crearProveedorDeDatos() {
        final List<VentaViewModel> lista = model.listOfSales();
        return new ProveedorDeDatosPaginacion<VentaViewModel>() {
            @Override
            public int getTotalRowCount() {
                return lista.size();
            }

            @Override
            public List<VentaViewModel> getRows(int startIndex, int endIndex) {
                return lista.subList(startIndex,endIndex);
            }
        };
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object evt = actionEvent.getSource();
        if (evt.equals(view.getComboBoxFilasPermitidas())){
            paginadorDeTabla.enventComboBox(view.getComboBoxFilasPermitidas());
        }
    }

    @Override
    public void tableChanged(TableModelEvent tableModelEvent) {
        paginadorDeTabla.actualizarBotonesPaginacion();
    }
}
