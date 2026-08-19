import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Bibliotecaria bibliotecaria = new Bibliotecaria();
        bibliotecaria.idBibliotecaria = 1;
        bibliotecaria.nome = "Maria";

        // Criando um livro
        Livro livro = new Livro();
        livro.idLivro = 1;
        livro.titulo = "Clean Code";
        livro.autor = "Robert C. Martin";
        livro.status = "Disponível";

        // Criando um usuário
        Usuario usuario = new Usuario();
        usuario.idUsuario = 1;
        usuario.nome = "Gabriel";
        usuario.email = "gabriel@email.com";

        System.out.println("=== CADASTROS ===");
        bibliotecaria.cadastrarLivro(livro);
        bibliotecaria.cadastrarUsuario(usuario);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.idEmprestimo = 1;
        emprestimo.livro = livro;
        emprestimo.usuario = usuario;
        emprestimo.dataEmprestimo = new Date();
        emprestimo.status = "Ativo";

        System.out.println("\n=== REGISTRANDO EMPRÉSTIMO ===");
        bibliotecaria.registrarEmprestimo(emprestimo);

        System.out.println("\n=== ESTADO APÓS EMPRÉSTIMO ===");
        livro.exibirInformacoes();
        emprestimo.exibirDados();
        usuario.listarEmprestimos();

        System.out.println("\n=== REGISTRANDO DEVOLUÇÃO ===");
        bibliotecaria.registrarDevolucao(emprestimo);

        System.out.println("\n=== ESTADO APÓS DEVOLUÇÃO ===");
        livro.exibirInformacoes();
        emprestimo.exibirDados();

        Reserva reserva = new Reserva();
        reserva.idReserva = 1;
        reserva.livro = livro;
        reserva.usuario = usuario;
        reserva.dataReserva = new Date();
        reserva.status = "Ativa";

        System.out.println("\n=== REGISTRANDO RESERVA ===");
        bibliotecaria.registrarReserva(reserva);
        reserva.exibirDados();
    }
}
