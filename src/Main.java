import java.util.Date;
import java.util.List;
import modelo.*;
import util.ManipuladorArquivos;

public class Main {

        public static void main(String[] args) {

                Biblioteca biblioteca = new Biblioteca();
                Bibliotecaria maria = new Bibliotecaria(1, "Maria");
                Bibliotecaria joao = new Bibliotecaria(2, "João");

                // --- CADASTRANDO OBJETOS 1 ---
                Livro livro1 = new Livro(
                                ManipuladorArquivos.proximoId("Livro.csv"),
                                "Clean Code",
                                "Robert C. Martin");
                ManipuladorArquivos.salvarLivro(livro1);

                Usuario usuario1 = new Usuario(
                                ManipuladorArquivos.proximoId("Usuario.csv"),
                                "Gabriel",
                                "gabriel@email.com");
                ManipuladorArquivos.salvarUsuario(usuario1);

                // --- CADASTRANDO OBJETOS 2 ---
                Livro livro2 = new Livro(
                                ManipuladorArquivos.proximoId("Livro.csv"),
                                "Crime e Castigo",
                                "Fiódor Dostoiévski");
                ManipuladorArquivos.salvarLivro(livro2);

                Usuario usuario2 = new Usuario(
                                ManipuladorArquivos.proximoId("Usuario.csv"),
                                "Fellipe",
                                "fellipe@email.com");
                ManipuladorArquivos.salvarUsuario(usuario2);

                System.out.println("=== CADASTROS (duas bibliotecárias, um único acervo) ===");
                maria.cadastrarLivro(biblioteca, livro1);
                joao.cadastrarLivro(biblioteca, livro2);
                maria.cadastrarUsuario(biblioteca, usuario1);
                joao.cadastrarUsuario(biblioteca, usuario2);

                // --- EMPRÉSTIMOS ---
                Emprestimo emprestimo1 = new Emprestimo(
                                ManipuladorArquivos.proximoId("Emprestimo.csv"),
                                livro1,
                                usuario1,
                                new Date());
                ManipuladorArquivos.salvarEmprestimo(emprestimo1);

                Emprestimo emprestimo2 = new Emprestimo(
                                ManipuladorArquivos.proximoId("Emprestimo.csv"),
                                livro2,
                                usuario2,
                                new Date());
                ManipuladorArquivos.salvarEmprestimo(emprestimo2);

                System.out.println("\n=== REGISTRANDO EMPRÉSTIMOS ===");
                maria.registrarEmprestimo(biblioteca, emprestimo1);
                joao.registrarEmprestimo(biblioteca, emprestimo2);

                System.out.println("\n=== REGISTRANDO DEVOLUÇÃO ===");
                maria.registrarDevolucao(emprestimo1);

                // --- RESERVAS ---
                System.out.println("\n=== DEMONSTRANDO RESERVAS ===");
                Reserva reserva1 = new Reserva(
                                ManipuladorArquivos.proximoId("Reserva.csv"),
                                livro1,
                                usuario2,
                                new Date());
                ManipuladorArquivos.salvarReserva(reserva1);
                joao.registrarReserva(biblioteca, reserva1);

                Reserva reserva2 = new Reserva(
                                ManipuladorArquivos.proximoId("Reserva.csv"),
                                livro2,
                                usuario1,
                                new Date());
                ManipuladorArquivos.salvarReserva(reserva2);
                maria.registrarReserva(biblioteca, reserva2);

                reserva1.cancelarReserva();

                System.out.println("\n=== ESTADO FINAL DA BIBLIOTECA ===");
                System.out.println(biblioteca);

                System.out.println("\n=== LENDO ARQUIVO DE LIVROS ===");
                List<Livro> livrosLidos = ManipuladorArquivos.lerLivros();
                for (Livro l : livrosLidos) {
                        System.out.println(l.toString());
                }

                System.out.println("\n=== LENDO ARQUIVO DE USUÁRIOS ===");
                List<Usuario> usuariosLidos = ManipuladorArquivos.lerUsuarios();
                for (Usuario u : usuariosLidos) {
                        System.out.println(u.toString());
                }
        }
}