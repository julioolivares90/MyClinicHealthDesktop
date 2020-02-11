package views;

import Constantes.Constantes;
import components.EditText;

import controllers.CategoriasController;
import models.Tipo;
import models.TipoDao;
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


public class NuevaCategoria  extends JFrame{
    TipoDao tipoDao = new TipoDao();

    public JPanel principal;
    private JTextField txtTipo;
    private JButton crearButton;
    private JTextArea txtDescripcion;

    public JComboBox getComboBoxFilasPermitidas() {
        return comboBoxFilasPermitidas;
    }

    public void setComboBoxFilasPermitidas(JComboBox comboBoxFilasPermitidas) {
        this.comboBoxFilasPermitidas = comboBoxFilasPermitidas;
    }

    private JComboBox comboBoxFilasPermitidas;

    public JTable getTblTipos() {
        return tblTipos;
    }

    private JTable tblTipos;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JTextField txtId;

    public JPanel getPaginadorPanel() {
        return paginadorPanel;
    }

    private JPanel paginadorPanel;
    private JTextField txtNombre;

    private CategoriasController controller;
    DefaultTableModel tableModel;
    public NuevaCategoria() {
        //cargarDatos();
        this.controller = new CategoriasController(this);

        crearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                String nombre = txtTipo.getText();
                String descripcion =  txtDescripcion.getText();

                if (nombre.equals("")){
                    JOptionPane.showMessageDialog(null, Constantes.CAMPO_VACIO);
                    txtNombre.requestFocus();
                }else  if (descripcion.equals("")){
                    JOptionPane.showMessageDialog(null,Constantes.CAMPO_VACIO);
                    txtDescripcion.requestFocus();
                }
                else {
                    Tipo tipo = new Tipo();
                    tipo.setTipoProducto(nombre);
                    tipo.setDescripcion(descripcion);
                    TipoDao tipoDao = new TipoDao();
                    int res = tipoDao.add(tipo);

                    if (res > 0){
                        JOptionPane.showMessageDialog(null,Constantes.AGREGADO_CON_EXITO);
                        limpiar();
                        //cargarDatos();
                    }else{
                        JOptionPane.showMessageDialog(null,Constantes.NOSE_PUDO_AGREGAR);
                        limpiar();
                    }
                }
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Tipo tipo = new Tipo();
                TipoDao tipoDao = new TipoDao();
                int id = Integer.parseInt(txtId.getText());
                if (id <= 0){
                    JOptionPane.showMessageDialog(null,Constantes.SELECCIONA_FILA);
                }else {
                    tipo.setId(id);
                    tipo.setTipoProducto(txtTipo.getText());
                    tipo.setDescripcion(txtDescripcion.getText());

                    int rs = tipoDao.update(tipo);
                    if (rs > 0){
                        JOptionPane.showMessageDialog(null,"Actualizado con exito");
                        //cargarDatos();
                        limpiar();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Ocuurrio un error al actualizar");
                    }
                }

            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                TipoDao tipoDao = new TipoDao();
                int id = Integer.parseInt(txtId.getText());
                if (id <= 0){
                    JOptionPane.showMessageDialog(null,Constantes.SELECCIONA_FILA);
                }else  {
                    int rs = tipoDao.delete(id);
                    if (rs > 0 ){
                        JOptionPane.showMessageDialog(null,"Eliminado con exito!!");
                        //cargarDatos();
                        limpiar();
                    }else {
                        JOptionPane.showMessageDialog(null,"Ocurrio un error!!");
                    }
                }

            }
        });
        tblTipos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                int fila = tblTipos.getSelectedRow();
                if (fila == -1){
                    Mensajes.SeleccionaUnaFila();
                }else {
                    int id = Integer.parseInt(tblTipos.getValueAt(fila,0).toString());
                    String nombre = tblTipos.getValueAt(fila,1).toString();
                    String descripcion = tblTipos.getValueAt(fila,2).toString();

                    txtId.setText(String.valueOf(id));
                    txtTipo.setText(nombre);
                    txtDescripcion.setText(descripcion);

                }
            }
        });
    }

    private void limpiar(){
        txtTipo.setText("");
        txtDescripcion.setText("");
    }
    private void cargarDatos(){
        tipoDao = new TipoDao();
        List<Tipo> datos = tipoDao.listar();

        //tableModel = (DefaultTableModel) tblTipos.getModel();
        Object[] objects = new Object[3];
        String[] header = {
                "ID",
                "tipo",
                "descripcion"
        };
        DefaultTableModel tableModel = new DefaultTableModel(0,0);

        tableModel.addColumn("ID");
        tableModel.addColumn("Tipo");
        tableModel.addColumn("Descripcion");
        //tableModel = new DefaultTableModel(0,3);

        for (Tipo dato : datos) {
            objects[0] = dato.getId();
            objects[1] = dato.getTipoProducto();
            objects[2] = dato.getDescripcion();

            tableModel.addRow(objects);
        }

        tblTipos.setModel(tableModel);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        txtNombre = new EditText();
        tblTipos = new JTable();
        initTable();
        //cargarDatos();
    }

    private void initTable(){
        this.tblTipos.setModel(crearModeloDeTabla());
    }

    private TableModel crearModeloDeTabla() {
        return new ModeloDeTabla<Tipo>() {

            public Object getValueAt(Tipo tipo, int columna) {
                switch (columna){
                    case 0:
                        return tipo.getId();
                    case 1:
                        return tipo.getTipoProducto();
                    case 2:
                        return tipo.getDescripcion();
                }
                return null;
            }

            public int getColumnCount() {
                return 3;
            }

            public String getColumnName(int columna) {
                switch (columna){
                    case 0:
                        return "ID";
                    case 1:
                        return "Categoria";
                    case 2:
                        return "Descripcion";
                }
                return null;
            }
        };
    }
    //metodos get y set

}
