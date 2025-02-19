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

    //metodo para que el empleado cargue los datos de un nuevo socio
    public void cargarSocio() throws IOException {
        float cuotaBase = 10000;
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

            nuevoSocio = new SocioVitalicio(nombre, fechaInscripcion, nroDNI);
            System.out.println("Monto a abonar: 0");
        } else if (tipoSocio == 2) {
            System.out.println("El socio es menor? (1 - Sí, 2 - No):");
            int esMenor = scanner.nextInt();
            float cuotaFinal = (esMenor == 1) ? cuotaBase * 0.5f : cuotaBase;

            nuevoSocio = new SocioComun(nombre, fechaInscripcion, nroDNI, cuotaFinal);
            System.out.println("Monto a abonar: " + String.format("%.2f", cuotaFinal));
        } else {
            System.out.println("Opción no válida.");
            return;
        }

        if (cargarPago()){
            guardarSocioEnArchivo(archivo, nuevoSocio);
            System.out.println("Socio registrado con éxito: " + nuevoSocio.getNombre());
        }
        else {
            System.out.println("Socio no registrado, por favor reintente el registro");
        }
    }

    //guarda los datos de los socios en el archivo socios.txt
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

    //lee los datos de los socios en el archivo socios.txt
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
                    socios.add(new SocioVitalicio(nombre, fechaInscripcion, nroDNI));
                } else if (tipo.equals("COMUN")) {
                    float cuota = Float.parseFloat(datos[4]);
                    socios.add(new SocioComun(nombre, fechaInscripcion, nroDNI, cuota));
                }
            }
        }
        br.close();
        return socios;
    }

    //metodo para que el empleado cargue las instalaciones nuevas
    public void cargarInstalacion() throws IOException {
        Scanner scanner = new Scanner(System.in);
        File archivo = new File("instalaciones.txt");

        System.out.println("Ingrese el nombre de la instalación: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la capacidad de la instalación: ");
        int capacidad = scanner.nextInt();
        System.out.println("Ingrese el precio de la instalación: ");
        double precio = scanner.nextDouble();

        Instalacion nuevaInstalacion = new Instalacion(nombre, capacidad, precio);
        guardarInstalacionEnArchivo(archivo, nuevaInstalacion);

        System.out.println("Instalación creada con éxito: " + nuevaInstalacion.getNombre());
    }

    //guarda las instalaciones nuevas en el archivo instalaciones.txt
    private void guardarInstalacionEnArchivo(File archivo, Instalacion nuevaInstalacion) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));

        bw.write( nuevaInstalacion.getNombre() + "," + nuevaInstalacion.getCapacidad() + "," + nuevaInstalacion.getPrecio());

        bw.newLine();
        bw.flush();
        bw.close();
    }

    //lee las instalaciones desde el archivo instalaciones.txt
    public List<Instalacion> cargarInstalacionesDesdeArchivo() throws IOException {
        List<Instalacion> instalaciones = new ArrayList<>();
        File archivo = new File("instalaciones.txt");

        if (!archivo.exists()) {
            return instalaciones;
        }

        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");

            if (datos.length <= 5) {
                String nombre = datos[0];
                int capacidad = Integer.parseInt(datos[1]);
                double precio = Double.parseDouble(datos[2]);
                //boolean disponible = Boolean.parseBoolean(datos[3]);

                instalaciones.add(new Instalacion(nombre, capacidad, precio));
            }
        }
        br.close();
        return instalaciones;
    }

    //metodo para que el empleado cargue una reserva
    public void cargarReserva(List<Socio> socios, List<Instalacion> instalaciones) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el DNI del socio: ");
        String dni = scanner.nextLine();
        Socio socio = socios.stream().filter(s -> s.getNroDNI().equals(dni)).findFirst().orElse(null);

        if (socio == null) {
            System.out.println("No se encontró un socio con ese DNI.");
            return;
        }

        List<Instalacion> instalacionesSeleccionadas = new ArrayList<>();
        try {
            instalaciones = cargarInstalacionesDesdeArchivo();

            if (instalaciones.isEmpty()) {
                System.out.println("No hay instalaciones disponibles.");
                return;
            }

            while (true) {
                System.out.println("Seleccione una instalación (ingrese 0 para terminar):");
                for (int i = 0; i < instalaciones.size(); i++) {
                    System.out.println((i + 1) + ". " + instalaciones.get(i).getNombre() + " - Precio: " + instalaciones.get(i).getPrecio());
                }

                String input = scanner.nextLine();

                if (input.equals("0")) {
                    break;
                }

                try {
                    int index = Integer.parseInt(input) - 1;
                    if (index >= 0 && index < instalaciones.size()) {
                        instalacionesSeleccionadas.add(instalaciones.get(index));
                        System.out.println("Instalación agregada: " + instalaciones.get(index).getNombre());
                    } else {
                        System.out.println("Opción inválida.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida.");
                }
            }

            System.out.println("Instalaciones seleccionadas:");
            for (Instalacion inst : instalacionesSeleccionadas) {
                System.out.println(inst.getNombre() + " - Precio: " + inst.getPrecio());
            }

        } catch (IOException e) {
            System.out.println("Error al cargar las instalaciones: " + e.getMessage());
        }

        System.out.println("Ingrese la fecha de la reserva (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.println("Ingrese la hora de la reserva (HH:MM): ");
        String hora = scanner.nextLine();
        System.out.println("Ingrese la cantidad de socios que asistirán: ");
        int cantSocios = scanner.nextInt();
        scanner.nextLine();

        Reserva reserva = new Reserva(socio, instalacionesSeleccionadas, fecha, hora, 0, cantSocios);
        double precioTotal = reserva.calcularPrecioTotal();

        System.out.println("Precio total de la reserva: " + String.format("%.2f", precioTotal));

        if (cargarPago()){
            guardarReservaEnArchivo(reserva);
            System.out.println("Reserva guardada con éxito");
        }
        else {
            System.out.println("Reserva no guardada, por favor reintente la reserva");
        }
    }

    //guarda las reservas en el archivo reservas.txt
    private void guardarReservaEnArchivo(Reserva reserva) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservas.txt", true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(reserva.getSocio().getNroDNI()).append(",");
            sb.append(reserva.getFecha()).append(",");
            sb.append(reserva.getHora()).append(",");

            for (Instalacion inst : reserva.getInstalaciones()) {
                sb.append(inst.getNombre()).append(" - " ).append(inst.getPrecio()).append(",");
            }

            sb.append(String.format("%d", (long) reserva.calcularPrecioTotal()));

            writer.write(sb.toString());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error al guardar la reserva en el archivo: " + e.getMessage());
        }
    }

    public boolean cargarPago(){
        Scanner scanner = new Scanner(System.in);
        String pagoValido = "";
        while (true) {
            System.out.println("\nSeleccione un método de pago: ");
            System.out.println("1 - Tarjeta de Credito/Debito");
            System.out.println("2 - Transferencia");
            System.out.println("3 - Efectivo");
            System.out.println("4 - Cancelar Pago");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Pase la tarjeta por el lector : ");
                    System.out.println("Llegó el pago? (1 - Sí, 2 - No): ");
                    pagoValido = scanner.nextLine();
                        if (pagoValido.equals("1")) {
                            return true;
                        }
                        else if (pagoValido.equals("2")){
                            System.out.println("Error en el pago");
                            return false;
                        }
                    break;
                case 2:
                    System.out.println("Por favor transfiera a CLUBEMERGENTE.MP : ");
                    System.out.println("\nLlegó el pago? (1 - Sí, 2 - No) : ");
                    pagoValido = scanner.nextLine();
                        if (pagoValido.equals("1")) {
                            return true;
                        }
                        else if (pagoValido.equals("2")){
                            System.out.println("Error en el pago");
                            return false;
                        }
                    break;
                case 3:
                    System.out.println("\nRecibió el pago en efectivo?(1 - Sí, 2 - No): ");
                    pagoValido = scanner.nextLine();
                        if (pagoValido.equals("1")) {
                            return true;
                        }
                        else if (pagoValido.equals("2")){
                            System.out.println("Error en el pago");
                            return false;
                        }
                    break;
                case 4:
                    return false;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    //genera un informe diario
    public Informe generarInforme() throws IOException {
        int cantNuevosSocios = 0;
        List<Instalacion> instalacionesReservadas = new ArrayList<>();
        double recaudacionReservas = 0.0;
        double recaudacionCuotas = 0.0;

        File archivoSocios = new File("socios.txt");
        File archivoReservas = new File("reservas.txt");

        if (archivoSocios.exists() || archivoReservas.exists()) {
            try (BufferedReader brSocios = archivoSocios.exists() ? new BufferedReader(new FileReader(archivoSocios)) : null;
                 BufferedReader brReservas = archivoReservas.exists() ? new BufferedReader(new FileReader(archivoReservas)) : null) {

                String linea;

                // Procesar socios
                if (brSocios != null) {
                    while ((linea = brSocios.readLine()) != null) {
                        String[] datos = linea.split(",");
                        if (datos.length >= 5) {
                            cantNuevosSocios++;
                            recaudacionCuotas += Double.parseDouble(datos[4]);
                        }
                    }
                }

                // Procesar reservas
                if (brReservas != null) {
                    while ((linea = brReservas.readLine()) != null) {
                        String[] datos = linea.split(",");

                        if (datos.length >= 5) {
                            for (int i = 3; i < datos.length - 1; i++) {
                                String[] instalacionData = datos[i].split("-");
                                if (instalacionData.length == 2) {
                                    String nombre = instalacionData[0];
                                    double precio = Double.parseDouble(instalacionData[1].replace("(", "").replace(")", ""));
                                    instalacionesReservadas.add(new Instalacion(nombre, precio));
                                }
                            }
                            recaudacionReservas += Double.parseDouble(datos[datos.length - 1]);
                        }
                    }
                }
            }

            // Vacia los archivos luego de usarlos
            /*try (PrintWriter writerSocios = new PrintWriter(new FileWriter(archivoSocios));
                 PrintWriter writerReservas = new PrintWriter(new FileWriter(archivoReservas))) {
                writerSocios.print("");
                writerReservas.print("");
            }*/
        }

        double recaudacionTotal = recaudacionReservas + recaudacionCuotas;
        return new Informe(cantNuevosSocios, instalacionesReservadas, recaudacionTotal);
    }
}

