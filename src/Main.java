import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Bibliotecaria bibliotecaria = new Bibliotecaria(1, "Maria");
        Livro livro = new Livro(1, "Clean Code", "Robert C. Martin");
        Usuario usuario = new Usuario(1, "Gabriel", "gabriel@email.com");

        System.out.println("=== CADASTROS ===");
        bibliotecaria.cadastrarLivro(livro);
        bibliotecaria.cadastrarUsuario(usuario);

        Emprestimo emprestimo = new Emprestimo(1, livro, usuario, new Date());

        System.out.println("\n=== REGISTRANDO EMPRÉSTIMO ===");
        bibliotecaria.registrarEmprestimo(emprestimo);

        System.out.println("\n=== IMPRIMINDO OBJETOS (toString) ===");
        System.out.println(livro);
        System.out.println(usuario);
        System.out.println(emprestimo);
        System.out.println(bibliotecaria);

        System.out.println("\n=== REGISTRANDO DEVOLUÇÃO ===");
        bibliotecaria.registrarDevolucao(emprestimo);

        System.out.println("\n=== ESTADO APÓS DEVOLUÇÃO ===");
        System.out.println(livro);
        System.out.println(emprestimo);
    }
}
