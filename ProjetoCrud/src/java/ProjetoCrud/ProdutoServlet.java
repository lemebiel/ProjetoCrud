// Arquivo: ProjetoCrud/ProdutoServlet.java
// VERSÃO COMPLETA E AJUSTADA COM MENSAGENS DE DEBUG
package ProjetoCrud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.List;
import java.sql.SQLException;
import org.h2.tools.Server;

import dao.ProdutoDAO;
import model.Produto;

@WebServlet("/produtos")
public class ProdutoServlet extends HttpServlet {

    private ProdutoDAO dao;
    private Server h2Server;

    @Override
    public void init() throws ServletException {
        dao = new ProdutoDAO(); 

        try {
            h2Server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            System.out.println("*****************************************************");
            System.out.println("Console H2 iniciado! Acesse em: http://localhost:8082");
            System.out.println("*****************************************************");
        } catch (SQLException e) {
            throw new ServletException("Falha ao iniciar o console H2", e);
        }
    }

    @Override
    public void destroy() {
        if (h2Server != null) {
            h2Server.stop();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        if ("editar".equals(acao)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Produto p = dao.getProdutoById(id);
            request.setAttribute("produtoEditar", p);
        } else if ("excluir".equals(acao)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.remover(id);
            response.sendRedirect(request.getContextPath() + "/produtos");
            return;
        }
        List<Produto> produtos = dao.listar();
        request.setAttribute("produtos", produtos);
        request.getRequestDispatcher("/produtos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        // DEBUG: VAMOS VER O ID QUE CHEGOU DO FORMULÁRIO
        System.out.println("SERVLET: doPost chamado. ID recebido: '" + idStr + "'"); 

        String nome = request.getParameter("nome");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        double preco = Double.parseDouble(request.getParameter("preco"));

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setQuantidade(quantidade);
        produto.setPreco(preco);

        if (idStr == null || idStr.isEmpty()) {
            System.out.println("SERVLET: Ação -> INSERIR"); // DEBUG
            dao.inserir(produto);
        } else {
            System.out.println("SERVLET: Ação -> ATUALIZAR"); // DEBUG
            int id = Integer.parseInt(idStr);
            produto.setId(id);
            dao.atualizar(produto);
        }

        response.sendRedirect(request.getContextPath() + "/produtos");
    }
}