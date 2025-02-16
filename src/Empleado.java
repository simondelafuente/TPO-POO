import java.util.*;
import java.io.*;

public class Empleado {
    private String nombre;
    private String fechaNac;
    private String rol;
    private double sueldo;

    public Empleado(String nombre, String fechaNac, String rol, double sueldo) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.rol = rol;
        this.sueldo = sueldo;
    }


    public void cargarSocio() throws IOException {
        Scanner scanner = new Scanner(System.in);
        File archivo = new File("socios.txt");

        System.out.println("Ingrese el nombre del socio:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la fecha de inscripción (YYYY-MM-DD):");
        String fechaInscripcion = scanner.nextLine();

        System.out.println("Ingrese el número de DNI:");
        String nroDNI = scanner.nextLine();

        System.out.println("Seleccione el tipo de socio:");
        System.out.println("1 - Socio Vitalicio");
        System.out.println("2 - Socio Común");
        int tipoSocio = scanner.nextInt();
        scanner.nextLine();

        Socio nuevoSocio;

        if (tipoSocio == 1) {
            System.out.println("Ingrese el porcentaje de beneficio (ej: 10 para 10%):");
            float beneficio = scanner.nextFloat();
            nuevoSocio = new SocioVitalicio(nombre, fechaInscripcion, nroDNI, beneficio);
        } else if (tipoSocio == 2) {
            System.out.println("El socio es menor? (1 - Sí, 2 - No):");
            int esMenor = scanner.nextInt();

            float cuotaBase = 10000;
            float cuotaFinal = (esMenor == 1) ? cuotaBase * 0.5f : cuotaBase;

            nuevoSocio = new SocioComun(nombre, fechaInscripcion, nroDNI, cuotaFinal);
        } else {
            System.out.println("Opción no válida.");
            return;
        }

        guardarSocioEnArchivo(archivo, nuevoSocio);
        System.out.println("Socio registrado con éxito: " + nuevoSocio.getNombre());
    }


    private void guardarSocioEnArchivo(File archivo, Socio socio) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));

        if (socio instanceof SocioVitalicio) {
            SocioVitalicio sv = (SocioVitalicio) socio;
            bw.write("VITALICIO," + sv.getNroDNI() + "," + sv.getNombre() + "," + sv.getFechaInscripcion() + "," + sv.calcularCuota());
        } else if (socio instanceof SocioComun) {
            SocioComun sc = (SocioComun) socio;
            bw.write("COMUN," + sc.getNroDNI() + "," + sc.getNombre() + "," + sc.getFechaInscripcion() + "," + sc.getValorCuota());
        }

        bw.newLine();
        bw.flush();
        bw.close();
    }


    public List<Socio> cargarSociosDesdeArchivo() throws IOException {
        List<Socio> socios = new ArrayList<>();
        File archivo = new File("socios.txt");

        if (!archivo.exists()) {
            return socios;
        }

        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");

            if (datos.length >= 5) {
                String tipo = datos[0];
                String nroDNI = datos[1];
                String nombre = datos[2];
                String fechaInscripcion = datos[3];

                if (tipo.equals("VITALICIO")) {
                    float beneficio = Float.parseFloat(datos[4]);
                    socios.add(new SocioVitalicio(nombre, fechaInscripcion, nroDNI, beneficio));
                } else if (tipo.equals("COMUN")) {
                    float cuota = Float.parseFloat(datos[4]);
                    socios.add(new SocioComun(nombre, fechaInscripcion, nroDNI, cuota));
                }
            }
        }
        br.close();
        return socios;
    }


    public void cargarInstalacion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre de la instalación: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la capacidad de la instalación: ");
        int capacidad = scanner.nextInt();
        System.out.println("Ingrese el precio de la instalación: ");
        double precio = scanner.nextDouble();

        Instalacion instalacion = new Instalacion(nombre, capacidad, precio);
        System.out.println("Instalación creada con éxito: " + instalacion.getNombre());
    }


    public void cargarReserva(List<Socio> socios, List<Instalacion> instalaciones) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el DNI del socio: ");
        String dni = scanner.nextLine();

        Socio socio = socios.stream()
                .filter(s -> s.getNroDNI().equals(dni))
                .findFirst()
                .orElse(null);

        if (socio == null) {
            System.out.println("No se encontró un socio con ese DNI.");
            return;
        }

        List<Instalacion> instalacionesSeleccionadas = new ArrayList<>();
        while (true) {
            System.out.println("Seleccione una instalación (o escriba 'fin' para terminar):");
            for (int i = 0; i < instalaciones.size(); i++) {
                System.out.println((i + 1) + ". " + instalaciones.get(i).getNombre() + " - Precio: " + instalaciones.get(i).getPrecio());
            }
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("fin")) {
                break;
            }
            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < instalaciones.size()) {
                    instalacionesSeleccionadas.add(instalaciones.get(index));
                } else {
                    System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida.");
            }
        }

        System.out.println("Ingrese la fecha de la reserva (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.println("Ingrese la hora de la reserva (HH:MM): ");
        String hora = scanner.nextLine();
        System.out.println("Ingrese la cantidad de socios que asistirán: ");
        int cantSocios = scanner.nextInt();

        Reserva reserva = new Reserva(socio, instalacionesSeleccionadas, fecha, hora, 0, cantSocios);
        double precioTotal = reserva.calcularPrecioTotal();

        System.out.println("Reserva creada con éxito. Precio total: " + precioTotal);
    }

    public void cargarPago(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el método de pago: ");
        String metodo = scanner.nextLine();
        System.out.println("Ingrese el DNI del socio: ");
        String dni = scanner.nextLine();
        System.out.println("Ingrese el monto a abonar: ");
        double precio = scanner.nextDouble();

        Pago pago = new Pago(metodo, dni, precio);
        System.out.println("Pago registrado con éxito para el socio con DNI: " + dni);
    }
}
