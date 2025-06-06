<%@ page import="model.Produto, java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gerenciamento de Produtos</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f0f4f8;
        }
        .navbar {
            background-color: #0d6efd;
        }
        .navbar-brand, .nav-link, .navbar-text {
            color: white !important;
        }
        .card-header {
            background-color: #0d6efd;
            color: white;
        }
        .btn-primary {
            background-color: #ec7100;
            border-color: #0d6efd;
        }
        .btn-primary:hover {
            background-color: #0b5ed7;
            border-color: #0b5ed7;
        }
        .btn-secondary {
            background-color: #6c757d;
            border-color: #6c757d;
        }
        .btn-warning {
            background-color: #0b5ed7;
            border-color: #0b5ed7;
            color: #f0f4f8;
        }
        .btn-danger {
            background-color: #dc3545;
            border-color: #dc3545;
        }
        .table thead {
            background-color: #0d6efd;
            color: white;
        }
        
        
        .card-body  {
            background-color: #0d6efd;
            color: white;
        }

</style>
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg mb-4">
        <div class="container">
            <a class="navbar-brand" href="#">Sistema de Produtos</a>
            <span class="navbar-text">
                Bem-vindo!
            </span>
        </div>
    </nav>

    <div class="container">
        <!-- Título -->
        <h1 class="text-center mb-4 text-primary fw-bold">Gerenciamento de Produtos</h1>

        <!-- Formulário -->
        <div class="card mb-4 border-primary">
            <div class="card-header">
                <h5 class="mb-0">
                    <%= request.getAttribute("produtoEditar") != null ? "Editar Produto" : "Cadastrar Produto" %>
                </h5>
            </div>
            <div class="card-body">
                <form action="produtos" method="post">
                    <input type="hidden" name="id" 
                           value="<%= request.getAttribute("produtoEditar") != null ? ((Produto)request.getAttribute("produtoEditar")).getId() : "" %>">

                    <div class="mb-3">
                        <label class="form-label">Nome</label>
                        <input type="text" name="nome" class="form-control" required
                               value="<%= request.getAttribute("produtoEditar") != null ? ((Produto)request.getAttribute("produtoEditar")).getNome() : "" %>">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Quantidade</label>
                        <input type="number" name="quantidade" class="form-control" required
                               value="<%= request.getAttribute("produtoEditar") != null ? ((Produto)request.getAttribute("produtoEditar")).getQuantidade() : "" %>">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Preço (R$)</label>
                        <input type="number" step="0.01" name="preco" class="form-control" required
                               value="<%= request.getAttribute("produtoEditar") != null ? ((Produto)request.getAttribute("produtoEditar")).getPreco() : "" %>">
                    </div>

                    <div>
                        <button type="submit" class="btn btn-primary">
                            <%= request.getAttribute("produtoEditar") != null ? "Atualizar" : "Cadastrar" %>
                        </button>
                        <a href="produtos" class="btn btn-secondary">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>

        <!-- Lista -->
        <div class="card border-primary">
            <div class="card-header">
                <h5 class="mb-0">Lista de Produtos</h5>
            </div>
            <div class="card-body p-0">
                <table class="table table-hover table-striped mb-0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Quantidade</th>
                            <th>Valor (R$)</th>
                            <th class="text-center">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
                            if (produtos != null && !produtos.isEmpty()) {
                                for (Produto p : produtos) {
                        %>
                            <tr>
                                <td><%= p.getId() %></td>
                                <td><%= p.getNome() %></td>
                                <td><%= p.getQuantidade() %></td>
                                <td><%= String.format("%.2f", p.getPreco()) %></td>
                                <td class="text-center">
                                    <a href="produtos?acao=editar&id=<%=p.getId()%>" 
                                       class="btn btn-sm btn-warning">Editar</a>
                                    <a href="produtos?acao=excluir&id=<%=p.getId()%>" 
                                       class="btn btn-sm btn-danger"
                                       onclick="return confirm('Deseja realmente excluir?');">
                                       Excluir
                                    </a>
                                </td>
                            </tr>
                        <%
                                }
                            } else {
                        %>
                            <tr>
                                <td colspan="5" class="text-center">Nenhum produto cadastrado.</td>
                            </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>

    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
