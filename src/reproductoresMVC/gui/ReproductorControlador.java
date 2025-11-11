package reproductoresMVC.gui;

import org.xml.sax.SAXException;
import reproductoresMVC.base.Audio;
import reproductoresMVC.util.Utilidades;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

public class ReproductorControlador implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana vista;
    private ReproductorModelo modelo;
    private File ultimaRutaExportada;

    public ReproductorControlador(Ventana vista, ReproductorModelo modelo){
        this.vista=vista;
        this.modelo=modelo;
        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No existe el fichero de configuracion "+e.getMessage());
        }
        addActionListener(this);

    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("vehiculos.conf"));
        ultimaRutaExportada=new File (configuracion.getProperty("ultimaRutaExportada"));
    }


    private void addActionListener(ActionListener listener){
        vista.nuevoButton.addActionListener(listener);
        vista.importarButton.addActionListener(listener);
        vista.exportarButton.addActionListener(listener);
        vista.musicaRadioButton.addActionListener(listener);
        vista.podcastRadioButton.addActionListener(listener);
        vista.audioLibroRadioButton.addActionListener(listener);
        vista.noticias.addActionListener(listener);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand){
            case "Nuevo":
                registrarAudio();
                limpiarCampos();
                refrescar();
                break;
            case "Importar":
                JFileChooser selectorFichero = Utilidades.cerrarSelectorFichero(ultimaRutaExportada, "Archivos XML", "xml");
                int op =selectorFichero.showOpenDialog(null);
                if (op==JFileChooser.APPROVE_OPTION){
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                    refrescar();
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2 = Utilidades.cerrarSelectorFichero(ultimaRutaExportada, "Archivos xml", "xml");
                int op2 =selectorFichero2.showSaveDialog(null);
                if(op2 == JFileChooser.APPROVE_OPTION){
                    try {
                        modelo.exportarXML(selectorFichero2.getSelectedFile());
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Música":
                vista.lblGenero.setText("Género");
                break;
            case "Podcast":
                vista.lblGenero.setText("Inivitados");
                break;
            case "Audio Libro":
                vista.lblGenero.setText("Categoria");
                break;
            case "Noticias":
                vista.lblGenero.setText("Categoria");
                break;
        }
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

    String titulo;
    String autor;
    String generoFluido;
    String prodcutora;
    String idioma;
    String formato;
    int nParticipantes;
    double tiempoDuracion;
    int valoracion;
    LocalDate fechaDeSalida;
    public void registrarAudio(){
        int participantes = (int) vista.campoParticipantes.getValue();
        Double duracion  = (double) vista.campoDuracion.getValue();

        if (!Utilidades.campoVacio(vista.campoTitulo)){
            Utilidades.lanzaAlertaVacio(vista.campoTitulo);
        }else if (!Utilidades.campoVacio(vista.campoAutor)){
            Utilidades.lanzaAlertaVacio(vista.campoAutor);
        }else if(!Utilidades.campoVacio(vista.campoProductora)){
            Utilidades.lanzaAlertaVacio(vista.campoProductora);
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
            try {
                titulo = vista.campoTitulo.getText();
                autor = vista.campoAutor.getText();
                generoFluido = vista.campoGeneroFluido.getText();
                prodcutora = vista.campoProductora.getText();
                fechaDeSalida = vista.campoFehca.getDate();
                nParticipantes = participantes;
                tiempoDuracion = duracion;
                idioma = vista.comboIdioma.getSelectedItem().toString();
                formato = vista.comboFormato.getSelectedItem().toString();
                valoracion = vista.campoValoracion.getValue();
                if (vista.musicaRadioButton.isSelected()){
                    modelo.altaMusica(titulo,autor,prodcutora,generoFluido,fechaDeSalida,nParticipantes,tiempoDuracion,idioma,formato,valoracion);
                }else if(vista.podcastRadioButton.isSelected()){
                    modelo.altaPodcast(titulo,autor,prodcutora,fechaDeSalida,nParticipantes,duracion,idioma,formato,valoracion,generoFluido);
                }else if (vista.audioLibroRadioButton.isSelected()){
                    modelo.altaAudioLibro(titulo,autor,generoFluido,prodcutora,fechaDeSalida,nParticipantes,duracion,idioma,formato,valoracion);
                }else {
                    modelo.altaNoticias(titulo,autor,prodcutora,fechaDeSalida,nParticipantes,duracion,idioma,formato,valoracion,generoFluido);
                }
            }catch (Exception ex) {

            }
        }
    }

    private void limpiarCampos(){
        vista.campoTitulo.setText(null);
        vista.campoAutor.setText(null);
        vista.campoGeneroFluido.setText(null);
        vista.campoProductora.setText(null);
        vista.campoFehca.setText(null);
        vista.campoDuracion.setValue(0);
        vista.campoParticipantes.setValue(0);
        vista.comboIdioma.setSelectedIndex(0);
        vista.comboFormato.setSelectedIndex(0);
        vista.campoTitulo.requestFocus(); //sirve para poner el cursor directamente en ese campo

    }

    private void refrescar() {
        vista.dlmAudio.clear();
        for (Audio vehiculo:modelo.obtenerListaAudios()) {
            vista.dlmAudio.addElement(vehiculo);
        }
    }
}
