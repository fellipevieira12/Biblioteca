import java.util.Date;

public class Emprestimo {

    int idEmprestimo;

    Livro livro;
    Usuario usuario;

    Date dataEmprestimo;
    Date dataDevolucao;
    String status;

    void registrarDevolucao() {
        this.status = "Devolvido";
        this.dataDevolucao = new Date();

        if (livro != null) {
            livro.alterarStatus("Disponível");
        }

        System.out.println("Devolução registrada.");
    }

    void exibirDados() {
        System.out.println("ID Empréstimo: " + idEmprestimo);
        System.out.println("Livro: " + (livro != null ? livro.titulo : "-"));
        System.out.println("Usuário: " + (usuario != null ? usuario.nome : "-"));
        System.out.println("Data Empréstimo: " + dataEmprestimo);
        System.out.println("Data Devolução: " + dataDevolucao);
        System.out.println("Status: " + status);
    }
}
