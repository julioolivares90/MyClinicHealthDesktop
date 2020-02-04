package views;

import Constantes.Constantes;
import conexion.Conexion;
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

public class Preedores  extends  JFrame{
    private JPanel principal;
    private JTextField txtNombrePreedor;
    private JTextField txtCorreoProveedor;
    private JButton crearButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    private JTextField txtTelefonoProveedor;
    private JTable tblProveedores;
    private JTextField txtID;

    public Preedores() {
        crearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String nombre = txtNombrePreedor.getText();
                String correo = txtCorreoProveedor.getText();
                String telefono = txtTelefonoProveedor.getText();

                if (nombre.equals("")){
                    JOptionPane.showMessageDialog(null, Constantes.CAMPO_VACIO);
                    txtNombrePreedor.requestFocus(true);
                }else if (correo.equals("")){
                    JOptionPane.showMessageDialog(null,Constantes.CAMPO_VACIO);
                    txtCorreoProveedor.requestFocus(true);
                }else  if (telefono.equals("")){
                    JOptionPane.showMessageDialog(null,Constantes.CAMPO_VACIO);
                    txtTelefonoProveedor.requestFocus(true);
                }else {
                    Proveedor proveedor = new Proveedor();
                    proveedor.setNombre(nombre);
                    proveedor.setCorreo_preoveedor(correo);
                    proveedor.setTelefono_proveedor(telefono);

                    ProveedorDao proveedorDao = new ProveedorDao();
                    int res = proveedorDao.add(proveedor);
                    if (res > 0){
                        JOptionPane.showMessageDialog(null,Constantes.AGREGADO_CON_EXITO);
                        limpiar();
                        cargarDatos();
                    }else{
                        JOptionPane.showMessageDialog(null,Constantes.NOSE_PUDO_AGREGAR);
                        limpiar();
                    }
                }
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String nombre = txtNombrePreedor.getText();
                String correo = txtCorreoProveedor.getText();
                String telefono = txtTelefonoProveedor.getText();
                int id = Integer.parseInt(txtID.getText());
                if (id <= 0){
                    JOptionPane.showMessageDialog(null,Constantes.SELECCIONA_FILA);
                    return;
                }else {
                    Proveedor proveedor = new Proveedor();
                    proveedor.setId_proveedor(id);
                    proveedor.setNombre(nombre);
                    proveedor.setCorreo_preoveedor(correo);
                    proveedor.setTelefono_proveedor(telefono);

                    ProveedorDao proveedorDao = new ProveedorDao();
                    int res = proveedorDao.add(proveedor);
                    if (res > 0){
                        JOptionPane.showMessageDialog(null,Constantes.AGREGADO_CON_EXITO);
                        limpiar();
                        cargarDatos();
                    }else{
                        JOptionPane.showMessageDialog(null,Constantes.NOSE_PUDO_AGREGAR);
                        limpiar();
                    }
                }

            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ProveedorDao proveedorDao = new ProveedorDao();
                int res = proveedorDao.delete(Integer.parseInt(txtID.getText()));
                if (res >0){
                    JOptionPane.showMessageDialog(null,"Eliminado con exito!!");
                    cargarDatos();

                }else {
                    JOptionPane.showMessageDialog(null,"ocurrio un error!");
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
               limpiar();
            }
        });


        tblProveedores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                int fila = tblProveedores.getSelectedRow();
                if (fila == -1){
                    JOptionPane.showMessageDialog(null,"Deve Seleccionar una fila");
                }else {
                    int id = Integer.parseInt(tblProveedores.getValueAt(fila,0).toString());
                    String nombreProveedor = tblProveedores.getValueAt(fila,1).toString();
                    String correoProveedor = tblProveedores.getValueAt(fila,2).toString();
                    String telefono = tblProveedores.getValueAt(fila,3).toString();

                    txtID.setText(String.valueOf(id));
                    txtNombrePreedor.setText(nombreProveedor);
                    txtCorreoProveedor.setText(correoProveedor);
                    txtTelefonoProveedor.setText(telefono);

                }
            }
        });
    }
    private void limpiar(){
        txtID.setText("");
        txtNombrePreedor.setText("");
        txtCorreoProveedor.setText("");
        txtTelefonoProveedor.setText("");
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
