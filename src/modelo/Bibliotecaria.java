package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bibliotecaria {

    private int idBibliotecaria;
    private String nome;

    private Livro[] acervo = new Livro[0];
    private Usuario[] usuarios = new Usuario[0];
    private Emprestimo[] emprestimos = new Emprestimo[0];
    private Reserva[] reservas = new Reserva[0];

    public Bibliotecaria(int idBibliotecaria, String nome) {
        this.idBibliotecaria = idBibliotecaria;
        this.nome = nome;
    }

    public void cadastrarLivro(Livro livro) {
        List<Livro> listaTemp = new ArrayList<>(Arrays.asList(acervo));
        listaTemp.add(livro);
        acervo = listaTemp.toArray(new Livro[0]);
        System.out.println("Livro '" + livro.getTitulo() + "' cadastrado por " + this.nome);
    }

    public void cadastrarUsuario(Usuario usuario) {
        List<Usuario> listaTemp = new ArrayList<>(Arrays.asList(usuarios));
        listaTemp.add(usuario);
        usuarios = listaTemp.toArray(new Usuario[0]);
        System.out.println("Usuário '" + usuario.getNome() + "' cadastrado por " + this.nome);
    }

    public void registrarEmprestimo(Emprestimo emprestimo) {
        List<Emprestimo> listaTemp = new ArrayList<>(Arrays.asList(emprestimos));
        listaTemp.add(emprestimo);
        emprestimos = listaTemp.toArray(new Emprestimo[0]);

        if (emprestimo.getLivro() != null) {
            emprestimo.getLivro().alterarStatus(StatusLivro.EMPRESTADO);
        }
        System.out.println("Empréstimo registrado por " + this.nome);
    }

    public void registrarReserva(Reserva reserva) {
        List<Reserva> listaTemp = new ArrayList<>(Arrays.asList(reservas));
        listaTemp.add(reserva);
        reservas = listaTemp.toArray(new Reserva[0]);
        System.out.println("Reserva registrada por " + this.nome);
    }

    public void registrarDevolucao(Emprestimo emprestimo) {
        emprestimo.registrarDevolucao();
        System.out.println("Devolução processada por " + this.nome);
    }

    public String toCSV() {
        return idBibliotecaria + ";" + nome;
    }

    @Override
    public String toString() {
        return "Bibliotecaria{" +
                "id=" + idBibliotecaria +
                ", nome='" + nome + '\'' +
                ", totalLivrosGerenciados=" + acervo.length +
                '}';
    }
}