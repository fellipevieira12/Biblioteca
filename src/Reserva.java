import java.util.Date;

public class Reserva {

    int idReserva;

    Livro livro;
    Usuario usuario;

    Date dataReserva;
    String status;

    void cancelarReserva() {
        this.status = "Cancelada";
        System.out.println("Reserva cancelada.");
    }

    void exibirDados() {
        System.out.println("ID Reserva: " + idReserva);
        System.out.println("Livro: " + (livro != null ? livro.titulo : "-"));
        System.out.println("Usuário: " + (usuario != null ? usuario.nome : "-"));
        System.out.println("Data Reserva: " + dataReserva);
        System.out.println("Status: " + status);
    }
}
