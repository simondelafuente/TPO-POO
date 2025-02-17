/* Clase para socios vitalicios, estos no pagan cuota y tienen beneficios para reservar instalaciones */

public class SocioVitalicio extends Socio {
    private float beneficio = 0.8F;

    public SocioVitalicio(String nombre, String fechaInscripcion, String nroDNI) {
        super(nombre, fechaInscripcion, nroDNI);
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
