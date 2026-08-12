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
                StatusLivro.DISPONIVEL);

        Emprestimo emprestimo = new Emprestimo(
                1,
                new Date(),
                null,
                true);

        Reserva reserva = new Reserva(
                1,
                new Date(),
                StatusReserva.ATIVA);

        System.out.println(usuario);
        System.out.println(biblioteca);
        System.out.println(livro);
        System.out.println(emprestimo);
        System.out.println(reserva);

        System.out.println(StatusLivro.DISPONIVEL);
        System.out.println(StatusReserva.ATIVA);
    }
}