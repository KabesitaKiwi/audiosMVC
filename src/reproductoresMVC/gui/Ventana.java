package reproductoresMVC.gui;

import com.github.lgooddatepicker.components.DatePicker;
import reproductoresMVC.base.Audio;

import javax.swing.*;
import java.util.ArrayList;

public class Ventana {
    public JPanel panel1;
    public JRadioButton musicaRadioButton;
    public JRadioButton podcastRadioButton;
    public JRadioButton audioLibroRadioButton;
    public JRadioButton noticias;
    public JTextField campoTitulo;
    public JTextField campoAutor;
    public JComboBox comboIdioma;
    public JComboBox comboFormato;
    public JSpinner campoDuracion;
    public JSpinner campoParticipantes;
    public JSlider campoValoracion;
    public JTextField campoProductora;
    public JButton nuevoButton;
    public JButton exportarButton;
    public JButton importarButton;
    public JLabel lblGenero;
    public JTextField campoCambiante;
    public JTextField campoGeneroFluido;
    public DatePicker campoFehca;
    public JList list1;


    //hechos por mi
    public JFrame frame;
    public DefaultListModel<Audio> dlmAudio;

    public Ventana(){
        frame = new JFrame("Reproductor Audio MVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        initComponents();
    }

    public void initComponents(){
        campoTitulo.setName("titulo");
        dlmAudio = new DefaultListModel<Audio>();
        list1.setModel(dlmAudio);

        //añado los valores a los 2 combo

        comboIdioma.addItem("Seleccionar");
        comboIdioma.addItem("Español");
        comboIdioma.addItem("Dembow");
        comboIdioma.addItem("Guatemalteco");

        comboFormato.addItem("Seleccionar");
        comboFormato.addItem("MP3");
        comboFormato.addItem("MP4");
        comboFormato.addItem("WAV");

        //valor predeterminados del spiner

        SpinnerNumberModel duracion = new SpinnerNumberModel(3.5, 0.0, 600.0, 0.5);
        campoDuracion.setModel(duracion);

        SpinnerNumberModel participantes = new SpinnerNumberModel(1, 1, 10, 1);
        campoParticipantes.setModel(participantes);

        ((JSpinner.DefaultEditor) campoParticipantes.getEditor()).getTextField().setEditable(false);

        //valores de slider
        campoValoracion.setMinimum(0);     // Valor mínimo
        campoValoracion.setMaximum(10);    // Valor máximo
        campoValoracion.setValue(5);       // Valor inicialç
        campoValoracion.setMajorTickSpacing(1);  // Separación grande entre marcas
        campoValoracion.setPaintLabels(true);    // Mostrar los números debajo
    }



}
