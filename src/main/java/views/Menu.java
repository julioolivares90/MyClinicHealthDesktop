package views;

import components.JButtonP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JPanel barraSuperior;
    private JPanel menuLeft;
    private JPanel menuCentral;
    public  JPanel principal;
    private JButton btnVenta;
    private JButton btnnuevoMedicamento;
    private JButton btnMedicamentos;
    private JButton btnUsuarios;
    private JLabel fondo;
    private JButton btnSalir;
    private JButton btnNuevaCategoria;
    private JButton btnProveedores;

    public Menu() {
        this.setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(principal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        principal.setBackground(new Color(255,255,255));
        menuCentral.setBackground(new Color(255,255,255));
        barraSuperior.setBackground(new Color(94,53,177));
        menuLeft.setBackground(new Color(94,53,177));

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        btnVenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        btnnuevoMedicamento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                NuevoMedicamento nuevoMedicamento = new NuevoMedicamento();
                JFrame jFrame = new JFrame("Nuevo Medicamento");
                jFrame.setContentPane(nuevoMedicamento.principal);
                //jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setLocationRelativeTo(null);
                jFrame.pack();
                jFrame.setVisible(true);
                //menuCentral.add(nuevoMedicamento);
            }
        });
        btnMedicamentos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        btnUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        btnUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        btnNuevaCategoria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                NuevaCategoria nuevaCategoria = new NuevaCategoria();
                JFrame jFrame = new JFrame("Nueva categoria");
                jFrame.setContentPane(nuevaCategoria.principal);
                jFrame.setLocationRelativeTo(null);
                jFrame.pack();
                jFrame.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        fondo = new JLabel(new ImageIcon("src/main/resources/Doctor.jpg"));

        btnVenta = new JButton(new ImageIcon("src/main/resources/icono_carrito_de_compra_cargado.png"));
        btnNuevaCategoria = new JButtonP();
        btnProveedores = new JButton(new ImageIcon("src/main/resources/icono_proveedor.png"));
        btnnuevoMedicamento = new JButton( new ImageIcon("src/main/resources/crear_nuevo.png"));
        btnMedicamentos = new JButton(new ImageIcon("src/main/resources/icono_lista.png"));
        btnUsuarios = new JButton(new ImageIcon("src/main/resources/icono_lista_usuarios.png"));
        btnSalir = new JButton(new ImageIcon("src/main/resources/cerrar_ventana.png"));
    }
}
