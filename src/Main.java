import java.util.Date;

public class Main {

        public static void main(String[] args) {

                Usuario usuario = new Usuario(
                                1,
                                "Gabriel",
                                "gabriel@email.com");

                Biblioteca biblioteca = new Biblioteca(
                                1,
                                "Biblioteca Central");

                Livro livro = new Livro(
                                1,
                                "Clean Code",
                                "Robert C. Martin",
                                "Disponível");

                Emprestimo emprestimo = new Emprestimo(
                                1,
                                new Date(),
                                null,
                                true);

                Reserva reserva = new Reserva(
                                1,
                                new Date(),
                                "Ativa");

                System.out.println("=== USUÁRIO ===");
                usuario.exibirDados();

                System.out.println("\n=== BIBLIOTECA ===");
                biblioteca.exibirDados();

                System.out.println("\n=== LIVRO ===");
                livro.exibirInformacoes();

                System.out.println("\n=== EMPRÉSTIMO ===");
                emprestimo.exibirDados();

                System.out.println("\n=== RESERVA ===");
                reserva.exibirDados();

                System.out.println("\n=== TESTANDO MÉTODOS ===");

                usuario.solicitarEmprestimo();
                usuario.consultarEmprestimos();

                biblioteca.cadastrarLivro();
                biblioteca.cadastrarUsuario();
                biblioteca.registrarEmprestimo();

                livro.alterarStatus("Emprestado");

                reserva.cancelarReserva();

                emprestimo.registrarDevolucao();
        }
}