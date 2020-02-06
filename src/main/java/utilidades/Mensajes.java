package utilidades;

import Constantes.Constantes;

import javax.swing.*;

public class Mensajes {
    public static void MensajeDeVacio(){
        JOptionPane.showMessageDialog(null, Constantes.CAMPO_VACIO);
    }
    public static  void  SoloNumerosDoubles(){
        JOptionPane.showMessageDialog(null,Constantes.SOLO_NUMEROS_DOUBLES);
    }
    public static void SoloNumerosInt(){
        JOptionPane.showMessageDialog(null,Constantes.SOLO_NUMEROS_INT);
    }
    public static void MensajeComboProveedor(){
        JOptionPane.showMessageDialog(null,"Selecciona un proveedor");
    }
    public static void MensajeComboTipoProducto(){
        JOptionPane.showMessageDialog(null,"Selecciona un tipo de medicamento");
    }
    public static void  AgregadoConExito(){
        JOptionPane.showMessageDialog(null,"Agregado con exito!!");
    }
    public static void OcurrioUnError(){
        JOptionPane.showMessageDialog(null,"Ocurrio un error");
    }
}
