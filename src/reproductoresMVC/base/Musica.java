package reproductoresMVC.base;

import java.time.LocalDate;

public class Musica extends Audio {
    private String genero;

    public Musica(){
        super();
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Musica(String titulo, String autor, String productora,  LocalDate fechaSalida, int numParticipantes, double duracion, String idioma, String formato, double valoracion, String genero) {
        super(titulo, autor, productora, fechaSalida, numParticipantes, duracion, idioma, formato, valoracion);
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Musica: " + getTitulo() + " " + getAutor() + " " + getProductora() + " " + getGenero() + " " + getFechaSalida() + " " +  getNumParticipantes() + " " +  getDuracion() + " " +  getIdioma() + " " +  getFormato() + " " +  getValoracion();
    }
}
