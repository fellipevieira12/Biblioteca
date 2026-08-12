import java.util.Date;

public class Emprestimo {

    private int idEmprestimo;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Boolean ativo;

    public Emprestimo(int idEmprestimo, Date dataEmprestimo,
            Date dataDevolucao, Boolean ativo) {

        this.idEmprestimo = idEmprestimo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.ativo = ativo;
    }

    public void registrarDevolucao() {
        this.ativo = false;
        this.dataDevolucao = new Date();

        System.out.println("Devolução registrada.");
    }

    public void exibirDados() {
        System.out.println("ID Empréstimo: " + idEmprestimo);
        System.out.println("Data Empréstimo: " + dataEmprestimo);
        System.out.println("Data Devolução: " + dataDevolucao);
        System.out.println("Ativo: " + ativo);
    }
}