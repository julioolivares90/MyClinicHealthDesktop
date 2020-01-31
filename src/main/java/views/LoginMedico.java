package views;

import javax.swing.*;

public class LoginMedico {
    public JPanel principal;
    private JLabel logo;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        logo = new JLabel(new ImageIcon("src/main/resources/doctor.png"));
    }
}
