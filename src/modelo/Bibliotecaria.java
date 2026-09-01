public class Bibliotecaria {

    private int idBibliotecaria;
    private String nome;

    public Bibliotecaria(int idBibliotecaria, String nome) {
        this.idBibliotecaria = idBibliotecaria;
        this.nome = nome;
    }

    public int getIdBibliotecaria() {
        return idBibliotecaria;
    }

    public void setIdBibliotecaria(int idBibliotecaria) {
        this.idBibliotecaria = idBibliotecaria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void cadastrarLivro(Biblioteca biblioteca, Livro livro) {
        biblioteca.adicionarLivro(livro);
        System.out.println("Livro cadastrado: " + livro.getTitulo());
    }

    public void cadastrarUsuario(Biblioteca biblioteca, Usuario usuario) {
        biblioteca.adicionarUsuario(usuario);
        System.out.println("Usuário cadastrado: " + usuario.getNome());
    }

    public void registrarEmprestimo(Biblioteca biblioteca, Emprestimo emprestimo) {
        biblioteca.adicionarEmprestimo(emprestimo);
        emprestimo.getLivro().alterarStatus(StatusLivro.EMPRESTADO);
        emprestimo.getUsuario().solicitarEmprestimo(emprestimo);
        System.out.println("Empréstimo registrado.");
    }

    public void registrarReserva(Biblioteca biblioteca, Reserva reserva) {
        biblioteca.adicionarReserva(reserva);
        reserva.getLivro().alterarStatus(StatusLivro.RESERVADO);
        System.out.println("Reserva registrada.");
    }

    public void registrarDevolucao(Emprestimo emprestimo) {
        emprestimo.registrarDevolucao();
    }

    @Override
    public String toString() {
        return "Bibliotecaria{" +
                "idBibliotecaria=" + idBibliotecaria +
                ", nome='" + nome + '\'' +
                '}';
    }
}
