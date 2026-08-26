public class Biblioteca {

    private Livro[] acervo = new Livro[0];
    private Usuario[] usuarios = new Usuario[0];
    private Emprestimo[] emprestimos = new Emprestimo[0];
    private Reserva[] reservas = new Reserva[0];

    public void adicionarLivro(Livro livro) {
        acervo = crescerArray(acervo, livro);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios = crescerArray(usuarios, usuario);
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos = crescerArray(emprestimos, emprestimo);
    }

    public void adicionarReserva(Reserva reserva) {
        reservas = crescerArray(reservas, reserva);
    }

    // Cresce o array em 1 posição apenas quando um novo item é adicionado.
    // Nada é alocado antecipadamente: o array só ocupa exatamente o espaço
    // que está sendo usado no momento.
    private Livro[] crescerArray(Livro[] original, Livro novoItem) {
        Livro[] novoArray = new Livro[original.length + 1];
        for (int i = 0; i < original.length; i++) {
            novoArray[i] = original[i];
        }
        novoArray[original.length] = novoItem;
        return novoArray;
    }

    private Usuario[] crescerArray(Usuario[] original, Usuario novoItem) {
        Usuario[] novoArray = new Usuario[original.length + 1];
        for (int i = 0; i < original.length; i++) {
            novoArray[i] = original[i];
        }
        novoArray[original.length] = novoItem;
        return novoArray;
    }

    private Emprestimo[] crescerArray(Emprestimo[] original, Emprestimo novoItem) {
        Emprestimo[] novoArray = new Emprestimo[original.length + 1];
        for (int i = 0; i < original.length; i++) {
            novoArray[i] = original[i];
        }
        novoArray[original.length] = novoItem;
        return novoArray;
    }

    private Reserva[] crescerArray(Reserva[] original, Reserva novoItem) {
        Reserva[] novoArray = new Reserva[original.length + 1];
        for (int i = 0; i < original.length; i++) {
            novoArray[i] = original[i];
        }
        novoArray[original.length] = novoItem;
        return novoArray;
    }

    public Livro[] getAcervo() {
        return acervo;
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public Emprestimo[] getEmprestimos() {
        return emprestimos;
    }

    public Reserva[] getReservas() {
        return reservas;
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "totalLivros=" + acervo.length +
                ", totalUsuarios=" + usuarios.length +
                ", totalEmprestimos=" + emprestimos.length +
                ", totalReservas=" + reservas.length +
                '}';
    }
}
