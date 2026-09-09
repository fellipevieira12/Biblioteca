package util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public static void salvarBibliotecaria(Bibliotecaria bibliotecaria) {
        salvarLinha("Bibliotecaria.csv", bibliotecaria.toCSV());
    }

    public static void removerLivro(int idLivro) {
        List<Livro> livros = lerLivros();
        livros.removeIf(l -> l.getIdLivro() == idLivro);

        List<String> linhas = new ArrayList<>();
        for (Livro l : livros) {
            linhas.add(l.toCSV());
        }
        reescreverArquivo("Livro.csv", linhas);
    }

    public static void removerUsuario(int idUsuario) {
        List<Usuario> usuarios = lerUsuarios();
        usuarios.removeIf(u -> u.getIdUsuario() == idUsuario);

        List<String> linhas = new ArrayList<>();
        for (Usuario u : usuarios) {
            linhas.add(u.toCSV());
        }
        reescreverArquivo("Usuario.csv", linhas);
    }

    private static void reescreverArquivo(String arquivo, List<String> linhas) {
        try {
            FileWriter fw = new FileWriter(new File(DIRETORIO, arquivo), false);
            for (String linha : linhas) {
                fw.write(linha + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Erro ao remover: " + e.getMessage());
        }
    }

    public static List<Bibliotecaria> lerBibliotecarias() {
        List<Bibliotecaria> bibliotecarias = new ArrayList<>();
        try {
            File arq = new File(DIRETORIO, "Bibliotecaria.csv");
            if (!arq.exists())
                return bibliotecarias;

            BufferedReader br = new BufferedReader(new FileReader(arq));
            String linha;

            while ((linha = br.readLine()) != null) {
                try {
                    String[] campos = linha.split(";");

                    int id = Integer.parseInt(campos[0]);
                    String nome = campos[1];

                    bibliotecarias.add(new Bibliotecaria(id, nome));
                } catch (Exception linhaInvalida) {
                    System.out.println("Linha inválida em Bibliotecaria.csv, ignorada: " + linha);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler bibliotecárias: " + e.getMessage());
        }
        return bibliotecarias;
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
                try {
                    String[] campos = linha.split(";");

                    int id = Integer.parseInt(campos[0]);
                    String titulo = campos[1];
                    String autor = campos[2];
                    String status = campos[3];

                    Livro livro = new Livro(id, titulo, autor);
                    livro.setStatus(StatusLivro.valueOf(status));

                    livros.add(livro);
                } catch (Exception linhaInvalida) {
                    System.out.println("Linha inválida em Livro.csv, ignorada: " + linha);
                }
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
                try {
                    String[] campos = linha.split(";");

                    int id = Integer.parseInt(campos[0]);
                    String nome = campos[1];
                    String email = campos[2];

                    Usuario usuario = new Usuario(id, nome, email);
                    usuarios.add(usuario);
                } catch (Exception linhaInvalida) {
                    System.out.println("Linha inválida em Usuario.csv, ignorada: " + linha);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler usuários: " + e.getMessage());
        }
        return usuarios;
    }

    public static List<Emprestimo> lerEmprestimos() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        try {
            File arq = new File(DIRETORIO, "Emprestimo.csv");
            if (!arq.exists())
                return emprestimos;

            List<Livro> livros = lerLivros();
            List<Usuario> usuarios = lerUsuarios();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            BufferedReader br = new BufferedReader(new FileReader(arq));
            String linha;

            while ((linha = br.readLine()) != null) {
                try {
                    String[] campos = linha.split(";", -1);

                    int id = Integer.parseInt(campos[0]);
                    int idLivro = Integer.parseInt(campos[1]);
                    int idUsuario = Integer.parseInt(campos[2]);
                    Date dataEmprestimo = sdf.parse(campos[3]);

                    Livro livro = buscarLivroPorId(livros, idLivro);
                    Usuario usuario = buscarUsuarioPorId(usuarios, idUsuario);

                    Emprestimo emprestimo = new Emprestimo(id, livro, usuario, dataEmprestimo);

                    if (campos.length > 4 && !campos[4].isEmpty()) {
                        emprestimo.setDataDevolucao(sdf.parse(campos[4]));
                    }
                    if (campos.length > 5) {
                        emprestimo.setAtivo(Boolean.parseBoolean(campos[5]));
                    }

                    emprestimos.add(emprestimo);
                } catch (Exception linhaInvalida) {
                    System.out.println("Linha inválida em Emprestimo.csv, ignorada: " + linha);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler empréstimos: " + e.getMessage());
        }
        return emprestimos;
    }

    public static List<Reserva> lerReservas() {
        List<Reserva> reservas = new ArrayList<>();
        try {
            File arq = new File(DIRETORIO, "Reserva.csv");
            if (!arq.exists())
                return reservas;

            List<Livro> livros = lerLivros();
            List<Usuario> usuarios = lerUsuarios();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            BufferedReader br = new BufferedReader(new FileReader(arq));
            String linha;

            while ((linha = br.readLine()) != null) {
                try {
                    String[] campos = linha.split(";");

                    int id = Integer.parseInt(campos[0]);
                    int idLivro = Integer.parseInt(campos[1]);
                    int idUsuario = Integer.parseInt(campos[2]);
                    Date dataReserva = sdf.parse(campos[3]);
                    StatusReserva status = StatusReserva.valueOf(campos[4]);

                    Livro livro = buscarLivroPorId(livros, idLivro);
                    Usuario usuario = buscarUsuarioPorId(usuarios, idUsuario);

                    Reserva reserva = new Reserva(id, livro, usuario, dataReserva);
                    reserva.setStatusReserva(status);

                    reservas.add(reserva);
                } catch (Exception linhaInvalida) {
                    System.out.println("Linha inválida em Reserva.csv, ignorada: " + linha);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler reservas: " + e.getMessage());
        }
        return reservas;
    }

    private static Livro buscarLivroPorId(List<Livro> livros, int idLivro) {
        for (Livro l : livros) {
            if (l.getIdLivro() == idLivro) {
                return l;
            }
        }
        return null;
    }

    private static Usuario buscarUsuarioPorId(List<Usuario> usuarios, int idUsuario) {
        for (Usuario u : usuarios) {
            if (u.getIdUsuario() == idUsuario) {
                return u;
            }
        }
        return null;
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