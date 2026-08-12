public class Clinica {

    public Integer idClinica;
    public String nomeClinica;
    public String endereco;
    public String telefone;

    public void atualizarClinica(Clinica clinica) {
        this.idClinica = clinica.idClinica;
        this.nomeClinica = clinica.nomeClinica;
        this.endereco = clinica.endereco;
        this.telefone = clinica.telefone;
    }

    public void cadastrarMedico(Medico medico) {
        System.out.println("Médico cadastrado: " + medico.nome);
    }

    public void cadastrarSecretaria(Secretaria secretaria) {
        System.out.println("Secretária cadastrada: " + secretaria.nome);
    }
}