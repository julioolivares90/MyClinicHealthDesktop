package views;

import consultas.CLogin;
import models.LoginViewModel;
import models.UserLogin;
import utilidades.Utilidades;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    public JPanel panel1;
    private JTextField txtUsuario;
    private JPasswordField txtPass;
    private JButton btnLogin;
    private JButton btnCancelar;
    private JLabel logo;
    private JLabel imagen;

    public Login() {


        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String username = txtUsuario.getText();
                String pass = txtPass.getText();

                if (username.equals("")){
                    JOptionPane.showMessageDialog(null,"Error ingresa un usuario ");
                    txtUsuario.requestFocus();
                }else if (pass.equals("")){
                    JOptionPane.showMessageDialog(null,"Error ingresa una contraseña");
                    txtPass.requestFocus();
                }else {
                    LoginViewModel loginViewModel = new LoginViewModel();
                    loginViewModel.setUsername(username);
                    loginViewModel.setPassword(Utilidades.Encriptar(pass));

                    UserLogin userData = CLogin.Login(loginViewModel);
                    if (userData != null){
                        JOptionPane.showMessageDialog(null, "Bienvenido!!!");
                    }else {
                        JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos");
                    }

                }

            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        logo = new JLabel(new ImageIcon("src/main/resources/Doctor.jpg"));

    }
}
