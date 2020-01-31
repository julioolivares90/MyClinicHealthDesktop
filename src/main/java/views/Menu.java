package views;

import javax.swing.*;

public class Menu {
    private JPanel barraSuperior;
    private JPanel menuLeft;
    private JPanel menuCentral;
    public  JPanel principal;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel fondo;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        fondo = new JLabel(new ImageIcon("src/main/resources/Doctor.jpg"));
    }
}
