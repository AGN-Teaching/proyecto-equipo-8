package ejemplotaqueria;
import java.io.*;
import java.util.*;

public class Taqueria {
    private Map<String, Cliente> clientes;
    private List<Producto> menu;
    private List<Pedido> pedidos;

    public Taqueria() {
        this.clientes = new HashMap<>();
        this.menu = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public void menuCliente(Cliente cliente, Taqueria taqueria) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bienvenido, " + cliente.getNombre() + "!");
            System.out.println("1. Hacer pedido");
            System.out.println("2. Estado del pedido");
            System.out.println("3. Historial de pedidos");
            System.out.println("4. Ver menú");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                    // Lógica para hacer un pedido
                    cliente.hacerPedido(menu, taqueria);
                    break;
                case 2:
                    // Lógica para verificar el estado del pedido del cliente
                    cliente.verEstadoPedido(cliente, taqueria);
                    break;
                case 3:
                    cliente.historialDePedidos(taqueria);
                    break;
                case 4:
                    // Lógica para mostrar el menú
                    mostrarMenu();
                    break;
                case 5:
                    System.out.println("¡Gracias por visitarnos, " + cliente.getNombre() + "! Hasta luego.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
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
            System.out.println("8. Ver menú");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                    // Lógica para registrar pedidos
                    registrarPedido();
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
                    System.out.println("Ingresa el telefono del cliente a buscar: ");
                    String numeroDeTelefono = scanner.nextLine(); // Número de teléfono a buscar
                    Cliente clienteBuscado = buscarClientePorTelefono(numeroDeTelefono);

                    if (clienteBuscado != null) {
                        System.out.println("Cliente encontrado: " + clienteBuscado.getNombre());
                    }
                    break;
                case 7:
                    // Lógica para marcar un pedido como entregado
                    System.out.println("Ingresa el telefono del cliente a buscar: ");
                    String numPedido = scanner.nextLine();

                    if (pedidos.isEmpty()) {
                        System.out.println("No hay pedidos registrados.");
                    } else {
                        System.out.println("Pedidos asociados al cliente con número de teléfono " + numPedido + ":");
                        for (Pedido pedido : pedidos) {
                            if (pedido.getCliente().getTelefono().equals(numPedido)&& pedido.getStatus().equals("Pendiente")) {
                                System.out.println("ID del Pedido: " + pedido.getId());
                                System.out.println("Fecha del Pedido: " + pedido.getFechaPedido());
                                System.out.println("Forma de Entrega: " + pedido.getFormaEntrega());
                                System.out.println("Forma de Pago: " + pedido.getFormaPago());
                                System.out.println("Productos:");
                                for (Producto producto : pedido.getProductos()) {
                                    System.out.println("- " + producto.getNombre() + " - $" + producto.getPrecio());
                                }
                                System.out.println("Estado: " + pedido.getStatus());
                                System.out.println("Total: "+pedido.getTotal());
                                System.out.println("--------------------------------------");
                            }
                        }
                    }
                    System.out.println("Ingresa el ID del pedido que deseas finalizar: ");
                    int idEliminar = scanner.nextInt();
                    scanner.nextLine();
                    Pedido eliminarPedido = buscarPedidoPorId(idEliminar);
                    if (eliminarPedido != null) {
                        System.out.println("Estado del Pedido (ID: " + eliminarPedido.getId() + "):");
                        System.out.println("Fecha del Pedido: " + eliminarPedido.getFechaPedido());
                        System.out.println("Hora del Pedido: " + eliminarPedido.getHoraPedido());
                        System.out.println("Forma de Entrega: " + eliminarPedido.getFormaEntrega());
                        System.out.println("Forma de Pago: " + eliminarPedido.getFormaPago());
                        System.out.println("Productos:");
                        for (Producto producto : eliminarPedido.getProductos()) {
                            System.out.println("- " + producto.getNombre() + " - $" + producto.getPrecio());
                        }
                        System.out.println("Estado: " + eliminarPedido.getStatus());
                    } else {
                        System.out.println("No se ha encontrado el pedido. Verifique el ID");
                    }
                    System.out.println("¿Esta seguro de finalizar este pedido?\t1.Si\t2.No");
                    int confirmacion = scanner.nextInt();
                    scanner.nextLine();
                    if (confirmacion == 1){
                        finalizarPedido(idEliminar);
                        System.out.println("Se ha finalizado el pedido.");
                    }else {
                        System.out.println("Finalización cancelada.");
                    }
                    break;
                case 8:
                    mostrarMenu();
                    break;
                case 9:
                    System.out.println("Sesión de administrador cerrada. Hasta luego.");
                    return;
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
    public void registrarPedido() {
        Scanner scanner = new Scanner(System.in);
        List<Producto> productosSeleccionados = new ArrayList<>();

        // Buscar el cliente por su número de teléfono
        System.out.println("Por favor, ingrese el número de teléfono del cliente:");
        String telefonoCliente = scanner.nextLine();

        Cliente cliente = buscarClientePorTelefono(telefonoCliente);
        if (cliente != null) {
            // Mostrar el menú para que el administrador seleccione productos
            mostrarMenu();

            // Lógica para seleccionar productos, forma de pago y forma de entrega
            boolean continuarSeleccionando = true;
            while (continuarSeleccionando) {
                System.out.println("Por favor, ingrese el número del producto que desea agregar al pedido (0 para finalizar):");
                int seleccion = scanner.nextInt();

                if (seleccion >= 1 && seleccion <= menu.size()) {
                    Producto productoSeleccionado = menu.get(seleccion - 1);
                    productosSeleccionados.add(productoSeleccionado);
                    System.out.println(productoSeleccionado.getNombre() + " ha sido agregado al pedido.");
                } else if (seleccion == 0) {
                    continuarSeleccionando = false;
                } else {
                    System.out.println("Selección inválida. Por favor, ingrese un número válido.");
                }
            }

            String formaPago = seleccionarFormaDePago();
            String formaEntrega = seleccionarFormaDeEntrega();


            // Crear el pedido
            Pedido nuevoPedido = new Pedido(cliente, productosSeleccionados, formaEntrega, formaPago);
            // Agregar el pedido a la lista de pedidos
            agregarPedido(nuevoPedido);

            System.out.println("Pedido registrado correctamente. ID del pedido: " + nuevoPedido.getId());
        } else {
            System.out.println("Cliente no encontrado. Por favor, verifique el número de teléfono.");
        }
    }
    //Metodo Para agregar pedido a List<Pedidos>
    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
        pedido.calcularTotal();
        if (pedido.getFormaEntrega().equals("Domicilio") &&pedido.getTotal() < 200){
            pedido.setTotal(pedido.getTotal()+30);
        }
    }
    public Pedido buscarPedidoPorId(int idPedido) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == idPedido) {
                return pedido;
            }
        }
        return null; // Si no se encuentra el pedido, devuelve null
    }
    public String seleccionarFormaDePago() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor, elija una forma de pago:");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta de crédito");
        System.out.println("3. Transferencia bancaria");

        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                return "Efectivo";
            case 2:
                return "Tarjeta";
            case 3:
                return "Transferencia";
            default:
                System.out.println("Opción inválida. Por favor, seleccione una forma de pago válida.");
                return seleccionarFormaDePago(); // Llamada recursiva en caso de opción inválida
        }
    }
    public String seleccionarFormaDeEntrega() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor, elija una forma de entrega:");
        System.out.println("1. Recoger en la tienda");
        System.out.println("2. Entrega a domicilio");

        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                return "Pick-Up";
            case 2:
                return "Domicilio";
            default:
                System.out.println("Opción inválida. Por favor, seleccione una forma de entrega válida.");
                return seleccionarFormaDeEntrega(); // Llamada recursiva en caso de opción inválida
        }
    }
    public void finalizarPedido(int idEliminar) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == idEliminar) {
                pedido.setStatus("Entregado");
                pedido.setHoraEntrega(new Date());
                System.out.println("Pedido finalizado. ID del pedido finalizado: "+idEliminar);
                return;
            }
        }
        System.out.println("No se encontró ningún pedido con el id " + idEliminar);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}

