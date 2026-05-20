package dao;

import model.Usuario;

import java.util.List;

public interface IUsuarioDAO {

    //C
    void salvarUsuario(Usuario usuario);

    //R
    List<Usuario> listarUsuario();
    Usuario buscarUsuarioPorId(int id);

    //U
    Usuario atualizarUsuario(Usuario usuario);

    //D
    void deletarUsuario(int id);

}
