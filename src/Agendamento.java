import java.sql.Time;
import java.util.Date;

public class Agendamento {

    public Integer idAgendamento;
    public Date data;
    public Time hora;
    public String status;

    public void atualizaStatus(String status) {
        this.status = status;
        System.out.println("Status atualizado para: " + status);
    }
}