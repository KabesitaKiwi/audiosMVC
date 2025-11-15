package reproductoresMVC;

        import reproductoresMVC.gui.ReproductorControlador;
        import reproductoresMVC.gui.ReproductorModelo;
        import reproductoresMVC.gui.Ventana;
        import reproductoresMVC.util.Utilidades;

public class Principal {
    public static void main(String[] args) {
        Ventana v = new Ventana();
        ReproductorModelo modelo = new ReproductorModelo();
        ReproductorControlador controlador = new ReproductorControlador(v, modelo);
        Utilidades utilidades = new Utilidades();
    }
}
