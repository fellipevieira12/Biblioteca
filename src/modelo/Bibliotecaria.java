package modelo;

public class Bibliotecaria {

    private int idBibliotecaria;
    private String nome;

    public Bibliotecaria(int idBibliotecaria, String nome) {
        this.idBibliotecaria = idBibliotecaria;
        this.nome = nome;
    }

    public void cadastrarLivro(Livro livro) {
        System.out.println("Livro '" + livro.getTitulo() + "' cadastrado por " + this.nome);
    }

    public void cadastrarUsuario(Usuario usuario) {
        System.out.println("Usuário '" + usuario.getNome() + "' cadastrado por " + this.nome);
    }

    public void registrarEmprestimo(Emprestimo emprestimo) {
        if (emprestimo.getLivro() != null) {
            emprestimo.getLivro().alterarStatus(StatusLivro.EMPRESTADO);
        }
        System.out.println("Empréstimo registrado por " + this.nome);
    }

    public void registrarReserva(Reserva reserva) {
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
                '}';
    }
}