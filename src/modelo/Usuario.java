package modelo;

public class Usuario {

    private int idUsuario;
    private String nome;
    private String email;

    private Emprestimo[] emprestimos = new Emprestimo[0];

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

    public void solicitarEmprestimo(Emprestimo emprestimo) {
        Emprestimo[] novoArray = new Emprestimo[emprestimos.length + 1];
        for (int i = 0; i < emprestimos.length; i++) {
            novoArray[i] = emprestimos[i];
        }
        novoArray[emprestimos.length] = emprestimo;
        emprestimos = novoArray;

        System.out.println("Empréstimo solicitado por " + nome);
    }

    public void consultarEmprestimos() {
        System.out.println("Empréstimos de " + nome + ":");
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(emprestimo);
        }
    }

    public void atualizarContato(String email) {
        this.email = email;
        System.out.println("E-mail atualizado para: " + email);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", totalEmprestimos=" + emprestimos.length +
                '}';
    }

    public String toCSV() {
        return idUsuario + ";" +
                nome + ";" +
                email;
    }
}
