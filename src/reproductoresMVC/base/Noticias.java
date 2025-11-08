package reproductoresMVC.base;

import java.time.LocalDate;

public class Noticias extends Audio {
    private String categoria;
    public Noticias(){
        super();
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Noticias(String titulo, String autor, String productora, LocalDate fechaSalida, int numParticipantes, double duracion, String idioma, String formato, double valoracion, String categoria) {
        super(titulo, autor, productora, fechaSalida, numParticipantes, duracion, idioma, formato, valoracion);
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Noticias: " + getTitulo() + " " + getAutor() + " " + getProductora() + " " + getCategoria() + " " +  getFechaSalida() + " " +  getNumParticipantes() + " " +  getDuracion() + " " +  getIdioma() + " " +  getFormato() + " " +  getValoracion();

    }
}
