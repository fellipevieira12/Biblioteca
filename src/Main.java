import java.util.Date;
import java.util.List;
import modelo.*;
import util.ManipuladorArquivos;

public class Main {

        public static void main(String[] args) {

                Bibliotecaria maria = new Bibliotecaria(1, "Maria");
                Bibliotecaria joao = new Bibliotecaria(2, "João");

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

                System.out.println("=== CADASTROS ===");
                maria.cadastrarLivro(livro1);
                joao.cadastrarLivro(livro2);
                maria.cadastrarUsuario(usuario1);
                joao.cadastrarUsuario(usuario2);

                Emprestimo emprestimo1 = new Emprestimo(
                                ManipuladorArquivos.proximoId("Emprestimo.csv"),
                                livro1,
                                usuario1,
                                new Date());
                ManipuladorArquivos.salvarEmprestimo(emprestimo1);

                System.out.println("\n=== REGISTRANDO EMPRÉSTIMOS ===");
                maria.registrarEmprestimo(emprestimo1);

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
                joao.registrarReserva(reserva1);

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