public class Usuario {

    private int idUsuario;
    private String nome;
    private String email;

    public Usuario(int idUsuario, String nome, String email) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
    }

    public void solicitarEmprestimo() {
        System.out.println("Empréstimo solicitado.");
    }

    public void consultarEmprestimos() {
        System.out.println("Consultando empréstimos...");
    }

    public void atualizarContato(String email) {
        this.email = email;
        System.out.println("E-mail atualizado para: " + email);
    }

    public void exibirDados() {
        System.out.println("ID Usuário: " + idUsuario);
        System.out.println("Nome: " + nome);
        System.out.println("E-mail: " + email);
    }
}
