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

        Livro livro = LivroControle.obterLivro(idLivro);
        Usuario usuario = UsuarioControle.obterUsuario(idUsuario);

        int id = ManipuladorArquivos.proximoId("Emprestimo.csv");
        Emprestimo emprestimo = new Emprestimo(id, livro, usuario, new Date());

        BibliotecariaControle.obterBibliotecaria(idBibliotecaria).registrarEmprestimo(emprestimo);
        ManipuladorArquivos.salvarEmprestimo(emprestimo);

        JOptionPane.showMessageDialog(tela, "Empréstimo registrado com sucesso!\nPrazo de devolução: 7 dias.");
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
        String avisoMulta = "";

        if (diasEmprestado > diasPermitidos) {
            long diasAtraso = diasEmprestado - diasPermitidos;
            double valorMulta = diasAtraso * 2.0;
            avisoMulta = String.format("\n\n⚠️ ATRASO DETECTADO: %d dias.\nMulta a ser cobrada: R$ %.2f", diasAtraso,
                    valorMulta);
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
                    "Devolução registrada!" + avisoMulta
                            + "\n\nATENÇÃO: Este livro possui reserva na fila de espera.\nStatus alterado para RESERVADO.");
        } else {
            JOptionPane.showMessageDialog(tela, "Devolução registrada com sucesso! Livro DISPONÍVEL." + avisoMulta);
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
}