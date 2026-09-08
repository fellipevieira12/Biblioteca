import modelo.Bibliotecaria;
import util.ManipuladorArquivos;
import visao.menus.MenuInicial;

public class Main {

        public static void main(String[] args) {

                if (ManipuladorArquivos.lerBibliotecarias().isEmpty()) {
                        Bibliotecaria admin = new Bibliotecaria(1, "admin");
                        ManipuladorArquivos.salvarBibliotecaria(admin);
                }

                new MenuInicial();
        }
}
