package utilidades;

import models.Producto;
import models.ProductoDao;
import models.ProductoViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class BusquedaDeProductos extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table1;

   private Producto producto = new Producto();

    public Producto getProducto() {
        return producto;
    }

    private static BusquedaDeProductos instance ;
    public static void setInstance(BusquedaDeProductos instance){
        BusquedaDeProductos.instance = instance;
    }

    public synchronized static BusquedaDeProductos getInstance(){
        if (instance == null){
            instance = new BusquedaDeProductos();
        }
        return instance;
    }

    public BusquedaDeProductos() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                int fila = table1.getSelectedRow();
                if (fila == -1){
                    Mensajes.SeleccionaUnaFila();
                }else {
                    int id = Integer.parseInt(table1.getValueAt(fila,0).toString());
                    String nombre = table1.getValueAt(fila,1).toString();
                    double costoPublico = Double.parseDouble(table1.getValueAt(fila,2).toString());
                    int cantidad = Integer.parseInt(table1.getValueAt(fila,3).toString());

                    producto.setID(id);
                    producto.setNombre(nombre);
                    producto.setCostoPublico(costoPublico);
                    producto.setCantidad(cantidad);
                }
            }
        });
    }

    private void onOK() {
        // add your code here

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void loadData(){
        ProductoDao productoDao = new ProductoDao();
        List<ProductoViewModel> productoViewModels = productoDao.showData();
        DefaultTableModel defaultTableModel = new DefaultTableModel(0,0);

        defaultTableModel.addColumn("ID producto");
        defaultTableModel.addColumn("Nombre");
        defaultTableModel.addColumn("Costo publico");
        defaultTableModel.addColumn("Cantidad");
        Object[] objects = new Object[4];
        for (ProductoViewModel productoViewModel : productoViewModels){

            objects[0] = productoViewModel.getId_producto();
            objects[1] = productoViewModel.getNombre();
            objects[2] = productoViewModel.getCosto_publico();
            objects[3] = productoViewModel.getCantidad();

            defaultTableModel.addRow(objects);
        }
        table1.setModel(defaultTableModel);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        table1 = new  JTable();
        loadData();
    }
/*
    public static void main(String[] args) {
        BusquedaDeProductos dialog = new BusquedaDeProductos();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

 */
}
