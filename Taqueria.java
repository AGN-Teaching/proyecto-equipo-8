package taqueria;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Taqueria {

    //Atributos de la clase Taqueria
    private String nombre;
    private String locacion;
    private int telefono;
       
    public static void main(String[] args){
        
        //Creación del objeto necesario para la lectura de la información
        Scanner leer = new Scanner(System.in);
        
        //Variables que se ocuparan
        String domicilio, correo, nombre;
        int opc, idPedido;
        int numTelefono, numTarjeta;
        
        List<Cliente> registroClientes = new ArrayList<Cliente>();
        List<Administrador> registrarPedido = new ArrayList<Administrador>();
        
        System.out.println("Menu de la Taqueria \"Don Taco\"");
        System.out.println("\nOpcion\tUsuario");
        System.out.println("1\tAdministrador(Registrar Pedido)\n2\tCliente (hacer pedido)");
        System.out.println("\nIngrese la opcion del tipo de usuario: ");
        opc=leer.nextInt();
            if(opc==1)  
            {
                System.out.println("Administrador");
                System.out.println("Ingrese el nombre del cliente: ");
                nombre=leer.nextLine();
                leer.nextLine();
                System.out.println("Ingrese el correo electronioo del cliente: ");
                correo=leer.nextLine();
                leer.nextLine();
                System.out.println("Ingrese el numero de telefono del cliente: ");
                numTelefono=leer.nextInt();
                System.out.println("Ingrese el ID del pedido: ");
                idPedido=leer.nextInt();
                Administrador administrador = new Administrador(nombre, correo, numTelefono, idPedido);
                registrarPedido.add(administrador);
            }
            else if(opc==2)
            { 
                System.out.println("\nRegistro de Clientes");
                System.out.println("Ingrese su nombre: ");
                nombre=leer.nextLine();
                leer.nextLine();
                System.out.println("Ingrese su correo electronioo: ");
                correo=leer.nextLine();
                leer.nextLine();
                System.out.println("Ingrese un numero de telefono: ");
                numTelefono=leer.nextInt();
                System.out.println("Ingrese su numero de tarjeta: ");
                numTarjeta=leer.nextInt();
                System.out.println("Ingrese su domicilio: ");
                domicilio=leer.nextLine();
                leer.nextLine();
                Cliente cliente = new Cliente(nombre, correo, numTelefono, numTarjeta, domicilio);
                registroClientes.add(cliente);
            }
            else
            {
                System.out.println("\nLa opcion ingresada es incorrecta");
            }
               
    }
        
}
