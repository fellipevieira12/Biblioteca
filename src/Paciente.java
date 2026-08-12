import java.util.Date;

public class Paciente {

    public Integer idPaciente;
    public String nome;
    public Date dataNascimento;
    public String telefone;
    public String email;

    public void atualizarContato(String telefone, String email) {
        this.telefone = telefone;
        this.email = email;
    }

    public void listarAgendamentos() {
        System.out.println("Listando agendamentos do paciente...");
    }

    public void cancelarAgendamento(Integer idAgendamento) {
        System.out.println("Cancelando agendamento: " + idAgendamento);
    }
}