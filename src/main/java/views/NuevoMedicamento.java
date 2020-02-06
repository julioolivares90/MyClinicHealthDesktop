package views;

import models.*;
import utilidades.Mensajes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;

public class NuevoMedicamento extends JFrame {

    public JPanel principal;
    private JTextField txtNombre;
    private JTextField txtCostoPorUnidad;
    private JTextField txtCostoPublico;
    private JButton btnAceptar;
    private JComboBox cbTipoProducto;
    private JComboBox cbProveedor;
    private JTextField txtCantidad;
    private JButton btnLimpiar;

    public NuevoMedicamento(){
        Dimension dimension = new Dimension();
        dimension.width = 640;
        dimension.height = 300;
        principal.setPreferredSize(dimension);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();

        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String nombre = txtNombre.getText();
                int cantidad =0;
                double costoPorUnidad =0.0;
                double costoPublico =0.0;
                if (nombre.equals("")){
                    Mensajes.MensajeDeVacio();
                    txtNombre.requestFocus();
                }else if(txtCantidad.getText().equals("")){
                    Mensajes.MensajeDeVacio();
                    txtCantidad.requestFocus();
                }else  if (txtCostoPorUnidad.getText().equals("")){
                    Mensajes.MensajeDeVacio();
                    txtCostoPorUnidad.requestFocus();
                }else if (txtCostoPublico.getText().equals("")){
                    Mensajes.MensajeDeVacio();
                    txtCostoPublico.requestFocus();
                }else {
                    try{
                        cantidad = Integer.parseInt(txtCantidad.getText());
                    }catch (Exception e){
                        Mensajes.SoloNumerosInt();
                        txtCantidad.requestFocus();
                    }
                    try {
                        costoPorUnidad = Double.valueOf(txtCostoPorUnidad.getText());
                    }catch (Exception e){
                        Mensajes.SoloNumerosDoubles();
                        txtCostoPorUnidad.requestFocus();
                    }
                    try {
                        costoPublico = Double.valueOf(txtCostoPublico.getText());
                    }catch (Exception e){
                        Mensajes.SoloNumerosDoubles();
                        txtCostoPublico.requestFocus();
                    }

                    Producto producto = new Producto();
                    ProductoDao productoDao = new ProductoDao();

                    int idProveedor = cbProveedor.getSelectedIndex();
                    int idTipo = cbTipoProducto.getSelectedIndex();
                    if (idProveedor <= 0){
                        Mensajes.MensajeComboProveedor();
                    }
                    else if (idTipo <=0){
                        Mensajes.MensajeComboTipoProducto();
                    }else {
                        double ganancia = costoPublico-costoPorUnidad;

                        producto.setNombre(nombre);
                        producto.setCantidad(cantidad);
                        producto.setCostoPublico(costoPublico);
                        producto.setCostoPoUnidad(costoPorUnidad);
                        producto.setGanancia(ganancia);
                        producto.setIdTipoProducto(idTipo);
                        producto.setIdProveedor(idProveedor);

                        int res = productoDao.add(producto);
                        if (res >0 ){
                            Mensajes.AgregadoConExito();
                        }else{
                            Mensajes.OcurrioUnError();
                        }
                    }
                }
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                limpiar();
            }
        });
    }

    private void fillComboBoxProveedores(){
        ProveedorDao proveedorDao = new ProveedorDao();
        List<ProveedorViewModel> proveedorViewModelList = proveedorDao.getProveedoresComboBox();
        cbProveedor.addItem("seleccionar un proveedor");
        for (ProveedorViewModel proveedor: proveedorViewModelList
             ) {
            cbProveedor.addItem(proveedor.getNombre());
        }
    }
    private void limpiar(){
        txtCantidad.setText("");
        txtCostoPorUnidad.setText("");
        txtCostoPublico.setText("");
        txtNombre.setText("");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        cbProveedor = new JComboBox();
        cbTipoProducto = new JComboBox();
        fillComboBoxProveedores();
        fillComboBoxTipos();
    }

    private void fillComboBoxTipos() {
        TipoDao tipoDao = new TipoDao();
        List<Tipo> tipos = tipoDao.getTiposComboBox();
        cbTipoProducto.addItem("selecciona un tipo de medicamento");

        for (Tipo tipo : tipos){
            cbTipoProducto.addItem(tipo.getTipoProducto());
        }
    }
}
