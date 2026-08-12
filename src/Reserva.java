import java.util.Date;

public class Reserva {

    private int idReserva;
    private Date dataReserva;
    private String statusReserva;

    public Reserva(int idReserva, Date dataReserva, String statusReserva) {
        this.idReserva = idReserva;
        this.dataReserva = dataReserva;
        this.statusReserva = statusReserva;
    }

    public void cancelarReserva() {
        this.statusReserva = "Cancelada";
        System.out.println("Reserva cancelada.");
    }

    public void exibirDados() {
        System.out.println("ID Reserva: " + idReserva);
        System.out.println("Data Reserva: " + dataReserva);
        System.out.println("Status: " + statusReserva);
    }
}