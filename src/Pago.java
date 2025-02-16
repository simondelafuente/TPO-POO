/*clase para registrar el pago de un socio*/
public class Pago {
    private String metodo;
    private String socio;
    private double monto;

    public Pago(String metodo, String socio, double monto) {
        this.metodo = metodo;
        this.socio = socio;
        this.monto = monto;
    }

    public String getMetodo() {
        return metodo;
    }
    public void setMetodo(){
        this.metodo = metodo;
    }

    public String getSocio() {
        return socio;
    }
    public void setSocio(String socio) {
        this.socio = socio;
    }

    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
}
