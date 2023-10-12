package taqueria;

public class Administrador extends Usuario{

    //Atributo de la clase Administrador
    private String contrasena;
    
    //Método constructor
    public Administrador(String nombre, String correo, int telefono, int userID)
    {
        super(nombre, correo, telefono);
        this.contrasena=contrasena;
    }
    
   /* public void agregarProducto()
    {
        
    }
    
    public void eliminarProducto()
    {
        
    }
    
    public void listaPedidos()
    {
        
    }*/
}
