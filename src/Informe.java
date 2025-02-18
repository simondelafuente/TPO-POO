import java.util.List;

public class Informe {
    private int cantNuevosSocios;
    private List<Instalacion> instalacionesReservadas;
    private double recaudacion;

    public Informe(int cantNuevosSocios, List<Instalacion> instalacionesReservadas, double recaudacion) {
        this.cantNuevosSocios = cantNuevosSocios;
        this.instalacionesReservadas = instalacionesReservadas;
        this.recaudacion = recaudacion;
    }

    //Imprimir el informe
    public void mostrarInforme() {
        System.out.println("\n--- Informe del Club ---");
        System.out.println("Cantidad de nuevos socios: " + cantNuevosSocios);
        System.out.println("Recaudación total: $" + recaudacion);
        System.out.println("Instalaciones reservadas:");

        for (Instalacion inst : instalacionesReservadas) {
            System.out.println("- " + inst.getNombre());
        }
    }

    public int getCantNuevosSocios() {
        return cantNuevosSocios;
    }

    public void setCantNuevosSocios(int cantNuevosSocios) {
        this.cantNuevosSocios = cantNuevosSocios;
    }

    public List<Instalacion> getInstalacionesReservadas() {
        return instalacionesReservadas;
    }

    public void setInstalacionesReservadas(List<Instalacion> instalacionesReservadas) {
        this.instalacionesReservadas = instalacionesReservadas;
    }

    public double getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(double recaudacion) {
        this.recaudacion = recaudacion;
    }
}
