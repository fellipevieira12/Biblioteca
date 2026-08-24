public class Bibliotecaria {

    private int idBibliotecaria;
    private String nome;

    private Livro[] acervo = new Livro[50];
    private int totalLivros = 0;

    private Usuario[] usuarios = new Usuario[50];
    private int totalUsuarios = 0;

    private Emprestimo[] emprestimos = new Emprestimo[50];
    private int totalEmprestimos = 0;

    public Bibliotecaria(int idBibliotecaria, String nome) {
        this.idBibliotecaria = idBibliotecaria;
        this.nome = nome;
    }

    public int getIdBibliotecaria() {
        return idBibliotecaria;
    }

    public void setIdBibliotecaria(int idBibliotecaria) {
        this.idBibliotecaria = idBibliotecaria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void cadastrarLivro(Livro livro) {
        acervo[totalLivros] = livro;
        totalLivros++;
        System.out.println("Livro cadastrado: " + livro.getTitulo());
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios[totalUsuarios] = usuario;
        totalUsuarios++;
        System.out.println("Usuário cadastrado: " + usuario.getNome());
    }

    public void registrarEmprestimo(Emprestimo emprestimo) {
        emprestimos[totalEmprestimos] = emprestimo;
        totalEmprestimos++;
        emprestimo.getLivro().setStatus("Emprestado");
        emprestimo.getUsuario().adicionarEmprestimo(emprestimo);
        System.out.println("Empréstimo registrado.");
    }

    public void registrarDevolucao(Emprestimo emprestimo) {
        emprestimo.registrarDevolucao();
    }

    @Override
    public String toString() {
        return "Bibliotecaria{" +
                "idBibliotecaria=" + idBibliotecaria +
                ", nome='" + nome + '\'' +
                ", totalLivros=" + totalLivros +
                ", totalUsuarios=" + totalUsuarios +
                ", totalEmprestimos=" + totalEmprestimos +
                '}';
    }
}
