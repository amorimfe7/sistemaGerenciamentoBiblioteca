package daoImplements;

import com.mysql.cj.jdbc.exceptions.SQLError;
import dao.IUsuarioDAO;
import database.sqlConn;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImplements implements IUsuarioDAO {
    @Override
    public void salvarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nome, endereco, telefone) VALUES (?, ?, ?)";

        try(Connection conn = sqlConn.getConnection()){
            //'Statement.RETURN_GENERATED_KEYS'
            // Pegando o ID do próximo aluno a ser cadastrado (o do próprio aluno que estamos cadastrando)
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEndereco());
            stmt.setString(3, usuario.getTelefone());
            stmt.executeUpdate();

            ResultSet chavePk = stmt.getGeneratedKeys();

            if(chavePk.next()){
                usuario.setId_usuario(chavePk.getInt(1));
            }

            System.out.printf("Usuário [%s] cadastrado com sucesso!\n", usuario.getNome());

        }catch (Exception e){
            throw new RuntimeException("Erro ao cadastrar Usuário ->" + e.getMessage());
        }

    }

    @Override
    public List<Usuario> listarUsuario() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> listaUsuarios = new ArrayList<>();

        try(Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                listaUsuarios.add(new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("telefone"))
                );
            }

        } catch (SQLException e){
            throw new RuntimeException("Erro ao listar Usuários -> " + e.getMessage());
        }

        return listaUsuarios;
    }

    @Override
    public Usuario buscarUsuarioPorId(int id) {
        String sql = ("SELECT id_usuario, nome, endereco, telefone FROM usuario WHERE id_usuario = ?");
        Usuario usuarioEncontrado = null;

        try(Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1,id);

            ResultSet rs = stmt.executeQuery();

           while (rs.next()){
                usuarioEncontrado = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("telefone")
                );
            };

        } catch (SQLException e) {
            System.err.println("Erro ao tentar buscar usuário! -> " + e.getMessage());
        }

        return usuarioEncontrado;

    }

    @Override
    public Usuario atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, endereco = ?, telefone = ? WHERE id_usuario = ?";
        Usuario usuarioAtualizado = null;

        try (Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEndereco());
            stmt.setString(3, usuario.getTelefone());
            stmt.setInt(4, usuario.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if(linhasAfetadas > 0) {
                System.out.printf("Usuário [%d] atualizado com sucesso!\n", usuario.getId());
            } else {
                System.out.println("Nenhum usuário encontrado com esse ID\n");
            }

        } catch (SQLException e){
            System.err.println("Erro ao tentar atualizar usuário! ->" + e.getMessage());
        }
        return usuarioAtualizado;
    }

    @Override
    public void deletarUsuario(int id) {
        String sql = "DELETE from usuario WHERE id_usuario = ?";

        try(Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.printf("Usuário [%d] deletado com sucesso!\n",id);

        } catch (SQLException e){
            System.err.println("Erro ao tentar deletar usuário! -> " + e.getMessage());
        }

    }
}
