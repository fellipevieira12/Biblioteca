package controle;

import modelo.Bibliotecaria;
import util.ManipuladorArquivos;

import java.util.List;

public class BibliotecariaControle {

    public static Bibliotecaria obterBibliotecaria(int idBibliotecaria) {
        List<Bibliotecaria> bibliotecarias = ManipuladorArquivos.lerBibliotecarias();
        return bibliotecarias.stream()
                .filter(b -> b.getIdBibliotecaria() == idBibliotecaria)
                .findFirst()
                .orElse(null);
    }
}
