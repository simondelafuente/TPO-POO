//Clase para los socios comunes

public class SocioComun extends Socio {
    private float valorCuota;

    public SocioComun(String nombre, String fechaInscripcion, String nroDNI, float valorCuota) {
        super(nombre, fechaInscripcion, nroDNI);
        this.valorCuota = valorCuota;
    }

    public float calcularCuota(boolean esMenor) {
        // Si es menor, paga el 50% de la cuota
        return esMenor ? valorCuota * 0.5f : valorCuota;
    }

    public float getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(float valorCuota) {
        this.valorCuota = valorCuota;
    }

    @Override
    public String toString() {
        return "SOCIO_COMUN," + getNombre() + "," + getFechaInscripcion() + "," + getNroDNI() + "," + valorCuota;
    }
}
