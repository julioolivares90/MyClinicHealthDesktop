package views;

import Constantes.Constantes;
import components.EditText;
import models.Tipo;
import models.TipoDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class NuevaCategoria {
    TipoDao tipoDao = new TipoDao();

    public JPanel principal;
    private JTextField txtTipo;
    private JButton crearButton;
    private JTextArea txtDescripcion;
    private JTable tblTipos;
    private JTextField txtNombre;


    DefaultTableModel tableModel;
    public NuevaCategoria() {
        //cargarDatos();
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
                    tipo.setTipoProducto(txtTipo.getText());
                    tipo.setDescripcion(txtDescripcion.getText());
                   int res = tipoDao.add(tipo);

                    if (res > 0){
                        JOptionPane.showMessageDialog(null,Constantes.AGREGADO_CON_EXITO);
                        limpiar();
                    }else{
                        JOptionPane.showMessageDialog(null,Constantes.NOSE_PUDO_AGREGAR);
                        limpiar();
                    }
                }
            }
        });
    }

    private void limpiar(){
        txtNombre.setText("");
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
        DefaultTableModel tableModel = new DefaultTableModel(0,3);

        tableModel.addColumn("ID");
        tableModel.addColumn("Tipo");
        tableModel.addColumn("Descripcion");
        //tableModel = new DefaultTableModel(0,3);

        for (int i =0; i< datos.size();i++){
            objects[0]= datos.get(i).getId();
            objects[1]= datos.get(i).getTipoProducto();
            objects[2]= datos.get(i).getDescripcion();

            //tableModel.addRow(objects);
        }

        tblTipos.setModel(tableModel);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        txtNombre = new EditText();
        tblTipos = new JTable();

        cargarDatos();
    }
}
