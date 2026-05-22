package org.example;

import daoImplements.LivroDAOImplements;
import daoImplements.UsuarioDAOImplements;
import database.sqlConn;
import model.Livro;
import model.Usuario;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        UsuarioDAOImplements usuarioDAOMetodos = new UsuarioDAOImplements();
        LivroDAOImplements livroDAOMetodos = new LivroDAOImplements();
        Scanner sc = new Scanner(System.in);

        int opcaoMenu;

        sqlConn.testConnection();

        do {
            System.out.println("==== MENU =====");
            System.out.println("1. Usuários");
            System.out.println("2. Livros");
            System.out.println("0. Fechar programa");

            opcaoMenu = sc.nextInt();

            switch (opcaoMenu) {
                case 1:
                    menuUsuarios(sc, usuarioDAOMetodos);
                    break;
                case 2:
                    menuLivros(sc, livroDAOMetodos);
                    break;
            }

        } while (opcaoMenu != 0);

        System.out.println("Encerrando programa");
        System.out.print(".");
        System.out.print(".");
        System.out.print(".");

    }

    public static void menuUsuarios(Scanner sc, UsuarioDAOImplements usuarioDAOMetodos) {

        System.out.println("--- USUÁRIOS ----");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Listar Usuários Cadastrados");
        System.out.println("3. Atualizar Usuário");
        System.out.println("4. Deletar Usuário");
        System.out.println("5. Retornar para Menu");
        int opcaoUsuario = sc.nextInt();
        sc.nextLine();

        int idUsuario;
        String nome;
        String endereco;
        String telefone;

        switch (opcaoUsuario) {
            case 1:
                System.out.println("--- CADASTRO USUÁRIO ---\n");

                System.out.println("Digite o nome do usuário");
                nome = sc.nextLine();
                System.out.println("Digite o endereço do usuário:");
                endereco = sc.nextLine();
                System.out.println("Digite o telefone do usuário:");
                telefone = sc.nextLine();

                Usuario usuarioCadastrar = new Usuario(nome, endereco, telefone);
                usuarioDAOMetodos.salvarUsuario(usuarioCadastrar);
                break;

            case 2:
                System.out.println("--- LISTA DE USUÁRIOS CADASTRADOS ---\n");

                for (Usuario usuario : usuarioDAOMetodos.listarUsuario()) {
                    System.out.println(usuario);
                }
                break;

            case 3:
                System.out.println("--- ATUALIZAR USUÁRIO ---\n");

                for (Usuario usuario : usuarioDAOMetodos.listarUsuario()) {
                    System.out.println(usuario);
                }

                System.out.println("\nSelecione o ID do usuário a ser atualizado:");
                idUsuario = sc.nextInt();
                sc.nextLine();

                System.out.println("Usuário selecionado: ");
                System.out.println(usuarioDAOMetodos.buscarUsuarioPorId(idUsuario));

                System.out.println("Digite 1 para confirmar ou 2 para cancelar: ");
                int confirmaUsuarioSelecionado = sc.nextInt();
                sc.nextLine();

                boolean cancelou = false;

                while (!cancelou) {
                    if (confirmaUsuarioSelecionado == 1) {
                        System.out.println("Digite o nome do usuário");
                        nome = sc.nextLine();
                        System.out.println("Digite o endereço do usuário:");
                        endereco = sc.nextLine();
                        System.out.println("Digite o telefone do usuário:");
                        telefone = sc.nextLine();

                        Usuario usuarioAtualizar = new Usuario(idUsuario, nome, endereco, telefone);
                        usuarioDAOMetodos.atualizarUsuario(usuarioAtualizar);

                        cancelou = true;
                    } else {
                        cancelou = true;
                        System.out.println("Operação cancelada");
                        return;
                    }
                }
                break;

            case 4:
                System.out.println("--- DELETAR USUÁRIO ---\n");

                for (Usuario usuario : usuarioDAOMetodos.listarUsuario()) {
                    System.out.println(usuario);
                }

                System.out.println("\nSelecione o ID do usuário a ser deletado:");
                idUsuario = sc.nextInt();
                sc.nextLine();

                usuarioDAOMetodos.deletarUsuario(idUsuario);
                break;
        }

    }

    public static void menuLivros(Scanner sc, LivroDAOImplements livroDAOMetodos) {

        System.out.println("--- LIVROS ----");
        System.out.println("1. Cadastrar Livro");
        System.out.println("2. Listar Livros Cadastrados");
        System.out.println("3. Atualizar Livro");
        System.out.println("4. Deletar Livro");
        System.out.println("5. Retornar para Menu");
        int opcaoUsuario = sc.nextInt();
        sc.nextLine();

        int idLivro;
        String nome;
        String autor;
        String editora;

        switch (opcaoUsuario) {
            case 1:
                System.out.println("--- CADASTRO LIVRO ---\n");

                System.out.println("Digite o nome do livro");
                nome = sc.nextLine();
                System.out.println("Digite o autor do livro:");
                autor = sc.nextLine();
                System.out.println("Digite o editora do livro:");
                editora = sc.nextLine();

                Livro livroCadastrar = new Livro(nome, autor, editora);
                livroDAOMetodos.salvarLivro(livroCadastrar);
                break;

            case 2:
                System.out.println("--- LISTA DE LIVROS CADASTRADOS ---\n");

                for (Livro livro : livroDAOMetodos.listarLivros()) {
                    System.out.println(livro);
                }
                break;

            case 3:
                System.out.println("--- ATUALIZAR LIVRO ---\n");

                for (Livro livro : livroDAOMetodos.listarLivros()) {
                    System.out.println(livro);
                }

                System.out.println("\nSelecione o ID do Livro a ser atualizado:");
                idLivro = sc.nextInt();
                sc.nextLine();

                System.out.println("Livro selecionado: ");
                System.out.println(livroDAOMetodos.buscaLivroPorId(idLivro));

                System.out.println("Digite 1 para confirmar ou 2 para cancelar: ");
                int confirmaLivroSelecionado = sc.nextInt();
                sc.nextLine();

                boolean cancelou = false;

                while (!cancelou) {
                    if (confirmaLivroSelecionado == 1) {
                        System.out.println("Digite o nome do livro");
                        nome = sc.nextLine();
                        System.out.println("Digite o autor do livro:");
                        autor = sc.nextLine();
                        System.out.println("Digite a editora do livro:");
                        editora = sc.nextLine();

                        Livro livroAtualizar = new Livro(idLivro, nome, autor, editora);
                        livroDAOMetodos.atualizarLivro(livroAtualizar);

                        cancelou = true;
                    } else {
                        System.out.println("Operação cancelada");
                        return;
                    }
                }
                break;

            case 4:
                System.out.println("--- DELETAR LIVRO ---\n");

                for (Livro livro : livroDAOMetodos.listarLivros()) {
                    System.out.println(livro);
                }

                System.out.println("\nSelecione o ID do Livro a ser deletado:");
                idLivro = sc.nextInt();
                sc.nextLine();

                livroDAOMetodos.deletarLivro(idLivro);
                break;
        };

    }
}