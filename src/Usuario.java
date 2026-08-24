public class Usuario {

    private int idUsuario;
    private String nome;
    private String email;

    private Emprestimo[] emprestimos = new Emprestimo[50];
    private int totalEmprestimos = 0;

    public Usuario(int idUsuario, String nome, String email) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos[totalEmprestimos] = emprestimo;
        totalEmprestimos++;
    }

    public void listarEmprestimos() {
        System.out.println("Empréstimos de " + nome + ":");
        for (int i = 0; i < totalEmprestimos; i++) {
            System.out.println(emprestimos[i]);
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", totalEmprestimos=" + totalEmprestimos +
                '}';
    }
}
