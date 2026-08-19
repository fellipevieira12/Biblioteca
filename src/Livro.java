public class Livro {

    int idLivro;
    String titulo;
    String autor;
    String status;

    void alterarStatus(String status) {
        this.status = status;
    }

    void exibirInformacoes() {
        System.out.println("ID: " + idLivro);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Status: " + status);
    }
}
