package ejemplotaqueria;
/*
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Pedido implements Serializable{
     private static int contadorPedidos = 1;
    private int id;
    private Cliente cliente;
    private List<Producto> productos;
    private String formaEntrega;
    private String formaPago;
    private Date fechaPedido;
    private Date horaPedido;
    private Date horaEstimada;
    private Date horaEntrega;
    private String status;

    public Pedido(Cliente cliente, List<Producto> productos, String formaEntrega, String formaPago) {
        this.id = contadorPedidos++;
        this.cliente = cliente;
        this.productos = productos;
        this.formaEntrega = formaEntrega;
        this.formaPago = formaPago;
        this.fechaPedido = new Date(); // Fecha actual
        this.horaPedido = new Date();  // Hora actual
        this.status = "Pendiente";

        // Calcular horaEstimada como 30 minutos después de la hora actual
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());  // Establecer la hora actual
        calendar.add(Calendar.MINUTE, 30);  // Sumar 30 minutos
        this.horaEstimada = calendar.getTime();
    }
    // Getters y setters

    public List<Producto> getProductos() {
        return productos;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public Date getHoraEntrega() {
        return horaEntrega;
    }

    public Date getHoraEstimada() {
        return horaEstimada;
    }

    public Date getHoraPedido() {
        return horaPedido;
    }

    public static int getContadorPedidos() {
        return contadorPedidos;
    }

    public int getId() {
        return id;
    }

    public String getFormaEntrega() {
        return formaEntrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHoraEntrega(Date horaEntrega) {
        this.horaEntrega = horaEntrega;
    }
}*/
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Pedido implements Serializable {
    private static int contadorPedidos = 1;
    private int id;
    private Cliente cliente;
    private List<Producto> productos;
    private String formaEntrega;
    private String formaPago;
    private Date fechaPedido;
    private Date horaPedido;
    private Date horaEstimada;
    private Date horaEntrega;
    private String status;
    private double total;

    public Pedido(Cliente cliente, List<Producto> productos, String formaEntrega, String formaPago) {
        this.id = contadorPedidos++;
        this.cliente = cliente;
        this.productos = productos;
        this.formaEntrega = formaEntrega;
        this.formaPago = formaPago;
        this.fechaPedido = new Date(); // Fecha actual
        this.horaPedido = new Date();  // Hora actual
        this.status = "Pendiente";

        // Calcular horaEstimada como 30 minutos después de la hora actual
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());  // Establecer la hora actual
        calendar.add(Calendar.MINUTE, 30);  // Sumar 30 minutos
        this.horaEstimada = calendar.getTime();
    }
    // Getters y setters

    public void calcularTotal() {
        double nuevoTotal = 0;
        for (Producto producto : productos) {
            nuevoTotal += producto.getPrecio();
        }
        this.total = nuevoTotal;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public Date getHoraEntrega() {
        return horaEntrega;
    }

    public Date getHoraEstimada() {
        return horaEstimada;
    }

    public Date getHoraPedido() {
        return horaPedido;
    }

    public static int getContadorPedidos() {
        return contadorPedidos;
    }

    public int getId() {
        return id;
    }

    public String getFormaEntrega() {
        return formaEntrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHoraEntrega(Date horaEntrega) {
        this.horaEntrega = horaEntrega;
    }
}
