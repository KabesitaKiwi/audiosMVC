package reproductoresMVC.gui;

import reproductoresMVC.util.Utilidades;

<<<<<<< HEAD
=======
import javax.swing.*;
>>>>>>> 8f83fbfecd29948e1ff5d48ee4254a9d070983c2
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
public class ReproductorControlador implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana vista;
    private ReproductorModelo modelo;
    private File ultimaRutaExportada;

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    public void registrarAudio(){
        int participantes = (int) vista.campoParticipantes.getValue();
        Double duracion  = (double) vista.campoParticipantes.getValue();

        if (!Utilidades.campoVacio(vista.campoTitulo)){
            Utilidades.lanzaAlertaVacio(vista.campoTitulo);
        }else if (!Utilidades.campoVacio(vista.campoAutor)){
            Utilidades.lanzaAlertaVacio(vista.campoAutor);
        }else if (!Utilidades.campoVacio(vista.campoGeneroFluido)){
            Utilidades.lanzaAlertaVacio(vista.campoGeneroFluido);
        }else if (!Utilidades.campoVacio(vista.campoFehca)){
            Utilidades.lanzaAlertaVacioCalendar(vista.campoFehca);
        }else if (participantes == 0){
            Utilidades.lanzaAlertaCero(vista.campoParticipantes);
        }else if (duracion == 0){
            Utilidades.lanzaAlertaCero(vista.campoDuracion);
        }else if (!Utilidades.comboNoSeleccionado(vista.comboIdioma)){
            Utilidades.lanzaAlertaCombo(vista.comboIdioma);
        }else if(!Utilidades.comboNoSeleccionado(vista.comboFormato)){
            Utilidades.lanzaAlertaCombo(vista.comboFormato);
        }else{

        }
    }

}
