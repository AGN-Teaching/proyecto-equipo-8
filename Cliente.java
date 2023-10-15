import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente implements Serializable {
    private String nombre;
    private String domicilio;
    private String telefono;
    private String contrasena;
    private String correo;
    private String numTarjeta;

    public Cliente(String nombre, String domicilio, String telefono, String contrasena, String correo, String numTarjeta) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.correo = correo;
        this.numTarjeta = numTarjeta;
    }

    // Getters y setters

    public String getContrasena() {
        return contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }
    public boolean comprobarContrasena(String contrasenaIngresada) {
        return this.contrasena.equals(contrasenaIngresada);
    }

    public void hacerPedido(List<Producto> menu, Taqueria taqueria) {
        Scanner scanner = new Scanner(System.in);
        List<Producto> productosSeleccionados = new ArrayList<>();

        System.out.println("Menú:");
        for (int i = 0; i < menu.size(); i++) {
            Producto producto = menu.get(i);
            System.out.println((i + 1) + ". " + producto.getNombre() + " - $" + producto.getPrecio());
        }

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

        String formaPago = taqueria.seleccionarFormaDePago();
        String formaEntrega = taqueria.seleccionarFormaDeEntrega();

        // Crear un objeto Pedido y añadirlo a la lista de pedidos
        Pedido nuevoPedido = new Pedido(this, productosSeleccionados, formaEntrega, formaPago);
        // Agregar nuevoPedido a la lista de pedidos
        taqueria.agregarPedido(nuevoPedido);
        System.out.println("Pedido realizado correctamente. ID del pedido: " + nuevoPedido.getId());
    }

    public void verEstadoPedido(Cliente cliente, Taqueria taqueria) {
        Pedido pedido = taqueria.buscarPedidoPorTelefono(cliente.telefono);

        if (pedido != null) {
            System.out.println("Estado del Pedido (ID: " + pedido.getId() + "):");
            System.out.println("Fecha del Pedido: " + pedido.getFechaPedido());
            System.out.println("Hora del Pedido: " + pedido.getHoraPedido());
            System.out.println("Forma de Entrega: " + pedido.getFormaEntrega());
            System.out.println("Forma de Pago: " + pedido.getFormaPago());
            System.out.println("Productos:");
            for (Producto producto : pedido.getProductos()) {
                System.out.println("- " + producto.getNombre() + " - $" + producto.getPrecio());
            }
            System.out.println("Estado: " + pedido.getStatus());
        } else {
            System.out.println("Pedido no encontrado.");
        }
    }
}
