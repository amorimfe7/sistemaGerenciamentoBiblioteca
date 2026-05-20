package daoImplements;

import dao.ILivroDAO;
import database.sqlConn;
import model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LivroDAOImplements implements ILivroDAO {
    @Override
    public void salvarLivro(Livro livro) {
        String sql = "INSERT INTO livro(nome, autor, editora) VALUES (?, ?, ?)";

        try (Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, livro.getNome());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());

            stmt.executeUpdate();

            System.out.printf("O livro '%s' foi cadastrado com sucesso!", livro.getNome());

        } catch (SQLException e){
            System.err.println("Erro ao tentar cadastrar livro! ->" + e.getMessage());
        }

    }

    @Override
    public List<Livro> listarLivros() {
        return List.of();
    }

    @Override
    public Livro buscaLivroPorId(int id) {
        return null;
    }

    @Override
    public Livro atualizarLivro(Livro livro) {
        return null;
    }

    @Override
    public void deletarLivro(int ido) {

    }
}
