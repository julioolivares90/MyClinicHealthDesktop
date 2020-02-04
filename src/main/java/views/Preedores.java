package views;

import models.Proveedor;
import models.ProveedorDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Preedores {
    private JPanel principal;
    private JTextField txtNombrePreedor;
    private JTextField txtCorreoProveedor;
    private JButton crearButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    private JTextField txtTelefonoProveedor;
    private JTable tblProveedores;

    public Preedores() {
        crearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

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
        limpiarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        tblProveedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
            }
        });
    }
    private void  cargarDatos(){
        ProveedorDao proveedorDao = new ProveedorDao();
        List<Proveedor> proveedors =  proveedorDao.listar();
        Object[] objects = new Object[4];
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("Nombre Proveedor");
        defaultTableModel.addColumn("Correo Proveedor");
        defaultTableModel.addColumn("Telenofo Proveedor");

        for (Proveedor proveedor: proveedors
             ) {
            objects[0] = proveedor.getId_proveedor();
            objects[1] = proveedor.getNombre();
            objects[2] = proveedor.getCorreo_preoveedor();
            objects[3]= proveedor.getTelefono_proveedor();
            defaultTableModel.addRow(objects);
        }
        tblProveedores.setModel(defaultTableModel);

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        tblProveedores = new JTable();
        cargarDatos();
    }
}
