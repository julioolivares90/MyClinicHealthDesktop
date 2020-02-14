package views;

import consultas.CLogin;
import models.LoginViewModel;
import models.UserLogin;
import utilidades.Utilidades;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    public JPanel panel1;
    private JTextField txtUsuario;
    private JPasswordField txtPass;
    private JButton btnLogin;
    private JButton btnCancelar;
    private JLabel logo;
    private JLabel logo1;
    private JLabel imagen;

    public int getIdUser() {
        return idUser;
    }

    private int idUser=0;
    private String Rol;
    private  static Login login;
    public synchronized static Login getInstance(){
        if (login == null) {
            login = new Login();
        }
        return  login;
    }
    public Login() {
        this.setLocationRelativeTo(null);
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                        idUser = userData.getId();
                        Rol = userData.getRol();
                        JOptionPane.showMessageDialog(null, "Bienvenido!!!");
                        JFrame jFrame = new JFrame("Menu");
                        Menu menu = new Menu();
                        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
                        int heigth = pantalla.height;
                        int width = pantalla.width;
                        setExtendedState(JFrame.MAXIMIZED_HORIZ);
                        setExtendedState(JFrame.MAXIMIZED_VERT);
                        //menu.setSize(width,heigth);
                        menu.setLocationRelativeTo(null);
                        menu.setVisible(true);
                        menu.pack();
                        hide();
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
        logo1 = new JLabel(new ImageIcon("src/main/resources/doctor.png"));

    }

    //getter

    public String getRol() {
        return Rol;
    }
}
