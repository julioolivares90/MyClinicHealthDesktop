package views;

import models.*;
import utilidades.Mensajes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Usuarios extends JFrame {
    public JPanel principal;
    private JTable tblUsuarios;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtUserName;
    private JPasswordField txtPassword;
    private JComboBox cbTipoUser;
    private JTextField txtID;
    private JButton crearButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    private JButton button1;

    public Usuarios() {
        crearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String username = txtUserName.getText();
                String password = txtPassword.getText();

                if (nombre.equals("")){
                    Mensajes.MensajeDeVacio();
                    txtNombre.requestFocus();
                }else if (apellido.equals("")){
                    Mensajes.MensajeDeVacio();
                    txtApellido.requestFocus();
                }else if (username.equals("")){
                    Mensajes.MensajeDeVacio();
                    txtUserName.requestFocus();
                }else if (password.equals("")){
                    Mensajes.MensajeDeVacio();
                    txtPassword.requestFocus();
                }else {
                    UsuarioDao  usuarioDao = new UsuarioDao();
                    Usuario usuario = new Usuario();
                    usuario.setNombre(nombre);
                    usuario.setApellido(apellido);
                    usuario.setUserName(username);
                    usuario.setPass(password);

                    int idRol = cbTipoUser.getSelectedIndex();
                    if (idRol <= 0){
                        Mensajes.MensajeComboTipoUser();
                    }else {
                        usuario.setId_rol(idRol);
                        int res = usuarioDao.add(usuario);
                        if (res>0){
                            Mensajes.AgregadoConExito();
                            fillTable();
                        }else  {
                            Mensajes.OcurrioUnError();
                        }
                    }
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int id = Integer.parseInt(txtID.getText());
                if (id>0){
                    UsuarioDao usuarioDao = new UsuarioDao();
                    int res = usuarioDao.delete(id);
                    if (res > 0){
                        Mensajes.BorradoConExito();
                        fillTable();
                    }else {
                       Mensajes.OcurrioUnError();
                    }
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                limpiar();
            }
        });
        tblUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                int fila = tblUsuarios.getSelectedRow();
                if (fila == -1){
                    Mensajes.SeleccionaUnaFila();
                }else {
                    int id = Integer.parseInt(tblUsuarios.getValueAt(fila,0).toString());
                    String nombre = tblUsuarios.getValueAt(fila,1).toString();
                    String apellido = tblUsuarios.getValueAt(fila,2).toString();
                    String username = tblUsuarios.getValueAt(fila,3).toString();

                    txtID.setText(String.valueOf(id)) ;
                    txtNombre.setText(nombre);
                    txtApellido.setText(apellido);
                    txtUserName.setText(username);
                }
            }
        });
    }

    private void limpiar(){

        this.txtID.setText("");
        this.txtNombre.setText("");
        this.txtApellido.setText("");
        this.txtPassword.setText("");
        this.txtUserName.setText("");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        tblUsuarios = new JTable();
        fillTable();
        cbTipoUser = new JComboBox();
        fillComboBox();

    }
    private void fillTable(){
        UsuarioDao usuarioDao = new UsuarioDao();
        List<UsuarioViewModel> usuarios = usuarioDao.listar();
        Object[]objects = new Object[5];
        DefaultTableModel tableModel = new DefaultTableModel(0,0);
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Apellido");
        tableModel.addColumn("nombre de usuario");
        tableModel.addColumn("rol");

        for (UsuarioViewModel usuarioViewModel : usuarios){
            objects[0] = usuarioViewModel.getID();
            objects[1] = usuarioViewModel.getNombre();
            objects[2] = usuarioViewModel.getApellido();
            objects[3] = usuarioViewModel.getUserName();
            objects[4] = usuarioViewModel.getRol();

            tableModel.addRow(objects);
        }
        tblUsuarios.setModel(tableModel);

    }
    private void fillComboBox(){
        RolDao rolDao = new RolDao();
        List<Rol> rols = rolDao.listar();
        cbTipoUser.addItem("Selecciona un usuario");
        for (Rol rol : rols){
            cbTipoUser.addItem(rol.getRol());
        }
    }
}
