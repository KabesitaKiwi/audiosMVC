package reproductoresMVC.base;

import java.time.LocalDate;

public class Podcast extends Audio{
    private String invitados;

    public Podcast(){
        super();
    }

    public Podcast(String titulo, String autor, String productora, LocalDate fechaSalida, int numParticipantes, double duracion, String idioma, String formato, double valoracion, String invitados) {
        super(titulo, autor, productora, fechaSalida, numParticipantes, duracion, idioma, formato, valoracion);
        this.invitados = invitados;
    }

    public String getInvitados() {
        return invitados;
    }

    public void setInvitados(String invitados) {
        this.invitados = invitados;
    }

    @Override
    public String toString() {
        return "Podcast: " + getTitulo() + " " + getAutor() + " " + getProductora() + " " + getInvitados() + " " +  getFechaSalida() + " " +  getNumParticipantes() + " " +  getDuracion() + " " +  getIdioma() + " " +  getFormato() + " " +  getValoracion();

    }
}
