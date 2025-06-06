# ProjetoCrud

# Projeto CRUD de Produtos - Java Web

![Status](https://img.shields.io/badge/status-concluído-brightgreen)
![Java](https://img.shields.io/badge/Java-8%2B-blue)
![Tomcat](https://img.shields.io/badge/Apache%20Tomcat-9-orange)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5-purple)

Um projeto web simples para gerenciamento de produtos, desenvolvido com Java, Servlets e JSP, demonstrando as operações básicas de um sistema CRUD (Create, Read, Update, Delete).

## 📋 Índice

- [Descrição](#-descrição)
- [Funcionalidades](#-funcionalidades)
- [Demonstração](#-demonstração)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Como Executar o Projeto](#-como-executar-o-projeto)
- [Acessando o Banco de Dados](#-acessando-o-banco-de-dados)
- [Autor](#-autor)

## 📖 Descrição

Este projeto é uma aplicação web construída para fins de aprendizado e portfólio. Ele simula um pequeno sistema de controle de estoque, permitindo ao usuário realizar as quatro operações fundamentais de manipulação de dados em um banco de dados relacional.

A arquitetura segue o padrão **Model-View-Controller (MVC)**, onde:
- **Model:** As classes `Produto` (POJO) e `ProdutoDAO` (Data Access Object) representam os dados e a lógica de negócio.
- **View:** As páginas `.jsp` (JavaServer Pages) são responsáveis pela apresentação dos dados ao usuário.
- **Controller:** A classe `ProdutoServlet` atua como o controlador, recebendo as requisições HTTP, processando as ações e direcionando para a View apropriada.

## ✨ Funcionalidades

-   [x] **Cadastrar** novos produtos.
-   [x] **Listar** todos os produtos cadastrados em uma tabela.
-   [x] **Editar** as informações de um produto existente.
-   [x] **Excluir** um produto do sistema.
-   [x] Formulário dinâmico que se adapta para cadastro ou edição.
-   [x] Interface responsiva construída com Bootstrap.

## 📸 Demonstração

*(Dica: Tire um screenshot da tela principal da sua aplicação e adicione aqui. Você pode arrastar a imagem para a caixa de edição do GitHub)*

![Exemplo da Tela Principal](caminho/para/seu/screenshot.png)

## 🛠️ Tecnologias Utilizadas

Este projeto foi construído com as seguintes tecnologias:

-   **Backend:**
    -   Java 11+
    -   Servlets API 4.0
    -   JSP (JavaServer Pages) 2.3

-   **Banco de Dados:**
    -   H2 Database Engine (rodando em modo de memória)

-   **Frontend:**
    -   HTML5
    -   CSS3
    -   Bootstrap 5.3

-   **Ambiente de Desenvolvimento:**
    -   Apache Tomcat 9.0 (Servidor de Aplicação)
    -   Apache NetBeans IDE
    -   Git & GitHub

## 🚀 Como Executar o Projeto

Siga os passos abaixo para executar o projeto em seu ambiente local.

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:
-   [JDK](https://www.oracle.com/java/technologies/downloads/) (Java Development Kit) - Versão 11 ou superior.
-   [Apache NetBeans IDE](https://netbeans.apache.org/download/index.html) - Versão 12 ou superior.
-   [Apache Tomcat](https://tomcat.apache.org/download-90.cgi) - Versão 9, configurado no NetBeans.

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/seu-usuario/seu-repositorio.git
    ```

2.  **Abra o projeto no NetBeans:**
    -   Vá em `Arquivo > Abrir Projeto...` e selecione a pasta que você acabou de clonar.

3.  **Verifique as dependências:**
    -   O projeto já inclui a biblioteca do H2 na pasta `lib`. O NetBeans deve reconhecê-la automaticamente.

4.  **Limpe e Construa o projeto:**
    -   Clique com o botão direito sobre o nome do projeto e selecione `Limpar e Construir`.

5.  **Execute o projeto:**
    -   Clique com o botão direito novamente e selecione `Executar`. O NetBeans irá iniciar o Tomcat e abrir a aplicação no seu navegador padrão.
    -   A URL padrão será: `http://localhost:8080/ProjetoCrud/` (a porta pode variar).

## 🗃️ Acessando o Banco de Dados

O banco de dados H2 roda em memória e pode ser acessado através de um console web que é iniciado junto com a aplicação.

1.  Com a aplicação rodando, acesse a URL:
    **`http://localhost:8082`**

2.  Na tela de login do H2 Console, use os seguintes dados para conectar:
    -   **Driver Class:** `org.h2.Driver`
    -   **JDBC URL:** `jdbc:h2:mem:testdb`
    -   **User Name:** `sa`
    -   **Password:** (deixe em branco)

3.  Clique em `Connect`. Agora você pode executar consultas SQL, como `SELECT * FROM PRODUTOS;`, para ver os dados em tempo real.

## 👨‍💻 Autor

Feito com muita persistência por **Gabriel Dantas**.

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/seu-linkedin/)
[![github](https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/seu-usuario)
