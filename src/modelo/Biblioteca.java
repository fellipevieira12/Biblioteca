package modelo;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Livro> acervo = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    public void adicionarLivro(Livro livro) {
        acervo.add(livro);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public List<Livro> getAcervo() {
        return acervo;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "totalLivros=" + acervo.size() +
                ", totalUsuarios=" + usuarios.size() +
                ", totalEmprestimos=" + emprestimos.size() +
                ", totalReservas=" + reservas.size() +
                '}';
    }
}