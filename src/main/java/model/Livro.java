package model;

public class Livro {
    private int id_livro;
    private String nome;
    private String autor;
    private String editora;

    //CADASTRO LIVRO
    public Livro(String nome, String autor, String editora){
        this.nome = nome;
        this.autor = autor;
        this.editora = editora;
    }

    //BUSCAR/LISTAR LIVRO
    public Livro(int id_livro, String nome, String autor, String editora){
        this.id_livro = id_livro;
        this.nome = nome;
        this.autor = autor;
        this.editora = editora;
    }

}
