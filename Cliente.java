import java.io.Serializable;

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
}
