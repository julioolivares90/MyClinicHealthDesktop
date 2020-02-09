package views;

import models.Producto;
import models.ProductoDao;

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


    DefaultTableModel tableModel;
    public Medicamentos() {

        tblMedicamentos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        tblMedicamentos = new JTable();
        fillTabla();
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
