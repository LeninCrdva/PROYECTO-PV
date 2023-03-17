package Modelo;

public class Det_reserva {
    private int id_rha;
    private int idReserva_rha;
    private int idHabitacion_rha;
    private int idCliente_rha;

    public Det_reserva() {
    }

    public Det_reserva(int id_rha, int idReserva_rha, int idHabitacion_rha, int idCliente_rha) {
        this.id_rha = id_rha;
        this.idReserva_rha = idReserva_rha;
        this.idHabitacion_rha = idHabitacion_rha;
        this.idCliente_rha = idCliente_rha;
    }

    public int getId_rha() {
        return id_rha;
    }

    public void setId_rha(int id_rha) {
        this.id_rha = id_rha;
    }

    public int getIdReserva_rha() {
        return idReserva_rha;
    }

    public void setIdReserva_rha(int idReserva_rha) {
        this.idReserva_rha = idReserva_rha;
    }

    public int getIdHabitacion_rha() {
        return idHabitacion_rha;
    }

    public void setIdHabitacion_rha(int idHabitacion_rha) {
        this.idHabitacion_rha = idHabitacion_rha;
    }

    public int getIdCliente_rha() {
        return idCliente_rha;
    }

    public void setIdCliente_rha(int idCliente_rha) {
        this.idCliente_rha = idCliente_rha;
    }
}
