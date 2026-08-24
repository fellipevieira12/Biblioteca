public class Livro {

    private int idLivro;
    private String titulo;
    private String autor;
    private String status;

    public Livro(int idLivro, String titulo, String autor) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.autor = autor;
        this.status = "Disponível";
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals("Disponível") || status.equals("Emprestado")) {
            this.status = status;
        } else {
            System.out.println("Status inválido. Use 'Disponível' ou 'Emprestado'.");
        }
    }

    @Override
    public String toString() {
        return "Livro{" +
                "idLivro=" + idLivro +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
