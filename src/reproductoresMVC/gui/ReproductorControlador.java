package reproductoresMVC.gui;

import org.xml.sax.SAXException;
import reproductoresMVC.base.*;
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
import java.io.*;
import java.time.LocalDate;
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
        addWindowListener(this);
        addListSelectionListener(this);
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


    private void addWindowListener(WindowListener listener){
        vista.frame.addWindowListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener){
        vista.list1.addListSelectionListener(listener);
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("audios.conf"));
        ultimaRutaExportada=new File (configuracion.getProperty("ultimaRutaExportada"));
    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada=ultimaRutaExportada;
    }

    private void guardarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada",ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("audios.conf"), "Datos configuracion Audios");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand){
            case "Nuevo":
                registrarAudio();

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
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Música":
                vista.lblGenero.setText("Género");
                vista.campoGeneroFluido.setName("Género");
                break;
            case "Podcast":
                vista.lblGenero.setText("Inivitados");
                vista.campoGeneroFluido.setName("Invitados");
                break;
            case "Audio Libro":
                vista.lblGenero.setText("Categoría");
                vista.campoGeneroFluido.setName("Categoría");
                break;
            case "Noticias":
                vista.lblGenero.setText("Categoría");
                vista.lblGenero.setName("Categoría");
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int respuesta = Utilidades.mensajeConfirmacion("¿Estas seguro que quieres salir de la aplicacion?", "Salir");
        if (respuesta == JOptionPane.OK_OPTION){
            try {
                guardarDatosConfiguracion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
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
        if (e.getValueIsAdjusting()){
            Audio audioSeleccionado = (Audio) vista.list1.getSelectedValue();

            vista.campoTitulo.setText(audioSeleccionado.getTitulo());
            vista.campoAutor.setText(audioSeleccionado.getAutor());
            vista.campoProductora.setText(audioSeleccionado.getAutor());
            vista.comboIdioma.setSelectedItem(audioSeleccionado.getIdioma());
            vista.comboFormato.setSelectedItem(audioSeleccionado.getFormato());
            vista.campoDuracion.setValue(audioSeleccionado.getAutor());
            vista.campoParticipantes.setValue(audioSeleccionado.getAutor());
            vista.campoFehca.setText(audioSeleccionado.getAutor());
            if(audioSeleccionado instanceof Musica){
                vista.musicaRadioButton.doClick();
                vista.campoGeneroFluido.setText(((Musica) audioSeleccionado).getGenero());
            }else if(audioSeleccionado instanceof AudioLibro){
                vista.audioLibroRadioButton.doClick();
                vista.campoGeneroFluido.setText(((AudioLibro) audioSeleccionado).getCategoria());
            }else if(audioSeleccionado instanceof Podcast){
                vista.audioLibroRadioButton.doClick();
                vista.campoGeneroFluido.setText(((Podcast) audioSeleccionado).getInvitados());
            }else if (audioSeleccionado instanceof Noticias){
                vista.noticias.doClick();
                vista.campoGeneroFluido.setText(((Noticias) audioSeleccionado).getCategoria());
            }

        }

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

        if (!Utilidades.campoVacioCalendario(vista.campoTitulo)){
            Utilidades.lanzaAlertaVacio(vista.campoTitulo);
        }else if (!Utilidades.campoVacioCalendario(vista.campoAutor)){
            Utilidades.lanzaAlertaVacio(vista.campoAutor);
        }else if(!Utilidades.campoVacioCalendario(vista.campoProductora)){
            Utilidades.lanzaAlertaVacio(vista.campoProductora);
        }else if (!Utilidades.campoVacioCalendario(vista.campoGeneroFluido)){
            Utilidades.lanzaAlertaVacio(vista.campoGeneroFluido);
        }else if (Utilidades.campoVacioCalendario(vista.campoFehca)){
            Utilidades.lanzaAlertaVacioCalendar(vista.campoFehca);
        }else if (participantes == 0){
            Utilidades.lanzaAlertaCero(vista.campoParticipantes);
        }else if (duracion == 0){
            Utilidades.lanzaAlertaCero(vista.campoDuracion);
        }else if (Utilidades.comboNoSeleccionado(vista.comboIdioma)){
            Utilidades.lanzaAlertaCombo(vista.comboIdioma);
        }else if(Utilidades.comboNoSeleccionado(vista.comboFormato)){
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
                    limpiarCampos();
                }else if(vista.podcastRadioButton.isSelected()){
                    modelo.altaPodcast(titulo,autor,prodcutora,fechaDeSalida,nParticipantes,duracion,idioma,formato,valoracion,generoFluido);
                    limpiarCampos();
                }else if (vista.audioLibroRadioButton.isSelected()){
                    modelo.altaAudioLibro(titulo,autor,generoFluido,prodcutora,fechaDeSalida,nParticipantes,duracion,idioma,formato,valoracion);
                    limpiarCampos();
                }else {
                    modelo.altaNoticias(titulo,autor,prodcutora,fechaDeSalida,nParticipantes,duracion,idioma,formato,valoracion,generoFluido);
                    limpiarCampos();
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
        vista.campoParticipantes.setValue(1);
        vista.comboIdioma.setSelectedIndex(0);
        vista.comboFormato.setSelectedIndex(0);
        vista.campoValoracion.setValue(5);
        vista.campoTitulo.requestFocus(); //sirve para poner el cursor directamente en ese campo

    }

    private void refrescar() {
        vista.dlmAudio.clear();
        for (Audio audio:modelo.obtenerListaAudios()) {
            vista.dlmAudio.addElement(audio);
        }
    }
}
