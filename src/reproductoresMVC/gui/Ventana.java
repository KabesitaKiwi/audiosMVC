package reproductoresMVC.gui;

import com.github.lgooddatepicker.components.DatePicker;
import reproductoresMVC.base.Audio;

import javax.swing.*;
import java.util.ArrayList;

public class Ventana {
    private JPanel panel1;
    private JRadioButton músicaRadioButton;
    private JRadioButton podcastRadioButton;
    private JRadioButton audioLibroRadioButton;
    private JRadioButton noticias;
    private JTextField campoTitulo;
    private JTextField campoAutor;
    private JComboBox comboIdioma;
    private JComboBox comboFormato;
    private JSpinner campoDuracion;
    private JSpinner campoParticipantes;
    private JSlider campoValoracion;
    private JTextField campoProductora;
    private JButton nuevoButton;
    private JButton exportarButton;
    private JButton importarButton;
    private JLabel lblGenero;
    private JTextField campoCambiante;
    private DatePicker campoFehca;
    private JList list1;


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
        dlmAudio = new DefaultListModel<Audio>();
        list1.setModel(dlmAudio);
    }

}
