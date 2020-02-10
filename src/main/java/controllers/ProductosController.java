package controllers;

import models.Producto;
import models.ProductoDao;
import models.ProductoForTable;
import utilidades.ModeloDeTabla;
import utilidades.ProveedorDeDatosPaginacion;
import views.Medicamentos;

import java.util.List;

public class ProductosController {
    private final Medicamentos view;

    private final ProductoDao model;

    public ProductosController(Medicamentos view){
        this.view = view;
        model = new ProductoDao();

        ProveedorDeDatosPaginacion<ProductoForTable> proveedorDeDatosPaginacion = crearProveedorDeDatos();
        ModeloDeTabla<ProductoForTable> modeloDeTabla =(ModeloDeTabla<ProductoForTable>) view.getTblMedicamentos().getModel();
        List<ProductoForTable> lista = proveedorDeDatosPaginacion.getRows(0,10);
        modeloDeTabla.setLista(lista);
        events();

    }

    private void events() {
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
}
