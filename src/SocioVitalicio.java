/* Clase para socios vitalicios, estos no pagan cuota y tienen beneficios para reservar instalaciones */

public class SocioVitalicio extends Socio {
    private float beneficio;

    public SocioVitalicio(String nombre, String fechaInscripcion, String nroDNI, float beneficio) {
        super(nombre, fechaInscripcion, nroDNI);
        this.beneficio = beneficio;
    }

    public float calcularCuota() {
        return 0; // Socios vitalicios no pagan cuota
    }

    public float getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(float beneficio) {
        this.beneficio = beneficio;
    }

    @Override
    public String toString() {
        return "SOCIO_VITALICIO," + getNombre() + "," + getFechaInscripcion() + "," + getNroDNI() + "," + beneficio;
    }
}
