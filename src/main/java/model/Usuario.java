package model;

public class Usuario {
    private int id_usuario;
    private String nome;
    private String endereco;
    private String telefone;

    //CADASTRAR USUARIO
    public Usuario(String nome, String endereco, String telefone){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    //BUSCAR USUARIO
    public Usuario(int id_usuario, String nome, String endereco, String telefone){
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public int getId(){
        return id_usuario;
    }

    public String getNome(){
        return nome;
    }

    public String getEndereco(){
        return endereco;
    }

    public String getTelefone(){
        return telefone;
    };

    public String toString(){
        return String.format(
                "ID: %d | "+
                "Nome: %s | " +
                "Endereço: %s | " +
                "Telefone: %s", id_usuario, nome, endereco, telefone);
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
