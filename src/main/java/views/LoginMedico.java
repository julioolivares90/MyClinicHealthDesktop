package views;

import consultas.CLogin;
import models.LoginViewModel;
import models.UserLogin;
import utilidades.Utilidades;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMedico {
    public JPanel principal;
    private JLabel logo;
    private JTextField txtUsuario;
    private JPasswordField txtPass;
    private JButton iniciarButton;
    private JButton salirButton;

    private static LoginMedico loginMedico;
    public synchronized static LoginMedico getInstance(){
        if (loginMedico == null){
            loginMedico = new LoginMedico();
        }
        return loginMedico;
    }
    public LoginMedico() {
        iniciarButton.addActionListener(new ActionListener() {
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
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        logo = new JLabel(new ImageIcon("src/main/resources/doctor.png"));
    }
}
