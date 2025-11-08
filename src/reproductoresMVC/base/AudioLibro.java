package reproductoresMVC.base;

import java.time.LocalDate;

public class AudioLibro extends Audio{
    private String categoria;

    public AudioLibro(){
        super();
    }


    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public AudioLibro(String titulo, String autor, String productora, LocalDate fechaSalida, int numParticipantes, double duracion, String idioma, String formato, double valoracion, String categoria) {
        super(titulo, autor, productora, fechaSalida, numParticipantes, duracion, idioma, formato, valoracion);
        this.categoria = categoria;
    }

    @Override
    public String toString() {

        return "Audio Libro: " + getTitulo() + " " + getAutor() + " " + getProductora() + " " + getCategoria() + " " +  getFechaSalida() + " " +  getNumParticipantes() + " " +  getDuracion() + " " +  getIdioma() + " " +  getFormato() + " " +  getValoracion();
    }
}
