import java.sql.Date;

public class Paciente {
    int idPaciente;
    String nome;
    Date dataNascimento;
    String telefone;
    String email;

    public void atualizarContato(String novoTelefone, String novoEmail) {
        this.telefone = novoTelefone;
        this.email = novoEmail;
    }
}
