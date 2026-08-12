public class Livro {

    private int idLivro;
    private String titulo;
    private String autor;
    private String status;

    public Livro(int idLivro, String titulo, String autor, String status) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.autor = autor;
        this.status = status;
    }

    public void alterarStatus(String status) {
        this.status = status;
    }

    public void exibirInformacoes() {
        System.out.println("ID: " + idLivro);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Status: " + status);
    }
}