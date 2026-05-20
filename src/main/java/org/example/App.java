package org.example;

import daoImplements.UsuarioDAOImplements;
import database.sqlConn;
import model.Usuario;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        UsuarioDAOImplements usuarioDAOMetodos = new UsuarioDAOImplements();
        Scanner sc = new Scanner(System.in);
        sqlConn.testConnection();

    }
}
