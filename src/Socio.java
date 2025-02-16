//Clase abstracta para socios
public abstract class Socio {
    private String nombre;
    private String fechaInscripcion;
    private String nroDNI;

    public Socio(String nombre, String fechaInscripcion, String nroDNI) {
        this.nombre = nombre;
        this.fechaInscripcion = fechaInscripcion;
        this.nroDNI = nroDNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getNroDNI() {
        return nroDNI;
    }

    public void setNroDNI(String nroDNI) {
        this.nroDNI = nroDNI;
    }

    @Override
    public String toString() {
        return "SOCIO," + nombre + "," + fechaInscripcion + "," + nroDNI;
    }
}
