import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.*;
import util.ManipuladorArquivos;

public class Main {

        public static void main(String[] args) {

                List<Livro> acervoMain = new ArrayList<>();
                List<Usuario> usuariosMain = new ArrayList<>();
                List<Emprestimo> emprestimosMain = new ArrayList<>();
                List<Reserva> reservasMain = new ArrayList<>();

                Bibliotecaria maria = new Bibliotecaria(1, "Maria");
                Bibliotecaria joao = new Bibliotecaria(2, "João");

                Livro livro1 = new Livro(ManipuladorArquivos.proximoId("Livro.csv"), "Clean Code", "Robert C. Martin");
                Livro livro2 = new Livro(ManipuladorArquivos.proximoId("Livro.csv") + 1, "Padrões de Projetos", "GoF");

                Usuario usuario1 = new Usuario(1, "Gabriel", "gabriel@email.com");
                Usuario usuario2 = new Usuario(2, "Fellipe", "fellipe@email.com");

                Emprestimo emp1 = new Emprestimo(1, livro1, usuario1, new Date());
                Emprestimo emp2 = new Emprestimo(2, livro2, usuario2, new Date());

                Reserva res1 = new Reserva(1, livro2, usuario1, new Date());
                Reserva res2 = new Reserva(2, livro1, usuario2, new Date());

                maria.cadastrarLivro(livro1);
                joao.cadastrarLivro(livro2);
                acervoMain.add(livro1);
                acervoMain.add(livro2);
                ManipuladorArquivos.salvarLivro(livro1);
                ManipuladorArquivos.salvarLivro(livro2);

                maria.cadastrarUsuario(usuario1);
                joao.cadastrarUsuario(usuario2);
                usuariosMain.add(usuario1);
                usuariosMain.add(usuario2);
                ManipuladorArquivos.salvarUsuario(usuario1);
                ManipuladorArquivos.salvarUsuario(usuario2);

                maria.registrarEmprestimo(emp1);
                joao.registrarEmprestimo(emp2);
                emprestimosMain.add(emp1);
                emprestimosMain.add(emp2);
                ManipuladorArquivos.salvarEmprestimo(emp1);
                ManipuladorArquivos.salvarEmprestimo(emp2);

                maria.registrarReserva(res1);
                joao.registrarReserva(res2);
                reservasMain.add(res1);
                reservasMain.add(res2);
                ManipuladorArquivos.salvarReserva(res1);
                ManipuladorArquivos.salvarReserva(res2);

                System.out.println("\n=== LENDO ARQUIVOS CSV ===");

                List<Livro> livrosLidos = ManipuladorArquivos.lerLivros();
                System.out.println("LIVROS SALVOS:");
                for (Livro l : livrosLidos) {
                        System.out.println(l.toString());
                }

                List<Usuario> usuariosLidos = ManipuladorArquivos.lerUsuarios();
                System.out.println("\nUSUÁRIOS SALVOS:");
                for (Usuario u : usuariosLidos) {
                        System.out.println(u.toString());
                }
        }
}