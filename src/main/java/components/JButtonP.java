package components;

import javax.swing.*;
import java.awt.*;

public class JButtonP extends JButton {
    public JButtonP(){
        this.setBackground(new Color(94,53,177));
        this.setFont(new Font("Arial",0,20));
        this.setForeground(new Color(255,255,255));
        this.setIcon(new ImageIcon("src/main/resources/icono_agregar_carpeta.png"));
    }
}
