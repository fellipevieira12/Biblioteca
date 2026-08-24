import java.util.Date;

public class Emprestimo {

    private int idEmprestimo;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Boolean ativo;

    private Livro livro;
    private Usuario usuario;

    public Emprestimo(int idEmprestimo, Livro livro, Usuario usuario, Date dataEmprestimo) {
        this.idEmprestimo = idEmprestimo;
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.ativo = true;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void registrarDevolucao() {
        this.ativo = false;
        this.dataDevolucao = new Date();

        if (livro != null) {
            livro.alterarStatus(StatusLivro.DISPONIVEL);
        }

        System.out.println("Devolução registrada.");
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "idEmprestimo=" + idEmprestimo +
                ", livro=" + (livro != null ? livro.getTitulo() : "-") +
                ", usuario=" + (usuario != null ? usuario.getNome() : "-") +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                ", ativo=" + ativo +
                '}';
    }
}
