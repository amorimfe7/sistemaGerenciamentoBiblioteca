package dao;

import model.Livro;

import java.util.List;

public interface ILivroDAO {

    //C
    void salvarLivro(Livro livro);

    //R / R por ID
    List<Livro> listarLivros();
    Livro buscaLivroPorId(int id);

    //U
    Livro atualizarLivro(Livro livro);

    //D
    void deletarLivro(int ido);
}
