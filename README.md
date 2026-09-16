# Sistema de Biblioteca

Projeto acadêmico da disciplina de Programação Orientada a Objetos (UNIVAS),
desenvolvido a partir da UML da pasta `UML/`.

Aplicação desktop em Java (Swing) organizada no padrão **MVC**, com
persistência simples em arquivos `.csv` e relacionamento entre classes por
**composição** (ex.: um `Emprestimo` guarda o objeto `Livro` e o objeto
`Usuario` completos, não apenas seus IDs).

## Estrutura

```
src/
├── Main.java
├── modelo/
│   ├── Livro.java
│   ├── Usuario.java
│   ├── Bibliotecaria.java
│   ├── Emprestimo.java          // tem-um Livro, tem-um Usuario
│   ├── Reserva.java             // tem-um Livro, tem-um Usuario
│   ├── StatusLivro.java
│   └── StatusReserva.java
├── controle/
│   ├── LivroControle.java
│   ├── UsuarioControle.java
│   ├── BibliotecariaControle.java
│   ├── EmprestimoControle.java
│   └── ReservaControle.java
├── visao/
│   ├── menus/                   // MenuInicial, MenuBibliotecaria, MenuUsuario
│   └── telas/                   // cadastro, exclusão, empréstimo, devolução, reserva
└── util/
    └── ManipuladorArquivos.java // leitura/escrita dos .csv em dados/
```

## Como executar

1. Abra a pasta do projeto no IntelliJ/Eclipse/NetBeans/VS Code.
2. Compile e execute a classe `Main` (pacote raiz).
3. Na primeira execução, `Main` cria automaticamente uma bibliotecária
   padrão (`admin`) caso `dados/Bibliotecaria.csv` esteja vazio.
4. A tela inicial (`MenuInicial`) permite entrar como **Bibliotecária** ou
   **Usuário** e navegar pelas telas de cadastro, empréstimo, devolução e
   reserva.

## Persistência

Todos os dados (livros, usuários, bibliotecárias, empréstimos e reservas)
são salvos em arquivos `.csv` dentro da pasta `dados/`. A composição entre
objetos (ex.: `Emprestimo` → `Livro`/`Usuario`) existe apenas em memória:
no CSV continuam sendo gravados os IDs, e `ManipuladorArquivos` reconstrói
os objetos completos ao ler o arquivo, buscando cada `Livro`/`Usuario`
pelo ID antes de instanciar o `Emprestimo`/`Reserva`.
