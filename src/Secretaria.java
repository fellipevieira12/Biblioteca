import java.sql.Time;
import java.util.Date;

public class Secretaria {

    public String idSecretaria;
    public String nome;
    public String turno;

    public void cadastrarPaciente(Paciente paciente) {
        System.out.println("Paciente cadastrado: " + paciente.nome);
    }

    public void atualizarContatoPaciente(
            Integer idPaciente,
            String telefone,
            String email) {

        System.out.println("Atualizando contato do paciente "
                + idPaciente);

        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
    }

    public void cadastrarAgendamento(
            Paciente paciente,
            Medico medico,
            Date data,
            Time hora) {

        System.out.println("Agendamento cadastrado.");
        System.out.println("Paciente: " + paciente.nome);
        System.out.println("Médico: " + medico.nome);
        System.out.println("Data: " + data);
        System.out.println("Hora: " + hora);
    }

    public void listarAgendamentos() {
        System.out.println("Listando agendamentos...");
    }
}