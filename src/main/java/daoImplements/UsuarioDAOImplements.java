package daoImplements;

import com.mysql.cj.protocol.Resultset;
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

            System.out.println("Usuário cadastrado com sucesso!");

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
        return null;
    }

    @Override
    public void atualizarUsuario(Usuario usuario) {

    }

    @Override
    public void deletarUsuario(int id) {

    }
}
