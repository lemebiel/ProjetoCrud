// Arquivo: dao/ProdutoDAO.java
// VERSÃO COMPLETA E AJUSTADA COM MENSAGENS DE DEBUG
package dao;

import model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    
    // Configurações para o banco de dados H2 em memória
    private final String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private final String user = "sa";
    private final String password = "";

    public ProdutoDAO() {
        try {
            // Carrega o driver do H2
            Class.forName("org.h2.Driver");
            criarTabela();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection conectar() throws SQLException {
        // Conecta usando as credenciais do H2
        return DriverManager.getConnection(url, user, password);
    }

    private void criarTabela() {
        // O SQL é praticamente o mesmo
        String sql = """
            CREATE TABLE IF NOT EXISTS produtos (
                id INT PRIMARY KEY AUTO_INCREMENT,
                nome VARCHAR(255) NOT NULL,
                preco DOUBLE NOT NULL,
                quantidade INT NOT NULL
            );
        """;

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Produto p) {
        String sql = "INSERT INTO produtos (nome, preco, quantidade) VALUES (?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.println("DAO: Inserindo produto: " + p.getNome()); // DEBUG
            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco());
            ps.setInt(3, p.getQuantidade());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto", e);
        }
    }
    
    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos", e);
        }
        return lista;
    }

    public Produto getProdutoById(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        Produto produto = null;

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por ID", e);
        }
        return produto;
    }
    
    public void atualizar(Produto p) {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, quantidade = ? WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            // DEBUG: VAMOS VER O QUE O DAO ESTÁ TENTANDO ATUALIZAR
            System.out.println("DAO: Tentando ATUALIZAR produto com ID: " + p.getId() + ", Novo Nome: " + p.getNome());

            ps.setString(1, p.getNome());
            ps.setDouble(2, p.getPreco());
            ps.setInt(3, p.getQuantidade());
            ps.setInt(4, p.getId());
            
            int linhasAfetadas = ps.executeUpdate(); // Captura o resultado
            System.out.println("DAO: Update executado. Linhas afetadas: " + linhasAfetadas); // DEBUG

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover produto", e);
        }
    }
}