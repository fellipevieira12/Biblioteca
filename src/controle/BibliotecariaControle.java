package controle;

import modelo.Bibliotecaria;
import util.ManipuladorArquivos;

import javax.swing.*;
import java.util.List;

public class BibliotecariaControle {

    public static Bibliotecaria obterBibliotecaria(int idBibliotecaria) {
        List<Bibliotecaria> bibliotecarias = ManipuladorArquivos.lerBibliotecarias();
        return bibliotecarias.stream()
                .filter(b -> b.getIdBibliotecaria() == idBibliotecaria)
                .findFirst()
                .orElse(null);
    }

    public static List<Bibliotecaria> listarTodasBibliotecarias() {
        return ManipuladorArquivos.lerBibliotecarias();
    }

    public static void cadastrarBibliotecaria(String nome, JFrame tela, int idBibliotecariaLogada) {
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o nome.");
            return;
        }

        int id = ManipuladorArquivos.proximoId("Bibliotecaria.csv");
        Bibliotecaria bibliotecaria = new Bibliotecaria(id, nome);
        ManipuladorArquivos.salvarBibliotecaria(bibliotecaria);

        JOptionPane.showMessageDialog(tela, "Bibliotecária cadastrada com sucesso!");
        tela.dispose();
        new visao.menus.MenuBibliotecaria(idBibliotecariaLogada);
    }

    public static void editarBibliotecaria(Integer idBibliotecaria, String novoNome, JFrame tela, int idBibliotecariaLogada) {
        if (idBibliotecaria == null) {
            JOptionPane.showMessageDialog(tela, "Selecione uma bibliotecária.");
            return;
        }

        if (novoNome.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha o nome.");
            return;
        }

        List<Bibliotecaria> bibliotecarias = ManipuladorArquivos.lerBibliotecarias();
        Bibliotecaria encontrada = null;

        for (int i = 0; i < bibliotecarias.size(); i++) {
            Bibliotecaria b = bibliotecarias.get(i);
            if (b.getIdBibliotecaria() == idBibliotecaria) {
                Bibliotecaria atualizada = new Bibliotecaria(b.getIdBibliotecaria(), novoNome);
                bibliotecarias.set(i, atualizada);
                encontrada = atualizada;
                break;
            }
        }

        if (encontrada == null) {
            JOptionPane.showMessageDialog(tela, "Bibliotecária não encontrada.");
            return;
        }

        ManipuladorArquivos.reescreverArquivoBibliotecarias(bibliotecarias);

        JOptionPane.showMessageDialog(tela, "Bibliotecária atualizada com sucesso!");
        tela.dispose();
        new visao.menus.MenuBibliotecaria(idBibliotecariaLogada);
    }

    public static void excluirBibliotecaria(Integer idBibliotecaria, JFrame tela, int idBibliotecariaLogada) {
        if (idBibliotecaria == null) {
            JOptionPane.showMessageDialog(tela, "Selecione uma bibliotecária.");
            return;
        }

        if (idBibliotecaria == idBibliotecariaLogada) {
            JOptionPane.showMessageDialog(tela, "Não é possível excluir a bibliotecária logada no momento.");
            return;
        }

        if (listarTodasBibliotecarias().size() <= 1) {
            JOptionPane.showMessageDialog(tela, "Não é possível excluir: precisa existir ao menos uma bibliotecária.");
            return;
        }

        ManipuladorArquivos.removerBibliotecaria(idBibliotecaria);

        JOptionPane.showMessageDialog(tela, "Bibliotecária excluída com sucesso!");
        tela.dispose();
        new visao.menus.MenuBibliotecaria(idBibliotecariaLogada);
    }
}