package ejemplotaqueria;
import java.util.Scanner;

public class EjemploTaqueria {
    public static void main(String[] args) {
        Taqueria taqueria = new Taqueria();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Bienvenido a la Taquería");
            System.out.println("1. Administrador");
            System.out.println("2. Cliente");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                    // Lógica del administrador
                    taqueria.menuAdministrador();
                    break;
                case 2:
                    // Lógica del cliente
                    boolean stat = true;
                    while (stat) {
                        System.out.println("Bienvenido a la Taquería");
                        System.out.println("1. Iniciar Sesión");
                        System.out.println("2. Registrarse");
                        System.out.println("3. Salir");
                        System.out.print("Seleccione una opción: ");
                        int opc = scanner.nextInt();
                        scanner.nextLine();  // Consumir nueva línea

                        switch (opc) {
                            case 1:
                                Cliente cliente = taqueria.iniciarSesionCliente();
                                if (cliente != null) {
                                    taqueria.menuCliente(cliente, taqueria);
                                }
                                break;
                            case 2:
                                taqueria.registrarCliente();
                                break;
                            case 3:
                                System.out.println("¡Gracias por visitar la Taquería! ¡Hasta luego!");
                                stat = false;
                            default:
                                System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                        }
                    }
                    break;
                case 3:
                    System.out.println("¡Gracias por visitar la Taquería! ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }
}
