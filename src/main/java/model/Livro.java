package model;

public class Livro {
    private int id_livro;
    private String nome;
    private String autor;
    private String editora;

    //CADASTRO LIVRO
    public Livro(String nome, String autor, String editora) {
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

    public Livro(){

    }


    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }


    public String toString(){
        return String.format(
                "ID: %d | "+
                "Nome: %s | " +
                "Autor: %s | " +
                "Editora: %s", id_livro, nome, autor, editora);
    }

}
