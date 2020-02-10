package views;

import controllers.ProductosController;
import jdk.nashorn.internal.scripts.JO;
import models.Producto;
import models.ProductoDao;
import models.ProductoForTable;
import utilidades.Mensajes;
import utilidades.ModeloDeTabla;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Medicamentos extends JFrame {

    public JPanel principal;
    private JTable tblMedicamentos;
    private JButton actualizarButton;
    private JButton eliminarButton;

    public JComboBox getComboBoxFilasPermitidas() {
        return comboBoxFilasPermitidas;
    }

    public void setComboBoxFilasPermitidas(JComboBox comboBoxFilasPermitidas) {
        this.comboBoxFilasPermitidas = comboBoxFilasPermitidas;
    }

    private JComboBox comboBoxFilasPermitidas;

    public JPanel getPaginadorPanel() {
        return paginadorPanel;
    }

    private JPanel paginadorPanel;
    private JComboBox pageJComboBox;


    int fila;

    Producto producto = new Producto();
    DefaultTableModel tableModel;
    private final ProductosController controller;


    public JTable getTblMedicamentos() {
        return tblMedicamentos;
    }

    public Medicamentos() {
        this.setContentPane(principal);
        this.setLocationRelativeTo(null);

        controller = new ProductosController(this);
        tblMedicamentos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ProductoDao productoDao = new ProductoDao();
                if (fila == -1){
                    Mensajes.SeleccionaUnaFila();
                }else{

                    int rs = productoDao.update(producto);

                    if (rs > 0){
                        JOptionPane.showMessageDialog(null,"Actualizado con exito !!");
                        //fillTabla();
                    }else {
                        JOptionPane.showMessageDialog(null,"Ocurrio un error ");
                    }
                }

            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ProductoDao productoDao = new ProductoDao();
                if (fila == -1){
                    Mensajes.SeleccionaUnaFila();
                }else {
                    int rs = productoDao.delete(producto.getID());
                    if (rs>0){
                        JOptionPane.showMessageDialog(null,"Eliminado con exito");
                        //fillTabla();
                    }else {
                        JOptionPane.showMessageDialog(null,"Ocurrio un error");
                    }
                }
            }
        });
        tblMedicamentos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                fila = tblMedicamentos.getSelectedRow();
                if(fila == -1){
                    Mensajes.SeleccionaUnaFila();
                }
                int id = Integer.parseInt(tblMedicamentos.getValueAt(fila,0).toString());
                String nombre = tblMedicamentos.getValueAt(fila,1).toString();
                double costoPorUnidad = Double.parseDouble(tblMedicamentos.getValueAt(fila,2).toString());
                double costoPublico = Double.parseDouble(tblMedicamentos.getValueAt(fila,3).toString());
                double ganancia = Double.parseDouble(tblMedicamentos.getValueAt(fila,4).toString());
                int cantidad = Integer.parseInt(tblMedicamentos.getValueAt(fila,5).toString());
                int tipoProducto = Integer.parseInt(tblMedicamentos.getValueAt(fila,6).toString());
                int proveedor = Integer.parseInt(tblMedicamentos.getValueAt(fila,7).toString());

                producto.setID(id);
                producto.setNombre(nombre);
                producto.setCostoPoUnidad(costoPorUnidad);
                producto.setCostoPublico(costoPublico);
                producto.setGanancia(ganancia);
                producto.setCantidad(cantidad);
                producto.setIdTipoProducto(tipoProducto);
                producto.setIdProveedor(proveedor);


            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        tblMedicamentos = new JTable();
        initTable();
        //fillTabla();
    }

    private void initTable(){
        this.tblMedicamentos.setModel(crearModeloDeTabla());
    }
    private TableModel crearModeloDeTabla() {

        return new ModeloDeTabla<ProductoForTable>() {

            public Object getValueAt(ProductoForTable producto, int columna) {
                switch (columna){
                    case 0:
                        return producto.getID();
                    case 1:
                        return producto.getNombre();
                    case 2:
                        return producto.getCostoPoUnidad();
                    case 3:
                        return producto.getCostoPublico();
                    case 4:
                        return producto.getGanancia();
                    case 5:
                        return producto.getCantidad();
                    case 6:
                        return producto.getTipoProducto();
                    case 7:
                        return producto.getProveedor();
                }
                return null;
            }

            public int getColumnCount() {
                return 8;
            }

            public String getColumnName(int columna) {
                switch (columna){
                    case 0:
                        return "ID";
                    case 1:
                        return "Nombre";
                    case 2:
                        return "Costo por unidad" ;
                    case 3:
                        return "Costo publico" ;
                    case 4:
                        return "Ganancia";
                    case 5:
                        return "Cantidad" ;
                    case 6:
                        return "Tipo producto";
                    case 7:
                        return "Proveedor";
                }
                return null;
            }
        };
    }

    private void fillTabla(){
        ProductoDao productoDao = new ProductoDao();
        List<Producto>  productos = productoDao.listar();

        tableModel = new DefaultTableModel(0,0);
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Costo por unidad");
        tableModel.addColumn("Costro publico");
        tableModel.addColumn("Ganancia");
        tableModel.addColumn("cantidad");
        tableModel.addColumn("tipo producto");
        tableModel.addColumn("proveedor");

        Object[] objects = new Object[8];
        for (Producto p:
             productos) {
            objects[0] = p.getID();
            objects[1] =p.getNombre();
            objects[2] =p.getCostoPoUnidad();
            objects[3] =p.getCostoPublico();
            objects[4] =p.getGanancia();
            objects[5] =p.getCantidad();
            objects[6] =p.getIdTipoProducto();
            objects[7] = p.getIdProveedor();

            tableModel.addRow(objects);
        }
        tblMedicamentos.setModel(tableModel);
    }
}
