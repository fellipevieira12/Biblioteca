package modelo;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Reserva {

    private int idReserva;
    private Date dataReserva;
    private StatusReserva statusReserva;

    private Livro livro;
    private Usuario usuario;

    public Reserva(int idReserva, Livro livro, Usuario usuario, Date dataReserva) {
        this.idReserva = idReserva;
        this.livro = livro;
        this.usuario = usuario;
        this.dataReserva = dataReserva;
        this.statusReserva = StatusReserva.ATIVA;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public StatusReserva getStatusReserva() {
        return statusReserva;
    }

    public void setStatusReserva(StatusReserva statusReserva) {
        this.statusReserva = statusReserva;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void cancelarReserva() {
        this.statusReserva = StatusReserva.CANCELADA;
        System.out.println("Reserva cancelada.");
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", livro=" + (livro != null ? livro.getTitulo() : "-") +
                ", usuario=" + (usuario != null ? usuario.getNome() : "-") +
                ", dataReserva=" + dataReserva +
                ", statusReserva=" + statusReserva +
                '}';
    }

    public String toCSV() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return idReserva + ";" +
                livro.getIdLivro() + ";" +
                usuario.getIdUsuario() + ";" +
                sdf.format(dataReserva) + ";" +
                statusReserva;
    }
}
