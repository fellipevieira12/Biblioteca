import java.util.Date;
import modelo.*; // Importante para reconhecer Livro, Usuario, Biblioteca, etc.
import util.ManipuladorArquivos;

public class Main {

        public static void main(String[] args) {

                Biblioteca biblioteca = new Biblioteca();
                Bibliotecaria maria = new Bibliotecaria(1, "Maria");
                Bibliotecaria joao = new Bibliotecaria(2, "João");

                Livro livro = new Livro(
                                ManipuladorArquivos.proximoId("Livro.csv"),
                                "Clean Code",
                                "Robert C. Martin");
                ManipuladorArquivos.salvarLivro(livro);

                Usuario usuario = new Usuario(
                                ManipuladorArquivos.proximoId("Usuario.csv"),
                                "Gabriel",
                                "gabriel@email.com");
                ManipuladorArquivos.salvarUsuario(usuario);

                System.out.println("=== CADASTROS (duas bibliotecárias, um único acervo) ===");
                maria.cadastrarLivro(biblioteca, livro);
                joao.cadastrarUsuario(biblioteca, usuario);

                Emprestimo emprestimo = new Emprestimo(
                                ManipuladorArquivos.proximoId("Emprestimo.csv"),
                                livro,
                                usuario,
                                new Date());
                ManipuladorArquivos.salvarEmprestimo(emprestimo);

                System.out.println("\n=== REGISTRANDO EMPRÉSTIMO ===");
                maria.registrarEmprestimo(biblioteca, emprestimo);

                System.out.println("\n=== IMPRIMINDO OBJETOS (toString) ===");
                System.out.println(livro);
                System.out.println(usuario);
                System.out.println(emprestimo);
                System.out.println(maria);
                System.out.println(joao);
                System.out.println(biblioteca);

                System.out.println("\n=== REGISTRANDO DEVOLUÇÃO ===");
                maria.registrarDevolucao(emprestimo);

                System.out.println("\n=== ESTADO APÓS DEVOLUÇÃO ===");
                System.out.println(livro);
                System.out.println(emprestimo);

                System.out.println("\n=== DEMONSTRANDO RESERVA ===");

                Reserva reserva = new Reserva(
                                ManipuladorArquivos.proximoId("Reserva.csv"),
                                livro,
                                usuario,
                                new Date());
                ManipuladorArquivos.salvarReserva(reserva);

                joao.registrarReserva(biblioteca, reserva);
                System.out.println(reserva);
                System.out.println(livro);

                reserva.cancelarReserva();
                System.out.println(reserva);

                System.out.println("\n=== ESTADO FINAL DA BIBLIOTECA ===");
                System.out.println(biblioteca);
        }
}