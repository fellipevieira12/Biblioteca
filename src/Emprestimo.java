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

    @Override
    public String toString() {
        return "Emprestimo [idEmprestimo=" + idEmprestimo +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                ", ativo=" + ativo + "]";
    }
}