package controle;

import modelo.Emprestimo;
import modelo.Livro;
import modelo.Reserva;
import modelo.StatusLivro;
import modelo.StatusReserva;
import modelo.Usuario;
import util.ManipuladorArquivos;

import javax.swing.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmprestimoControle {

    public static List<Emprestimo> listarEmprestimosAtivos() {
        Map<Integer, Emprestimo> ultimoRegistroPorId = new LinkedHashMap<>();

        for (Emprestimo emprestimo : ManipuladorArquivos.lerEmprestimos()) {
            ultimoRegistroPorId.put(emprestimo.getIdEmprestimo(), emprestimo);
        }

        return ultimoRegistroPorId.values().stream()
                .filter(Emprestimo::getAtivo)
                .filter(e -> e.getLivro() != null && e.getUsuario() != null)
                .collect(Collectors.toList());
    }

    public static List<Emprestimo> listarTodosEmprestimos() {
        return ManipuladorArquivos.lerEmprestimos();
    }

    public static List<Livro> listarLivrosDisponiveis() {
        List<Integer> idsEmprestados = listarEmprestimosAtivos().stream()
                .map(e -> e.getLivro().getIdLivro())
                .collect(Collectors.toList());

        return ManipuladorArquivos.lerLivros().stream()
                .filter(l -> l.getStatus() == StatusLivro.DISPONIVEL && !idsEmprestados.contains(l.getIdLivro()))
                .collect(Collectors.toList());
    }

    public static void registrarEmprestimo(Integer idLivro, Integer idUsuario, JFrame tela, int idBibliotecaria) {
        if (idLivro == null || idUsuario == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um livro e um usuário.");
            return;
        }

        boolean livroJaEmprestado = listarEmprestimosAtivos().stream()
                .anyMatch(e -> e.getLivro() != null && e.getLivro().getIdLivro() == idLivro);

        if (livroJaEmprestado) {
            JOptionPane.showMessageDialog(tela, "Não é possível emprestar: livro já está emprestado no momento.");
            return;
        }

        List<Reserva> reservasAtivas = ManipuladorArquivos.lerReservas().stream()
                .filter(r -> r.getLivro() != null && r.getLivro().getIdLivro() == idLivro
                        && r.getStatusReserva() == StatusReserva.ATIVA)
                .collect(Collectors.toList());

        Reserva reservaDoUsuario = reservasAtivas.stream()
                .filter(r -> r.getUsuario() != null && r.getUsuario().getIdUsuario() == idUsuario)
                .findFirst()
                .orElse(null);

        if (!reservasAtivas.isEmpty() && reservaDoUsuario == null) {
            JOptionPane.showMessageDialog(tela, "Não é possível emprestar: livro está reservado para outro usuário.");
            return;
        }

        Livro livro = LivroControle.obterLivro(idLivro);
        Usuario usuario = UsuarioControle.obterUsuario(idUsuario);

        int id = ManipuladorArquivos.proximoId("Emprestimo.csv");
        Emprestimo emprestimo = new Emprestimo(id, livro, usuario, new Date());

        BibliotecariaControle.obterBibliotecaria(idBibliotecaria).registrarEmprestimo(emprestimo);
        ManipuladorArquivos.salvarEmprestimo(emprestimo);

        String avisoReserva = "";
        if (reservaDoUsuario != null) {
            List<Reserva> todasReservas = ManipuladorArquivos.lerReservas();
            for (Reserva r : todasReservas) {
                if (r.getIdReserva() == reservaDoUsuario.getIdReserva()) {
                    r.setStatusReserva(StatusReserva.CONCLUIDA);
                    break;
                }
            }
            ManipuladorArquivos.reescreverArquivoReservas(todasReservas);
            avisoReserva = "\n\nReserva referente a este livro foi baixada automaticamente.";
        }

        List<Livro> livros = ManipuladorArquivos.lerLivros();
        for (Livro l : livros) {
            if (l.getIdLivro() == idLivro) {
                l.setStatus(StatusLivro.EMPRESTADO);
                break;
            }
        }
        ManipuladorArquivos.reescreverArquivoLivros(livros);

        JOptionPane.showMessageDialog(tela,
                "Empréstimo registrado com sucesso!\nPrazo de devolução: 7 dias." + avisoReserva);
        tela.dispose();
        new visao.menus.MenuBibliotecaria(idBibliotecaria);
    }

    public static void registrarDevolucao(Integer idEmprestimo, JFrame tela, int idBibliotecaria) {
        if (idEmprestimo == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um empréstimo.");
            return;
        }

        Emprestimo emprestimo = listarEmprestimosAtivos().stream()
                .filter(e -> e.getIdEmprestimo() == idEmprestimo)
                .findFirst()
                .orElse(null);

        if (emprestimo == null) {
            JOptionPane.showMessageDialog(tela, "Empréstimo não encontrado.");
            return;
        }

        Date dataAtual = new Date();
        long diferencaMilissegundos = dataAtual.getTime() - emprestimo.getDataEmprestimo().getTime();
        long diasEmprestado = TimeUnit.DAYS.convert(diferencaMilissegundos, TimeUnit.MILLISECONDS);

        int diasPermitidos = 7;
        String avisoPrazo;

        if (diasEmprestado > diasPermitidos) {
            long diasAtraso = diasEmprestado - diasPermitidos;
            double valorMulta = diasAtraso * 2.0;
            avisoPrazo = String.format("\n\n⚠️ ATRASO DETECTADO: %d dias.\nMulta a ser cobrada: R$ %.2f", diasAtraso,
                    valorMulta);
        } else {
            long diasRestantes = diasPermitidos - diasEmprestado;
            avisoPrazo = String.format("\n\n✅ Devolução DENTRO DO PRAZO (%d dia(s) de folga).", diasRestantes);
        }

        BibliotecariaControle.obterBibliotecaria(idBibliotecaria).registrarDevolucao(emprestimo);
        ManipuladorArquivos.salvarEmprestimo(emprestimo);

        Livro livroDevolvido = emprestimo.getLivro();
        List<Reserva> reservas = ManipuladorArquivos.lerReservas();

        boolean temReserva = reservas.stream()
                .anyMatch(r -> r.getLivro().getIdLivro() == livroDevolvido.getIdLivro()
                        && r.getStatusReserva() == StatusReserva.ATIVA);

        List<Livro> todosLivros = ManipuladorArquivos.lerLivros();
        for (Livro l : todosLivros) {
            if (l.getIdLivro() == livroDevolvido.getIdLivro()) {
                if (temReserva) {
                    l.setStatus(StatusLivro.RESERVADO);
                } else {
                    l.setStatus(StatusLivro.DISPONIVEL);
                }
                break;
            }
        }
        ManipuladorArquivos.reescreverArquivoLivros(todosLivros);

        if (temReserva) {
            JOptionPane.showMessageDialog(tela,
                    "Devolução registrada!" + avisoPrazo
                            + "\n\nATENÇÃO: Este livro possui reserva na fila de espera.\nStatus alterado para RESERVADO.");
        } else {
            JOptionPane.showMessageDialog(tela, "Devolução registrada com sucesso! Livro DISPONÍVEL." + avisoPrazo);
        }

        tela.dispose();
        new visao.menus.MenuBibliotecaria(idBibliotecaria);
    }

    public static void solicitarEmprestimo(Integer idLivro, int idUsuario, JFrame tela) {
        if (idLivro == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um livro.");
            return;
        }

        Livro livro = LivroControle.obterLivro(idLivro);
        Usuario usuario = UsuarioControle.obterUsuario(idUsuario);

        int id = ManipuladorArquivos.proximoId("Emprestimo.csv");
        Emprestimo emprestimo = new Emprestimo(id, livro, usuario, new Date());

        usuario.solicitarEmprestimo();
        livro.alterarStatus(StatusLivro.EMPRESTADO);
        ManipuladorArquivos.salvarEmprestimo(emprestimo);

        JOptionPane.showMessageDialog(tela, "Empréstimo solicitado com sucesso!\nPrazo padrão: 7 dias.");
        tela.dispose();
        new visao.menus.MenuUsuario(idUsuario);
    }

    public static void editarEmprestimo(Integer idEmprestimo, String novaDataStr, JFrame tela, int idBibliotecaria) {
        if (idEmprestimo == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um empréstimo.");
            return;
        }

        if (novaDataStr.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha a data.");
            return;
        }

        Date novaData;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            novaData = sdf.parse(novaDataStr);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(tela, "Data inválida. Use o formato dd/MM/yyyy.");
            return;
        }

        List<Emprestimo> emprestimos = ManipuladorArquivos.lerEmprestimos();
        Emprestimo encontrado = null;

        for (Emprestimo e : emprestimos) {
            if (e.getIdEmprestimo() == idEmprestimo) {
                e.setDataEmprestimo(novaData);
                encontrado = e;
                break;
            }
        }

        if (encontrado == null) {
            JOptionPane.showMessageDialog(tela, "Empréstimo não encontrado.");
            return;
        }

        ManipuladorArquivos.reescreverArquivoEmprestimos(emprestimos);

        JOptionPane.showMessageDialog(tela, "Empréstimo atualizado com sucesso!");
        tela.dispose();
        new visao.menus.MenuBibliotecaria(idBibliotecaria);
    }

    public static void excluirEmprestimo(Integer idEmprestimo, JFrame tela, int idBibliotecaria) {
        if (idEmprestimo == null) {
            JOptionPane.showMessageDialog(tela, "Selecione um empréstimo.");
            return;
        }

        Emprestimo emprestimo = ManipuladorArquivos.lerEmprestimos().stream()
                .filter(e -> e.getIdEmprestimo() == idEmprestimo)
                .findFirst()
                .orElse(null);

        if (emprestimo == null) {
            JOptionPane.showMessageDialog(tela, "Empréstimo não encontrado.");
            return;
        }

        if (Boolean.TRUE.equals(emprestimo.getAtivo()) && emprestimo.getLivro() != null) {
            List<Livro> livros = ManipuladorArquivos.lerLivros();
            for (Livro l : livros) {
                if (l.getIdLivro() == emprestimo.getLivro().getIdLivro()) {
                    l.setStatus(StatusLivro.DISPONIVEL);
                    break;
                }
            }
            ManipuladorArquivos.reescreverArquivoLivros(livros);
        }

        ManipuladorArquivos.removerEmprestimo(idEmprestimo);

        JOptionPane.showMessageDialog(tela, "Empréstimo excluído com sucesso!");
        tela.dispose();
        new visao.menus.MenuBibliotecaria(idBibliotecaria);
    }
    
    public static List<Emprestimo> listarEmprestimosEmAtraso() {
        Date hoje = new Date();
        int diasPermitidos = 7;

        return listarEmprestimosAtivos().stream()
                .filter(e -> {
                    long diferencaMilissegundos = hoje.getTime() - e.getDataEmprestimo().getTime();
                    long diasEmprestado = TimeUnit.DAYS.convert(diferencaMilissegundos, TimeUnit.MILLISECONDS);
                    return diasEmprestado > diasPermitidos;
                })
                .collect(Collectors.toList());
    }

    public static long calcularDiasAtraso(Emprestimo emprestimo) {
        Date hoje = new Date();
        int diasPermitidos = 7;
        long diferencaMilissegundos = hoje.getTime() - emprestimo.getDataEmprestimo().getTime();
        long diasEmprestado = TimeUnit.DAYS.convert(diferencaMilissegundos, TimeUnit.MILLISECONDS);
        return Math.max(0, diasEmprestado - diasPermitidos);
    }

}