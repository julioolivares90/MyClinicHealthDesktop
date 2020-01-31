import views.Login;
import views.LoginMedico;

import javax.swing.*;

public class App {
    public static void main(String[] args){
        LoginMedico login = new LoginMedico();
        JFrame jFrame = new JFrame("login");
        jFrame.setSize(630,240);
        jFrame.setContentPane(login.principal);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
    }
}
