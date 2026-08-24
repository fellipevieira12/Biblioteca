import java.util.Date;

public class Emprestimo {

    private int idEmprestimo;
    private Livro livro;
    private Usuario usuario;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private String status;

    public Emprestimo(int idEmprestimo, Livro livro, Usuario usuario, Date dataEmprestimo) {
        this.idEmprestimo = idEmprestimo;
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.status = "Ativo";
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals("Ativo") || status.equals("Devolvido")) {
            this.status = status;
        } else {
            System.out.println("Status inválido. Use 'Ativo' ou 'Devolvido'.");
        }
    }

    public void registrarDevolucao() {
        this.status = "Devolvido";
        this.dataDevolucao = new Date();

        if (livro != null) {
            livro.setStatus("Disponível");
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
                ", status='" + status + '\'' +
                '}';
    }
}
