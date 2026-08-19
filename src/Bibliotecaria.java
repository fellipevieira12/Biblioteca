public class Bibliotecaria {

    int idBibliotecaria;
    String nome;

    // Relacionamento: "A bibliotecária gerencia vários livros" / registra empréstimos.
    Livro[] acervo = new Livro[50];
    int totalLivros = 0;

    Usuario[] usuarios = new Usuario[50];
    int totalUsuarios = 0;

    Emprestimo[] emprestimos = new Emprestimo[50];
    int totalEmprestimos = 0;

    void cadastrarLivro(Livro livro) {
        acervo[totalLivros] = livro;
        totalLivros++;
        System.out.println("Livro cadastrado: " + livro.titulo);
    }

    void cadastrarUsuario(Usuario usuario) {
        usuarios[totalUsuarios] = usuario;
        totalUsuarios++;
        System.out.println("Usuário cadastrado: " + usuario.nome);
    }

    void registrarEmprestimo(Emprestimo emprestimo) {
        emprestimos[totalEmprestimos] = emprestimo;
        totalEmprestimos++;
        emprestimo.livro.alterarStatus("Emprestado");
        emprestimo.usuario.adicionarEmprestimo(emprestimo);
        System.out.println("Empréstimo registrado.");
    }

    void registrarDevolucao(Emprestimo emprestimo) {
        emprestimo.registrarDevolucao();
    }

    void registrarReserva(Reserva reserva) {
        System.out.println("Reserva registrada.");
    }

    void exibirDados() {
        System.out.println("ID Bibliotecária: " + idBibliotecaria);
        System.out.println("Nome: " + nome);
    }
}
