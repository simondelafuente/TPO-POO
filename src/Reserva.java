import java.util.List;

public class Reserva {
    Socio socio;
    List<Instalacion> instalaciones;
    String fecha;
    String hora;
    double precio;
    int cantSocios;

    public Reserva(Socio socio, List<Instalacion> instalaciones, String fecha, String hora, double precio, int cantSocios) {
        this.socio = socio;
        this.instalaciones = instalaciones;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.cantSocios = cantSocios;
    }

    //Suma total de las instalaciones reservadas por un socio
    public double calcularPrecioTotal() {
        //En caso de tener mas de una instalacion consigo y sumo el precio de cada una
        double precioTotal = 0;
        for (Instalacion instalacion : instalaciones) {
            precioTotal += instalacion.getPrecio();
        }

        //si el socio es vitalicio tiene un descuento en su reserva
        if (socio instanceof SocioVitalicio) {
            SocioVitalicio socioVitalicio = (SocioVitalicio) socio;
            double descuento = precioTotal * (socioVitalicio.getBeneficio());
            precioTotal -= descuento;
        }
        return precioTotal;
    }

    public void agregarInstalacion(Instalacion instalacion) {
        instalaciones.add(instalacion);
    }

    public Socio getSocio() {
        return socio;
    }

    public List<Instalacion> getInstalaciones() {
        return instalaciones;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public double getPrecioTotal() {
        return precio;
    }
}
