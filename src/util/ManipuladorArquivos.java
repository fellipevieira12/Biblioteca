package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import modelo.*;

public class ManipuladorArquivos {

    private static final String DIRETORIO = "dados";

    static {
        File pasta = new File(DIRETORIO);
        if (!pasta.exists()) {
            pasta.mkdir();
        }
    }

    public static void salvarLivro(Livro livro) {
        salvarLinha("Livro.csv", livro.toCSV());
    }

    public static void salvarUsuario(Usuario usuario) {
        salvarLinha("Usuario.csv", usuario.toCSV());
    }

    public static void salvarEmprestimo(Emprestimo emprestimo) {
        salvarLinha("Emprestimo.csv", emprestimo.toCSV());
    }

    public static void salvarReserva(Reserva reserva) {
        salvarLinha("Reserva.csv", reserva.toCSV());
    }

    private static void salvarLinha(String arquivo, String linha) {
        try {
            FileWriter fw = new FileWriter(
                    new File(DIRETORIO, arquivo), true);

            fw.write(linha + "\n");
            fw.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public static List<Livro> lerLivros() {
        List<Livro> livros = new ArrayList<>();
        try {
            File arq = new File(DIRETORIO, "Livro.csv");
            if (!arq.exists())
                return livros;

            BufferedReader br = new BufferedReader(new FileReader(arq));
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";");

                int id = Integer.parseInt(campos[0]);
                String titulo = campos[1];
                String autor = campos[2];
                String status = campos[3];

                Livro livro = new Livro(id, titulo, autor);
                livro.setStatus(StatusLivro.valueOf(status));

                livros.add(livro);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler livros: " + e.getMessage());
        }
        return livros;
    }

    public static List<Usuario> lerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            File arq = new File(DIRETORIO, "Usuario.csv");
            if (!arq.exists())
                return usuarios;

            BufferedReader br = new BufferedReader(new FileReader(arq));
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(";");

                int id = Integer.parseInt(campos[0]);
                String nome = campos[1];
                String email = campos[2];

                Usuario usuario = new Usuario(id, nome, email);
                usuarios.add(usuario);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler usuários: " + e.getMessage());
        }
        return usuarios;
    }

    public static int proximoId(String arquivo) {

        int maiorId = 0;

        try {

            File arq = new File(DIRETORIO, arquivo);

            if (!arq.exists()) {
                return 1;
            }

            BufferedReader br = new BufferedReader(new FileReader(arq));

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] campos = linha.split(";");

                int id = Integer.parseInt(campos[0]);

                if (id > maiorId) {
                    maiorId = id;
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Erro ao gerar ID.");
        }

        return maiorId + 1;
    }
}