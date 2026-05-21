package daoImplements;

import dao.ILivroDAO;
import database.sqlConn;
import model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String sql = "SELECT * FROM livro";
        List<Livro> listaLivros = new ArrayList<>();

        try (Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                listaLivros.add( new Livro(
                        rs.getInt("id_livro"),
                        rs.getString("nome"),
                        rs.getString("autor"),
                        rs.getString("editora")
                ));
            }

            System.out.println("Lista de livros gerada com sucesso!");

        } catch (SQLException e){
            System.err.println("Erro ao tentar listar livros cadastrados! -> " + e.getMessage());
        }

        return listaLivros;
    }

    @Override
    public Livro buscaLivroPorId(int id) {
        String sql = ("SELECT id_livro, nome, autor, editora FROM livro WHERE id_livro = ?");
        Livro livroPesquisado = new Livro();

        try(Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                livroPesquisado = new Livro(
                        rs.getInt("id_livro"),
                        rs.getString("nome"),
                        rs.getString("autor"),
                        rs.getString("editora")
                );
            }

        } catch (SQLException e){
            System.err.println("Erro ao tentar buscar livro com ID [%d]" + e.getMessage());
        }

        return livroPesquisado;
    }

    @Override
    public Livro atualizarLivro(Livro livro) {
        String sql = "UPDATE livro SET nome = ?, autor = ?, editora = ? WHERE id_livro = ?";
        Livro livroAtualizado = new Livro();

        try(Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);


            stmt.setInt(4,livro.getId_livro());
            stmt.setString(1, livro.getNome());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            int linhasAfetadas = stmt.executeUpdate();

            livroAtualizado.setId_livro(livro.getId_livro());
            livroAtualizado.setNome(livro.getNome());
            livroAtualizado.setAutor(livro.getAutor());
            livroAtualizado.setEditora(livro.getEditora());

            if(linhasAfetadas > 0){
                System.out.printf("Livro [%d] foi atualizado com sucesso!\n" , livro.getId_livro());
            } else {
                System.out.printf("Nenhum livro foi encontrado com esse ID [%d]", livro.getId_livro());
            }


        } catch (SQLException e){
            System.err.println("Erro ao tentar atualizar dados do Livro! -> " + e.getMessage());
        }

        return livroAtualizado;
    }

    @Override
    public void deletarLivro(int ido) {

    }
}
