package reproductoresMVC.gui;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import reproductoresMVC.base.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReproductorModelo {
    private ArrayList<Audio> listaAudios;

    public ReproductorModelo (){listaAudios = new ArrayList<Audio>();}

    public ArrayList<Audio> obtenerListaAudios() {
        return listaAudios;
    }

    public void altaMusica(String titulo, String autor, String genero, String productora, LocalDate fechaSalida, int numParticipantes, double duracion, String idioma, String formato, double valoracion) {
        Musica nuevaCancion= new Musica(titulo, autor,productora,fechaSalida,numParticipantes,duracion,idioma,formato,valoracion,genero);
    }
    public void altaAudioLibro(String titulo, String autor, String categoria, String productora, LocalDate fechaSalida, int numParticipantes, double duracion, String idioma, String formato, double valoracion){
        AudioLibro nuevoLibro = new AudioLibro(titulo,autor,productora,fechaSalida,numParticipantes,duracion,idioma,formato,valoracion,categoria);
    }
    public void altaPodcast(String titulo, String autor, String productora, LocalDate fechaSalida, int numParticipantes, double duracion, String idioma, String formato, double valoracion, String invitados){
        Podcast nuevoPodcast = new Podcast(titulo,autor,productora,fechaSalida,numParticipantes,duracion,idioma,formato,valoracion,invitados);
    }
    public void altaNoticias(String titulo, String autor, String productora, LocalDate fechaSalida, int numParticipantes, double duracion, String idioma, String formato, double valoracion, String categoria){
        Noticias nuevaNoticia = new Noticias(titulo,autor,productora,fechaSalida,numParticipantes,duracion,idioma,formato,valoracion,categoria);
    }

    public void exportarXML(File fichero) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        Element raiz = documento.createElement("Audio");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoAudio = null;
        Element nodoDatos = null;
        Text text = null;

        for (Audio unAudio : listaAudios){
            //se añade dentro de Audios la musica, el podcas, las noticias y el audiolibro;

        }

    }

}
