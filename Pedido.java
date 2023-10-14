import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Pedido implements Serializable {
    private Cliente cliente;
    private Date fechaHoraPedido;
    private boolean paraRecoger;
    private List<Producto> productos;
    private String formaPago;
    private Date horaEstimadaEntrega;
    private boolean entregado;

    public Pedido(Cliente cliente, boolean paraRecoger, List<Producto> productos, String formaPago, Date horaEstimadaEntrega) {
        this.cliente = cliente;
        this.paraRecoger = paraRecoger;
        this.productos = productos;
        this.formaPago = formaPago;
        this.horaEstimadaEntrega = horaEstimadaEntrega;
        this.fechaHoraPedido = new Date();
        this.entregado = false;
    }

    // Getters y setters
}
