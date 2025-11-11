package reproductoresMVC.util;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Utilidades {
    public static boolean campoVacio(JTextField campo){
               return !campo.getText().isEmpty();
    }
    public static boolean campoVacio(DatePicker campo){
        return campo.getDate() == null;
    }
    public static void  lanzaAlertaVacio (JTextField campo){
        JOptionPane.showMessageDialog(null,"El campo " + campo + "es obligatorio");
    }
    public static void lanzaAlertaVacioCalendar(DatePicker campo){
        JOptionPane.showMessageDialog(null,"El campo " + campo + "es obligatorio");
    }

    public static boolean comboNoSeleccionado(JComboBox combo){
        return  combo.getSelectedIndex()==0;
    }

    public static void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje,"ERROR",JOptionPane.ERROR_MESSAGE);
    }
    public static int mensajeConfirmacion(String mensaje,String titulo) {
        return JOptionPane.showConfirmDialog(null,mensaje,titulo,JOptionPane.YES_NO_OPTION);
    }

    public static JFileChooser crearSelectorFichero(File rutaDefecto, String tipoArchivos, String extension){
        JFileChooser selectorFichero = new JFileChooser();
        if (rutaDefecto != null){
            selectorFichero.setCurrentDirectory(rutaDefecto);
        }
        if(extension !=null){
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(tipoArchivos,extension);
            selectorFichero.setFileFilter(filtro);
        }
        return selectorFichero;
    }

    public static void lanzaAlertaCero(JSpinner spinner){
        JOptionPane.showMessageDialog(null, "El campo " + spinner + "no puede ser 0");
    }

    public static void lanzaAlertaCombo(JComboBox combo){
        JOptionPane.showMessageDialog(null, "Seleccione una opccion en: " + combo );
    }

}
