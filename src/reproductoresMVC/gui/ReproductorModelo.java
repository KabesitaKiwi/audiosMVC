package reproductoresMVC.gui;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import reproductoresMVC.base.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
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
        listaAudios.add(nuevaCancion);
    }
    public void altaAudioLibro(String titulo, String autor, String categoria, String productora, LocalDate fechaSalida, int numParticipantes, double duracion, String idioma, String formato, double valoracion){
        AudioLibro nuevoLibro = new AudioLibro(titulo,autor,productora,fechaSalida,numParticipantes,duracion,idioma,formato,valoracion,categoria);
        listaAudios.add(nuevoLibro);
    }
    public void altaPodcast(String titulo, String autor, String productora, LocalDate fechaSalida, int numParticipantes, double duracion, String idioma, String formato, double valoracion, String invitados){
        Podcast nuevoPodcast = new Podcast(titulo,autor,productora,fechaSalida,numParticipantes,duracion,idioma,formato,valoracion,invitados);
        listaAudios.add(nuevoPodcast);
    }
    public void altaNoticias(String titulo, String autor, String productora, LocalDate fechaSalida, int numParticipantes, double duracion, String idioma, String formato, double valoracion, String categoria){
        Noticias nuevaNoticia = new Noticias(titulo,autor,productora,fechaSalida,numParticipantes,duracion,idioma,formato,valoracion,categoria);
        listaAudios.add(nuevaNoticia);
    }

    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
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
            if (unAudio instanceof Musica){
                nodoAudio = documento.createElement("Musica");
            }else if (unAudio instanceof AudioLibro){
                nodoAudio = documento.createElement("Audio_libro");
            }else if( unAudio instanceof Podcast){
                nodoAudio = documento.createElement("Podcast");
            }else{
                nodoAudio = documento.createElement("Noticias");
            }
            raiz.appendChild(nodoAudio);

            nodoDatos = documento.createElement("titulo");
            nodoAudio.appendChild(nodoDatos);
            text = documento.createTextNode(unAudio.getTitulo());
            nodoDatos.appendChild(text);

            nodoDatos = documento.createElement("autor");
            nodoAudio.appendChild(nodoDatos);
            text = documento.createTextNode(unAudio.getAutor());
            nodoDatos.appendChild(text);

            nodoDatos = documento.createElement("productora");
            nodoAudio.appendChild(nodoDatos);
            text = documento.createTextNode(unAudio.getProductora());
            nodoDatos.appendChild(text);

            nodoDatos = documento.createElement("fecha-Salida");
            nodoAudio.appendChild(nodoDatos);
            text = documento.createTextNode(unAudio.getFechaSalida().toString());
            nodoDatos.appendChild(text);

            nodoDatos = documento.createElement("numero-participantes");
            nodoAudio.appendChild(nodoDatos);
            text = documento.createTextNode(String.valueOf(unAudio.getNumParticipantes()));
            nodoDatos.appendChild(text);

            nodoDatos = documento.createElement("duracion");
            nodoAudio.appendChild(nodoDatos);
            text = documento.createTextNode(String.valueOf(unAudio.getDuracion()));
            nodoDatos.appendChild(text);

            nodoDatos = documento.createElement("idioma");
            nodoAudio.appendChild(nodoDatos);
            text = documento.createTextNode(unAudio.getIdioma());
            nodoDatos.appendChild(text);

            nodoDatos = documento.createElement("formato");
            nodoAudio.appendChild(nodoDatos);
            text = documento.createTextNode(unAudio.getFormato());
            nodoDatos.appendChild(text);

            nodoDatos = documento.createElement("valoracion");
            nodoAudio.appendChild(nodoDatos);
            text = documento.createTextNode(String.valueOf(unAudio.getValoracion()));
            nodoDatos.appendChild(text);

            if (unAudio instanceof Musica){
                nodoDatos = documento.createElement("genero");
                nodoAudio.appendChild(nodoDatos);
                text = documento.createTextNode(((Musica) unAudio).getGenero());
                nodoDatos.appendChild(text);
            }else if (unAudio instanceof AudioLibro){
                nodoDatos= documento.createElement("categoria");
                nodoAudio.appendChild(nodoDatos);
                text = documento.createTextNode(((AudioLibro) unAudio).getCategoria());
                nodoDatos.appendChild(text);
            }else if (unAudio instanceof Podcast){
                nodoDatos = documento.createElement("invitados");
                nodoAudio.appendChild(nodoDatos);
                text = documento.createTextNode(((Podcast) unAudio).getInvitados());
                nodoDatos.appendChild(text);
            }else if (unAudio instanceof Noticias){
                nodoDatos = documento.createElement("categoria");
                nodoAudio.appendChild(nodoDatos);
                text = documento.createTextNode(((Noticias) unAudio).getCategoria());
                nodoDatos.appendChild(text);
            }


        }

        //ahora se guardan los datos en el fichero

        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newDefaultInstance().newTransformer();
        transformer.transform(source, resultado);

    }

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaAudios = new ArrayList<Audio>();
        Musica nuevaMusica = null;
        AudioLibro nuevoAudioLibro = null;
        Podcast nuevoPodcast = null;
        Noticias nuevaNoticia = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i = 0; i<listaElementos.getLength();i++){

            Element nodoAudio = (Element) listaElementos.item(i);

            if(nodoAudio.getTagName().equals("Musica")){
                nuevaMusica = new Musica();
                nuevaMusica.setTitulo(nodoAudio.getChildNodes().item(0).getTextContent());
                nuevaMusica.setAutor(nodoAudio.getChildNodes().item(1).getTextContent());
                nuevaMusica.setProductora(nodoAudio.getChildNodes().item(2).getTextContent());
                nuevaMusica.setFechaSalida(LocalDate.parse(nodoAudio.getChildNodes().item(3).getTextContent()));
                nuevaMusica.setNumParticipantes(Integer.parseInt(nodoAudio.getChildNodes().item(4).getTextContent()));
                nuevaMusica.setDuracion(Double.parseDouble(nodoAudio.getChildNodes().item(5).getTextContent()));
                nuevaMusica.setIdioma(nodoAudio.getChildNodes().item(6).getTextContent());
                nuevaMusica.setFormato(nodoAudio.getChildNodes().item(7).getTextContent());
                nuevaMusica.setValoracion(Double.parseDouble(nodoAudio.getChildNodes().item(8).getTextContent()));
                nuevaMusica.setGenero(nodoAudio.getChildNodes().item(9).getTextContent());
                listaAudios.add(nuevaMusica);

            }else if (nodoAudio.getTagName().equals("Audio_libro")){
                nuevoAudioLibro = new AudioLibro();
                nuevoAudioLibro.setTitulo(nodoAudio.getChildNodes().item(0).getTextContent());
                nuevoAudioLibro.setAutor(nodoAudio.getChildNodes().item(1).getTextContent());
                nuevoAudioLibro.setProductora(nodoAudio.getChildNodes().item(2).getTextContent());
                nuevoAudioLibro.setFechaSalida(LocalDate.parse(nodoAudio.getChildNodes().item(3).getTextContent()));
                nuevoAudioLibro.setNumParticipantes(Integer.parseInt(nodoAudio.getChildNodes().item(4).getTextContent()));
                nuevoAudioLibro.setDuracion(Double.parseDouble(nodoAudio.getChildNodes().item(5).getTextContent()));
                nuevoAudioLibro.setIdioma(nodoAudio.getChildNodes().item(6).getTextContent());
                nuevoAudioLibro.setFormato(nodoAudio.getChildNodes().item(7).getTextContent());
                nuevoAudioLibro.setValoracion(Double.parseDouble(nodoAudio.getChildNodes().item(8).getTextContent()));
                nuevoAudioLibro.setCategoria(nodoAudio.getChildNodes().item(9).getTextContent());
                listaAudios.add(nuevoAudioLibro);
            }else if (nodoAudio.getTagName().equals("Podcast")) {
                nuevoPodcast = new Podcast();
                nuevoPodcast.setTitulo(nodoAudio.getChildNodes().item(0).getTextContent());
                nuevoPodcast.setAutor(nodoAudio.getChildNodes().item(1).getTextContent());
                nuevoPodcast.setProductora(nodoAudio.getChildNodes().item(2).getTextContent());
                nuevoPodcast.setFechaSalida(LocalDate.parse(nodoAudio.getChildNodes().item(3).getTextContent()));
                nuevoPodcast.setNumParticipantes(Integer.parseInt(nodoAudio.getChildNodes().item(4).getTextContent()));
                nuevoPodcast.setDuracion(Double.parseDouble(nodoAudio.getChildNodes().item(5).getTextContent()));
                nuevoPodcast.setIdioma(nodoAudio.getChildNodes().item(6).getTextContent());
                nuevoPodcast.setFormato(nodoAudio.getChildNodes().item(7).getTextContent());
                nuevoPodcast.setValoracion(Double.parseDouble(nodoAudio.getChildNodes().item(8).getTextContent()));
                nuevoPodcast.setInvitados(nodoAudio.getChildNodes().item(9).getTextContent());
                listaAudios.add(nuevoPodcast);
            }else{
                if (nodoAudio.getTagName().equals("Noticias")){
                    nuevaNoticia = new Noticias();
                    nuevaNoticia.setTitulo(nodoAudio.getChildNodes().item(0).getTextContent());
                    nuevaNoticia.setAutor(nodoAudio.getChildNodes().item(1).getTextContent());
                    nuevaNoticia.setProductora(nodoAudio.getChildNodes().item(2).getTextContent());
                    nuevaNoticia.setFechaSalida(LocalDate.parse(nodoAudio.getChildNodes().item(3).getTextContent()));
                    nuevaNoticia.setNumParticipantes(Integer.parseInt(nodoAudio.getChildNodes().item(4).getTextContent()));
                    nuevaNoticia.setDuracion(Double.parseDouble(nodoAudio.getChildNodes().item(5).getTextContent()));
                    nuevaNoticia.setIdioma(nodoAudio.getChildNodes().item(6).getTextContent());
                    nuevaNoticia.setFormato(nodoAudio.getChildNodes().item(7).getTextContent());
                    nuevaNoticia.setValoracion(Double.parseDouble(nodoAudio.getChildNodes().item(8).getTextContent()));
                    nuevaNoticia.setCategoria(nodoAudio.getChildNodes().item(9).getTextContent());
                    listaAudios.add(nuevaNoticia);
                }

            }

        }

    }

}
