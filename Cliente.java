package taqueria;

public class Cliente extends Usuario{
    
    //Atributos de la clase Cliente
    private int tarjeta;
    private String domicilio;
    
    //Método constructor
    public Cliente(String nombre, String correo, int telefono, int tarjeta, String domicilio)
    {
           super(nombre, correo, telefono);
           this.tarjeta=tarjeta;
           this.domicilio=domicilio;
    }
    
    /*
    public void hacerPedido()
    {
        
    }
    
    public void historialPedidos()
    {
        
    }*/
}
