/*Clase que representa las instalaciones del club*/

public class Instalacion {
    private String nombre;
    private int capacidad;
    private double precio;
    private boolean disponible;

    public Instalacion(String nombre, int capacidad, double precio) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precio = precio;
        this.disponible = true;
    }

    public Instalacion(String nombre, int capacidad, double precio, boolean disponible) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precio = precio;
        this.disponible = disponible;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
