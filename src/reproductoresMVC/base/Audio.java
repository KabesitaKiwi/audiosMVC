package reproductoresMVC.base;

import java.time.LocalDate;

public class Audio {
    private String titulo;
    private String autor;
    private String Productora;
    private LocalDate fechaSalida;
    private int numParticipantes;
    private double duracion;
    private String idioma;
    private String Formato;
    private double valoracion;

    public Audio(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getProductora() {
        return Productora;
    }

    public void setProductora(String productora) {
        Productora = productora;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getNumParticipantes() {
        return numParticipantes;
    }

    public void setNumParticipantes(int numParticipantes) {
        this.numParticipantes = numParticipantes;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getFormato() {
        return Formato;
    }

    public void setFormato(String formato) {
        Formato = formato;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public Audio(String titulo, String autor, String productora, LocalDate fechaSalida, int numParticipantes, double duracion, String idioma, String formato, double valoracion) {
        this.titulo = titulo;
        this.autor = autor;
        Productora = productora;
        this.fechaSalida = fechaSalida;
        this.numParticipantes = numParticipantes;
        this.duracion = duracion;
        this.idioma = idioma;
        Formato = formato;
        this.valoracion = valoracion;
    }
}
