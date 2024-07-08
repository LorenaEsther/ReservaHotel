package Model;

import java.util.Date;

public class Reserva {

    private int reservaID;
    private int idcliente;
    private String dni;
    private int habitacionNumero;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;

    public Reserva() {
    }

    public Reserva(int reservaID, String dni, int idcliente, int habitacionNumero, Date fechaInicio, Date fechaFin, String estado) {
        this.reservaID = reservaID;
        this.dni = dni;
        this.idcliente = idcliente;
        this.habitacionNumero = habitacionNumero;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

    public int getReservaID() {
        return reservaID;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public int getHabitacionNumero() {
        return habitacionNumero;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public String getDni() {
        return dni;
    }

    // Setters
    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setReservaID(int reservaID) {
        this.reservaID = reservaID;
    }

    public void setIdCliente(Cliente cliente) {
        this.idcliente = idcliente;
    }

    public void setHabitacionNumero(Habitacion habitacion) {
        this.habitacionNumero = habitacionNumero;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
