package views;

import javax.swing.*;
import java.awt.*;

public class NuevoMedicamento extends JFrame {

    public JPanel principal;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton btnAceptar;

    public NuevoMedicamento(){
        Dimension dimension = new Dimension();
        dimension.width = 640;
        dimension.height = 300;
        principal.setPreferredSize(dimension);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }
}
