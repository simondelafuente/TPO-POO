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
