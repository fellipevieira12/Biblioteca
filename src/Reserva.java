import java.util.Date;

public class Reserva {

    private int idReserva;
    private Date dataReserva;
    private StatusReserva statusReserva;

    public Reserva(int idReserva, Date dataReserva, StatusReserva statusReserva) {
        this.idReserva = idReserva;
        this.dataReserva = dataReserva;
        this.statusReserva = statusReserva;
    }

    public void cancelarReserva() {
        this.statusReserva = StatusReserva.CANCELADA;
        System.out.println("Reserva cancelada.");
    }

    @Override
    public String toString() {
        return "Reserva [idReserva=" + idReserva +
                ", dataReserva=" + dataReserva +
                ", statusReserva=" + statusReserva + "]";
    }
}