import java.sql.Time;
import java.util.Date;

public class App {

    public static void main(String[] args) {

        Clinica clinica = new Clinica();
        Secretaria secretaria = new Secretaria();
        Agendamento agendamento = new Agendamento();
        Medico medico = new Medico();
        Paciente paciente = new Paciente();

        clinica.idClinica = 1;
        clinica.nomeClinica = "Clinica Vida";
        clinica.endereco = "Rua Principal, 100";
        clinica.telefone = "35999999999";

        secretaria.idSecretaria = "SEC001";
        secretaria.nome = "Maria";
        secretaria.turno = "Manhã";

        medico.idMedico = 1;
        medico.nome = "Dr. João";
        medico.especialidade = "Cardiologia";
        medico.crm = "12345";

        paciente.idPaciente = 1;
        paciente.nome = "Gabriel";
        paciente.dataNascimento = new Date();
        paciente.telefone = "35888888888";
        paciente.email = "gabriel@email.com";

        agendamento.idAgendamento = 1;
        agendamento.data = new Date();
        agendamento.hora = new Time(System.currentTimeMillis());
        agendamento.status = "Agendado";

        System.out.println("=== CLÍNICA ===");
        System.out.println(clinica.nomeClinica);
        System.out.println(clinica.endereco);
        System.out.println(clinica.telefone);

        System.out.println("\n=== SECRETARIA ===");
        System.out.println(secretaria.nome);
        System.out.println(secretaria.turno);

        System.out.println("\n=== MÉDICO ===");
        System.out.println(medico.nome);
        System.out.println(medico.especialidade);
        System.out.println(medico.crm);

        System.out.println("\n=== PACIENTE ===");
        System.out.println(paciente.nome);
        System.out.println(paciente.telefone);
        System.out.println(paciente.email);

        System.out.println("\n=== AGENDAMENTO ===");
        System.out.println(agendamento.idAgendamento);
        System.out.println(agendamento.data);
        System.out.println(agendamento.hora);
        System.out.println(agendamento.status);
    }
}