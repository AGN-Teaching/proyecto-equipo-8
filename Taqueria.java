import java.io.*;
import java.util.*;

public class Taqueria {
    private Map<String, Cliente> clientes;
    private List<Producto> menu;
    private List<Pedido> pedidos;
    Taqueria taqueria = new Taqueria();

    public Taqueria() {
        this.clientes = new HashMap<>();
        this.menu = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    // Métodos para gestionar clientes, productos y pedidos

    public void main(String[] args) {
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
                    menuAdministrador();
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
                                Cliente cliente = iniciarSesionCliente();
                                if (cliente != null) {
                                    menuCliente(cliente);
                                }
                                break;
                            case 2:
                                registrarCliente();
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
    public Cliente iniciarSesionCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su número de teléfono: ");
        String telefono = scanner.nextLine();

        if (clientes.containsKey(telefono)) {
            Cliente cliente = clientes.get(telefono);
            System.out.print("Ingrese su contraseña: ");
            String contraseña = scanner.nextLine();

            if (cliente.comprobarContrasena(contraseña)) {
                System.out.println("Inicio de sesión exitoso. Bienvenido, " + cliente.getNombre() + "!");
                return cliente;
            } else {
                System.out.println("Contraseña incorrecta. Inicio de sesión fallido.");
            }
        } else {
            System.out.println("Número de teléfono no registrado. Por favor, regístrese primero.");
        }

        return null;
    }

    public void registrarCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su domicilio: ");
        String domicilio = scanner.nextLine();
        System.out.print("Ingrese su número de teléfono: ");
        String telefono = scanner.nextLine();

        // Verificar si el número de teléfono ya está registrado
        if (clientes.containsKey(telefono)) {
            System.out.println("El número de teléfono ya está registrado. Por favor, inicie sesión.");
        } else {
            System.out.print("Ingrese su contraseña: ");
            String contraseña = scanner.nextLine();
            System.out.print("Ingrese su correo electrónico: ");
            String correo = scanner.nextLine();
            System.out.print("Ingrese su número de tarjeta bancaria: ");
            String numTarjeta = scanner.nextLine();

            // Crear un nuevo cliente y agregarlo al mapa de clientes
            Cliente nuevoCliente = new Cliente(nombre, domicilio, telefono, contraseña, correo, numTarjeta);
            clientes.put(telefono, nuevoCliente);

            System.out.println("Registro exitoso. ¡Bienvenido, " + nuevoCliente.getNombre() + "!");
        }
    }


    public void menuCliente(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bienvenido, " + cliente.getNombre() + "!");
            System.out.println("1. Hacer pedido");
            System.out.println("2. Estado del pedido");
            System.out.println("3. Ver menú");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                    // Lógica para hacer un pedido
                    break;
                case 2:
                    // Lógica para verificar el estado del pedido del cliente
                    break;
                case 3:
                    // Lógica para mostrar el menú
                    mostrarMenu();
                    break;
                case 4:
                    System.out.println("¡Gracias por visitarnos, " + cliente.getNombre() + "! Hasta luego.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }
    public void mostrarMenu() {
        if (menu.isEmpty()) {
            System.out.println("El menú está vacío. Por favor, comuníquese con el administrador.");
        } else {
            System.out.println("---- Menú ----");
            for (int i = 0; i < menu.size(); i++) {
                Producto producto = menu.get(i);
                System.out.println((i + 1) + ". " + producto.getNombre() + " - $" + producto.getPrecio());
                System.out.println("   Descripción: " + producto.getDescripcion());
            }
        }
    }


    public void menuAdministrador() {
        Scanner scanner = new Scanner(System.in);
        String contraseñaAdmin = "admin123";

        System.out.print("Ingrese la contraseña del administrador: ");
        String contraseña = scanner.nextLine();

        if (!contraseña.equals(contraseñaAdmin)) {
            System.out.println("Contraseña incorrecta. Acceso denegado.");
            return;
        }

        while (true) {
            System.out.println("Bienvenido, Administrador!");
            System.out.println("1. Registrar pedidos");
            System.out.println("2. Agregar productos");
            System.out.println("3. Cargar menú desde archivo");
            System.out.println("4. Eliminar productos");
            System.out.println("5. Guardar menú en archivo");
            System.out.println("6. Buscar cliente");
            System.out.println("7. Entregar pedido");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                    // Lógica para registrar pedidos
                    break;
                case 2:
                    // Lógica para agregar nuevos productos al menú
                    agregarProducto();
                    break;
                case 3:
                    // Lógica para cargar datos del menú desde un archivo
                    cargarMenuDesdeArchivo();
                    break;
                case 4:
                    // Lógica para eliminar productos del menú}
                    eliminarProducto();
                    break;
                case 5:
                    // Lógica para guardar datos del menú en un archivo
                    guardarMenuEnArchivo();
                    break;
                case 6:
                    // Lógica para buscar clientes por número de teléfono
                    System.out.println("Ingresa el telefono del cliente a buscar:\t");
                    String numeroDeTelefono = scanner.nextLine(); // Número de teléfono a buscar
                    Cliente clienteBuscado = taqueria.buscarClientePorTelefono(numeroDeTelefono);

                    if (clienteBuscado != null) {
                        System.out.println("Cliente encontrado: " + clienteBuscado.getNombre());
                    }
                    break;
                case 7:
                    // Lógica para marcar un pedido como entregado
                    break;
                case 8:
                    System.out.println("Sesión de administrador cerrada. Hasta luego.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }
    public void agregarProducto() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();  // Consumir nueva línea
        System.out.print("Ingrese la descripción del producto: ");
        String descripcion = scanner.nextLine();

        Producto nuevoProducto = new Producto(nombre, precio, descripcion);
        menu.add(nuevoProducto);

        System.out.println("Producto agregado correctamente al menú.");
    }

    public void eliminarProducto() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del producto que desea eliminar: ");
        String nombreProducto = scanner.nextLine();

        // Usa un iterator para recorrer la lista y eliminar el producto si se encuentra
        Iterator<Producto> iterator = menu.iterator();
        boolean productoEncontrado = false;

        while (iterator.hasNext()) {
            Producto producto = iterator.next();
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                iterator.remove();
                productoEncontrado = true;
                System.out.println("Producto eliminado correctamente del menú.");
                break;
            }
        }

        if (!productoEncontrado) {
            System.out.println("No se encontró un producto con el nombre especificado en el menú.");
        }
    }
    public void guardarMenuEnArchivo() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("menu.dat"))) {
            // Guarda la lista de productos en el archivo
            outputStream.writeObject(menu);
            System.out.println("Datos del menú guardados correctamente en menu.dat.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos del menú: " + e.getMessage());
        }
    }

    public void cargarMenuDesdeArchivo() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("menu.dat"))) {
            // Lee la lista de productos desde el archivo
            menu = (List<Producto>) inputStream.readObject();
            System.out.println("Datos del menú cargados correctamente desde menu.dat.");
        } catch (FileNotFoundException e) {
            System.out.println("El archivo menu.dat no se encuentra. Se creará uno nuevo al guardar el menú.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos del menú: " + e.getMessage());
        }
    }
    public Cliente buscarClientePorTelefono(String telefono) {
        if (clientes.containsKey(telefono)) {
            return clientes.get(telefono);
        } else {
            System.out.println("No se encontró ningún cliente con el número de teléfono proporcionado.");
            return null;
        }
    }
}
