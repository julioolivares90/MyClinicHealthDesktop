package controllers;

import models.ProductoForTable;
import models.Tipo;
import models.TipoDao;
import utilidades.PaginadorDeTabla;
import utilidades.ProveedorDeDatosPaginacion;
import views.NuevaCategoria;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CategoriasController implements ActionListener, TableModelListener {
    private NuevaCategoria view;
    private final TipoDao model;
    private final PaginadorDeTabla<Tipo> paginadorDeTabla;
    public CategoriasController(NuevaCategoria vista){
        this.view = vista;
        this.model = new TipoDao();

        //preparar paginador
        ProveedorDeDatosPaginacion<Tipo> proveedorDeDatosPaginacion = crearProveedorDeDatos();
        this.paginadorDeTabla = new PaginadorDeTabla<Tipo>(view.getTblTipos(),proveedorDeDatosPaginacion,new int[]{5,10,20,50,75,100},10);
        paginadorDeTabla.crearListadoDeFilasPermitidas(view.getPaginadorPanel());
        view.setComboBoxFilasPermitidas(paginadorDeTabla.getComboBoxFilasPermitidas());
        paginadorDeTabla.setComboBoxFilasPermitidas(view.getComboBoxFilasPermitidas());
        paginadorDeTabla.actualizarBotonesPaginacion();
        events();
    }

    private void events() {
        this.view.getComboBoxFilasPermitidas().addActionListener(this);
        this.view.getTblTipos().getModel().addTableModelListener(this);
    }

    private ProveedorDeDatosPaginacion<Tipo> crearProveedorDeDatos(){

        final List<Tipo> lista = model.listar();

        return new ProveedorDeDatosPaginacion<Tipo>() {
            public int getTotalRowCount() {
                return lista.size();
            }

            public List<Tipo> getRows(int startIndex, int endIndex) {
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
