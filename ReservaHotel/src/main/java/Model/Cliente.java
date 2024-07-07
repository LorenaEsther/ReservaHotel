package Model;

public class Cliente {
    private int clienteID;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
 
    public Cliente() {
    }
 
    public Cliente(int clienteID, String nombre, String apellido, String dni, String email) {
        this.clienteID = clienteID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
    }
 
    public int getClienteID() {
        return clienteID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }
 
    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 
    @Override
    public String toString() {
        return "Cliente{" +
                "clienteID=" + clienteID +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
