# Sistema de Biblioteca

Projeto acadêmico da disciplina de Programação Orientada a Objetos (UNIVAS),
desenvolvido a partir da UML da pasta `UML/`.

Aplicação desktop em Java (Swing) organizada no padrão **MVC**, com
persistência simples em arquivos `.csv` e relacionamento entre classes por
**composição** (ex.: um `Emprestimo` guarda o objeto `Livro` e o objeto
`Usuario` completos, não apenas seus IDs).

## Estrutura

```
Biblioteca/
├── UML/
│   └── UML biblioteca.jpg       // diagrama de classes do professor
├── dados/                        // persistência em .csv
│   ├── Bibliotecaria.csv
│   ├── Emprestimo.csv
│   ├── Livro.csv
│   ├── Reserva.csv
│   └── Usuario.csv
└── src/
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
    │   ├── menus/
    │   │   ├── MenuInicial.java
    │   │   ├── MenuBibliotecaria.java
    │   │   └── MenuUsuario.java
    │   └── telas/
    │       ├── TelaCadastroLivro.java
    │       ├── TelaCadastroUsuario.java
    │       ├── TelaExcluirLivro.java
    │       ├── TelaExcluirUsuario.java
    │       ├── TelaListarLivros.java
    │       ├── TelaListarUsuarios.java
    │       ├── TelaRegistrarEmprestimo.java
    │       ├── TelaRegistrarDevolucao.java
    │       ├── TelaReservarLivro.java
    │       ├── TelaSolicitarEmprestimo.java
    │       ├── TelaMinhasReservas.java
    │       ├── TelaEditarLivro.java          // criada, ainda vazia
    │       ├── TelaEditarUsuario.java        // criada, ainda vazia
    │       ├── TelaListarEmprestimos.java    // criada, ainda vazia
    │       └── TelaListarReservas.java       // criada, ainda vazia
    └── util/
        └── ManipuladorArquivos.java // leitura/escrita dos .csv em dados/
```

## Como executar

1. Abra a pasta do projeto no IntelliJ/Eclipse/NetBeans/VS Code.
2. Compile e execute a classe `Main` (pacote raiz).
3. Na primeira execução, `Main` cria automaticamente uma bibliotecária
   padrão (`admin`) caso `dados/Bibliotecaria.csv` esteja vazio.
4. A tela inicial (`MenuInicial`) permite entrar como **Bibliotecária** ou
   **Usuário** e navegar pelas telas de cadastro, exclusão, listagem,
   empréstimo, devolução e reserva.

## Funcionalidades por perfil

**Bibliotecária** (`MenuBibliotecaria`): cadastrar/excluir livro,
cadastrar/excluir usuário, registrar empréstimo, registrar devolução,
listar livros e listar usuários (tabelas com `JTable`, cada uma com botão
"Voltar" para o menu).

**Usuário** (`MenuUsuario`): solicitar empréstimo, reservar livro, consultar
"Minhas Reservas" e consultar o **Histórico de Leitura** (empréstimos já
devolvidos).

> Edição de livro/usuário e listagem de empréstimos/reservas já têm telas
> (`TelaEditarLivro`, `TelaEditarUsuario`, `TelaListarEmprestimos`,
> `TelaListarReservas`) criadas na estrutura do projeto, mas ainda não
> implementadas nem ligadas aos menus.

## Regras de empréstimo, devolução e reserva

- Prazo padrão de empréstimo: **7 dias**. Na devolução, se o prazo foi
  ultrapassado, o sistema calcula automaticamente a **multa por atraso**
  (R$ 2,00/dia) e exibe o valor para a bibliotecária.
- Se o livro devolvido tiver uma **reserva ativa** na fila, seu status muda
  para `RESERVADO` em vez de `DISPONIVEL` — e ele continua aparecendo nas
  telas de empréstimo, podendo ser emprestado normalmente a partir daí.
- O usuário pode consultar seu **Histórico de Leitura** (empréstimos já
  devolvidos) pelo próprio `MenuUsuario`.

## Persistência

Todos os dados (livros, usuários, bibliotecárias, empréstimos e reservas)
são salvos em arquivos `.csv` dentro da pasta `dados/`. A composição entre
objetos (ex.: `Emprestimo` → `Livro`/`Usuario`) existe apenas em memória:
no CSV continuam sendo gravados os IDs, e `ManipuladorArquivos` reconstrói
os objetos completos ao ler o arquivo, buscando cada `Livro`/`Usuario`
pelo ID antes de instanciar o `Emprestimo`/`Reserva`.