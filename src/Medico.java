public class Medico {

    public Integer idMedico;
    public String nome;
    public String especialidade;
    public String crm;

    public void atualizarStatusAgendamento(Integer idAgendamento, String status) {
        System.out.println("Atualizando agendamento " + idAgendamento
                + " para: " + status);
    }

    public void listarAgendamentos() {
        System.out.println("Listando agendamentos do médico...");
    }
}