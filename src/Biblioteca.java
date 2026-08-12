public class Biblioteca {

    private int idBiblioteca;
    private String nome;

    public Biblioteca(int idBiblioteca, String nome) {
        this.idBiblioteca = idBiblioteca;
        this.nome = nome;
    }

    public void cadastrarLivro() {
        System.out.println("Livro cadastrado.");
    }

    public void cadastrarUsuario() {
        System.out.println("Usuário cadastrado.");
    }

    public void registrarEmprestimo() {
        System.out.println("Empréstimo registrado.");
    }

    public void registrarReserva() {
        System.out.println("Reserva registrada.");
    }

    public void registrarDevolucao() {
        System.out.println("Devolução registrada.");
    }

    @Override
    public String toString() {
        return "Biblioteca [idBiblioteca=" + idBiblioteca +
                ", nome=" + nome + "]";
    }
}