package views;

import Constantes.Constantes;
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
    private JButton btnVentas;

    public Menu() {

        setContentPane(principal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        principal.setBackground(new Color(255,255,255));
        menuCentral.setBackground(new Color(255,255,255));
        barraSuperior.setBackground(new Color(94,53,177));
        menuLeft.setBackground(new Color(94,53,177));

        btnSalir.addActionListener(actionEvent -> System.exit(0));
        btnVenta.addActionListener(actionEvent -> {
            Venta venta = new Venta();
            JFrame jFrame = new JFrame("Venta");
            jFrame.setContentPane(venta.principal);
            jFrame.setLocationRelativeTo(null);
            jFrame.pack();
            jFrame.setVisible(true);
        });
        btnnuevoMedicamento.addActionListener(actionEvent -> {

            NuevoMedicamento nuevoMedicamento = new NuevoMedicamento();
            nuevoMedicamento.setContentPane(nuevoMedicamento.principal);


            Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
            int altura = pantalla.height;
            int ancho = pantalla.width;
            setSize(ancho/2,altura/2);
            nuevoMedicamento.setLocationRelativeTo(null);
            nuevoMedicamento.pack();
            nuevoMedicamento.setVisible(true);
            //menuCentral.add(nuevoMedicamento);
        });
        btnMedicamentos.addActionListener(actionEvent -> {
            Medicamentos medicamentos = new Medicamentos();
            Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
            int altura = pantalla.height;
            int ancho = pantalla.width;

            JFrame jFrame = new JFrame("Medicamentos");
            jFrame.setContentPane(medicamentos.principal);
            jFrame.setSize(ancho/2,altura/2);
            jFrame.setLocationRelativeTo(null);
            pack();
            jFrame.setVisible(true);

        });
        btnUsuarios.addActionListener(actionEvent -> {

        });
        btnUsuarios.addActionListener(actionEvent -> {
            Usuarios usuarios = new Usuarios();
            JFrame jFrame = new JFrame("Usuarios");
            jFrame.setLocationRelativeTo(null);
            jFrame.setContentPane(usuarios.principal);
            jFrame.pack();
            jFrame.setVisible(true);

        });
        btnNuevaCategoria.addActionListener(actionEvent -> {
            NuevaCategoria nuevaCategoria = new NuevaCategoria();
            JFrame jFrame = new JFrame("Nueva categoria");
            jFrame.setContentPane(nuevaCategoria.principal);
            jFrame.setLocationRelativeTo(null);
            jFrame.pack();
            jFrame.setVisible(true);
        });
        btnProveedores.addActionListener(actionEvent -> {
            Preedores preedores = new Preedores();
            JFrame jFrame = new JFrame("Nuevo proveedor");
            jFrame.setContentPane(preedores.principal);
            jFrame.setLocationRelativeTo(null);
            jFrame.pack();
            jFrame.setVisible(true);
        });
        btnVentas.addActionListener(actionEvent -> {
            Ventas ventas = new Ventas();
            ventas.setContentPane(ventas.getPrincipal());
            ventas.setLocationRelativeTo(null);
            ventas.pack();
            ventas.setVisible(true);
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        fondo = new JLabel(new ImageIcon("src/main/resources/Doctor.jpg"));

        btnVenta = new JButton(new ImageIcon("src/main/resources/icono_carrito_de_compra_cargado.png"));
        btnVentas = new JButton(new ImageIcon(Constantes.SRC+"icons_factura.png"));
        btnNuevaCategoria = new JButtonP();
        btnProveedores = new JButton(new ImageIcon("src/main/resources/icono_proveedor.png"));
        btnnuevoMedicamento = new JButton( new ImageIcon("src/main/resources/crear_nuevo.png"));
        btnMedicamentos = new JButton(new ImageIcon("src/main/resources/icono_lista.png"));
        btnUsuarios = new JButton(new ImageIcon("src/main/resources/icono_lista_usuarios.png"));
        btnSalir = new JButton(new ImageIcon("src/main/resources/cerrar_ventana.png"));
    }
}
