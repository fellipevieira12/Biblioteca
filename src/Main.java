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

                Livro livro1 = new Livro(
                                ManipuladorArquivos.proximoId("Livro.csv"),
                                "Clean Code",
                                "Robert C. Martin");

                acervoMain.add(livro1);

                maria.cadastrarLivro(livro1);

                ManipuladorArquivos.salvarLivro(livro1);

                Usuario usuario1 = new Usuario(1, "Gabriel", "gabriel@email.com");
                usuariosMain.add(usuario1);
                maria.cadastrarUsuario(usuario1);

                Emprestimo emp1 = new Emprestimo(1, livro1, usuario1, new Date());
                emprestimosMain.add(emp1);

                maria.registrarEmprestimo(emp1);
                ManipuladorArquivos.salvarEmprestimo(emp1);
        }
}