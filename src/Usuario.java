public class Usuario {

    int idUsuario;
    String nome;
    String email;

    // Relacionamento: "Um usuário pode ter vários empréstimos."
    Emprestimo[] emprestimos = new Emprestimo[50];
    int totalEmprestimos = 0;

    void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos[totalEmprestimos] = emprestimo;
        totalEmprestimos++;
    }

    void listarEmprestimos() {
        System.out.println("Empréstimos de " + nome + ":");
        for (int i = 0; i < totalEmprestimos; i++) {
            emprestimos[i].exibirDados();
        }
    }

    void atualizarContato(String novoEmail) {
        this.email = novoEmail;
        System.out.println("E-mail atualizado para: " + novoEmail);
    }

    void exibirDados() {
        System.out.println("ID Usuário: " + idUsuario);
        System.out.println("Nome: " + nome);
        System.out.println("E-mail: " + email);
    }
}
