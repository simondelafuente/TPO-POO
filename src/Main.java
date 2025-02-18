import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Crear empleado
        Empleado empleado = new Empleado("Juan Pérez", "1985-06-15", "Administrador", 50000);

        // Listas de prueba para socios e instalaciones
        List<Socio> socios = empleado.cargarSociosDesdeArchivo();
        List<Instalacion> instalaciones;

        // menú de opciones
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1 - Cargar Socio");
            System.out.println("2 - Cargar Instalación");
            System.out.println("3 - Cargar Reserva");
            System.out.println("4 - Cargar Pago");
            System.out.println("5 - Generar informe");
            System.out.println("6 - Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    empleado.cargarSocio();
                    socios = empleado.cargarSociosDesdeArchivo();
                    break;
                case 2:
                    empleado.cargarInstalacion();
                    break;
                case 3:
                    instalaciones = empleado.cargarInstalacionesDesdeArchivo();
                    empleado.cargarReserva(socios, instalaciones);
                    break;
                case 4:
                    empleado.cargarPago();
                    break;
                case 5:
                    Informe informe = empleado.generarInforme();
                    informe.mostrarInforme();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
