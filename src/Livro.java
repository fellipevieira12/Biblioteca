public class Livro {

    private int idLivro;
    private String titulo;
    private String autor;
    private StatusLivro status;

    public Livro(int idLivro, String titulo, String autor, StatusLivro status) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.autor = autor;
        this.status = status;
    }

    public void alterarStatus(StatusLivro status) {
        this.status = status;
    }

    public void exibirInformacoes() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Livro [idLivro=" + idLivro +
                ", titulo=" + titulo +
                ", autor=" + autor +
                ", status=" + status + "]";
    }
}