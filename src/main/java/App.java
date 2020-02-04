import views.Login;
import views.LoginMedico;
import views.Menu;

import javax.swing.*;

public class App {
    public static void main(String[] args){
        Login login = Login.getInstance();
        login.setVisible(true);
        login.pack();
        //Menu menu = new Menu();
        //JFrame jFrame = new JFrame("login");
        //jFrame.setSize(630,240);
        //jFrame.setContentPane(menu.principal);
        Menu menu = new Menu();
        //menu.setVisible(true);
        //menu.pack();
       /*
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
       * */
    }
}
