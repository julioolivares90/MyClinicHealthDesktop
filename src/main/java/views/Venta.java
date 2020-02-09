package views;

import models.*;
import sun.rmi.runtime.Log;
import utilidades.BusquedaDeProductos;
import utilidades.Mensajes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Venta {
    public JPanel principal;
    private JTable tblProductos;
    private JLabel logo;
    private JButton btnGenerarVenta;
    private JButton btnCancelarVenta;
    private JTextField txtTotalAPagar;
    private JTextField txtBuscarProductoPorNombre;
    private JTextField txtMedicamento;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JTextField txtCantidad;
    private JButton btnBuscarMedicamento;
    private JButton btnAgregarAVenta;
    private JTextField txtDescuento;
    private JTextField txtFechaActual;

    private DefaultTableModel defaultTableModel;

   private Producto producto;

    double totalAPagar=0.0;
    double precio;
    int cantidad;

    public Venta() {
        btnGenerarVenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                guardarVenta();
                limpiarData();
            }
        });
        btnCancelarVenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                limpiarData();
            }
        });
        btnBuscarMedicamento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                BusquedaDeProductos busquedaDeProductos = BusquedaDeProductos.getInstance();
                busquedaDeProductos.pack();
                busquedaDeProductos.setVisible(true);
                producto = busquedaDeProductos.getProducto();

                txtMedicamento.setText(producto.getNombre());
                txtStock.setText(String.valueOf(producto.getCantidad()));
                txtPrecio.setText(String.valueOf(producto.getCostoPublico()));

            }
        });

        txtBuscarProductoPorNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                super.keyPressed(keyEvent);
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER){
                    ProductoDao productoDao  = new ProductoDao();
                   ProductoViewModel productoViewModel = productoDao.findByName(txtBuscarProductoPorNombre.getText());
                   producto.setID(productoViewModel.getId_producto());
                   producto.setCantidad(productoViewModel.getCantidad());
                   producto.setCostoPublico(productoViewModel.getCosto_publico());
                   producto.setNombre(productoViewModel.getNombre());
                    txtMedicamento.setText(productoViewModel.getNombre());
                    txtStock.setText(String.valueOf(productoViewModel.getCantidad()));
                    txtPrecio.setText(String.valueOf(productoViewModel.getCosto_publico()));
                }
            }
        });
        btnAgregarAVenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                crearFilaTable(tblProductos);
            }
        });
    }


public void limpiarData(){
        this.txtDescuento.setText("");
        txtCantidad.setText("");
        txtMedicamento.setText("");
        txtTotalAPagar.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtBuscarProductoPorNombre.setText("");

        loadTable();

}
    private void guardarVenta() {

        VentaDao ventaDao = new VentaDao();
        models.Venta venta = new models.Venta();
        String descuento = txtDescuento.getText();

        int des;
        if (descuento.equals("")){
            des =0;
        }else {
            des = Integer.parseInt(txtDescuento.getText());
            venta.setMonto(Double.parseDouble(txtTotalAPagar.getText()));
            venta.setFecha_venta(txtFechaActual.getText());
            venta.setId_usuario(Login.getInstance().getIdUser());
            venta.setDescuento(des);
            int res = ventaDao.add(venta);
            if (res > 0){
                guardarDetalleVenta();
            }else {
                Mensajes.OcurrioUnError();
            }
        }

    }

    private void guardarDetalleVenta() {

        VentaDao ventaDao = new VentaDao();
        DetalleVentaDao detalleVentaDao = new DetalleVentaDao();

        String id =  ventaDao.IdVentas();
        int idVenta = Integer.parseInt(id);
        for(int i =0; i < tblProductos.getRowCount(); i++){

            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setId_venta(idVenta);
            detalleVenta.setCantidad(Integer.parseInt(tblProductos.getValueAt(i,0).toString()));
            detalleVenta.setPrecioVenta(Double.parseDouble(txtPrecio.getText()));
            detalleVenta.setId_producto(producto.getID());
            detalleVentaDao.add(detalleVenta);
        }
    }

    private void createUIComponents() {

        logo = new JLabel(new ImageIcon("src/main/resources/logo2.png"));
        Dimension dimension = new Dimension();
        dimension.setSize(30,30);
        logo.setPreferredSize(dimension);
        tblProductos = new JTable();
        loadTable();
        txtFechaActual = new JTextField();
        getDate();
    }

    private void getDate() {
        Calendar calendar = new GregorianCalendar();
        txtFechaActual.setText(""+calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void loadTable(){

        defaultTableModel = new DefaultTableModel(0,0);
        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("Nombre");
        defaultTableModel.addColumn("Cantidad");
        defaultTableModel.addColumn("precio");
        tblProductos.setModel(defaultTableModel);

    }
    private void crearFilaTable(JTable jTable){

        //double total=0.0;
        int item =0;
        Object[] filas = new Object[4];

        String nombre = txtMedicamento.getText();
        try {
            cantidad = Integer.parseInt(txtCantidad.getText());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"escribe cuantos medicamentos venderas");
            txtCantidad.requestFocus();
            return;
        }

        precio = Double.parseDouble(txtPrecio.getText());
        int stock = Integer.parseInt(txtStock.getText());
        filas[0] = producto.getID();
        filas[1] = nombre;
        filas[2] = cantidad;
        filas[3] = precio;

        DefaultTableModel defaultTableModel1 = (DefaultTableModel) jTable.getModel();
        item= item+1;

        //total = cantidad * precio;
        if (stock < cantidad || stock ==0){
            JOptionPane.showMessageDialog(null,"no se pude completar la operacion compra mas medicamentos");
        }
        else {
            defaultTableModel1.addRow(filas);
            calcularTotal();
        }
    }

    private void calcularTotal() {
        for (int i =0; i< tblProductos.getRowCount();i++){
            cantidad = Integer.parseInt(tblProductos.getValueAt(i,2).toString());
            precio = Double.parseDouble(tblProductos.getValueAt(i,3).toString());



            if (txtDescuento.getText().equals("")){
                totalAPagar = totalAPagar + (cantidad * precio);
            }else {
                double descuento = Double.parseDouble(txtDescuento.getText());
                double totalDescuento = (descuento * precio) / 100;

                totalAPagar = totalAPagar + (cantidad * precio);
                totalAPagar = totalAPagar-totalDescuento;
            }
        }
        txtTotalAPagar.setText(String.valueOf(totalAPagar));
    }
}
