package util;

import java.sql.Connection;
import java.sql.*;

public class ConexaoPostgreSQL {

    private Connection conn;

    public boolean conectar() {
        try {
            String url = "jdbc:postgresql://localhost:5432/estoque";
            String usuario = "postgres";
            String senha = "Brunolira1";

            this.conn = DriverManager.getConnection(url,usuario,senha);
            System.out.println("Conectado");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean desconectar() {
        try {
            if (this.conn.isClosed() == false) {
                this.conn.close();
            }
             System.out.println("Desconectado");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Statement criarStatment() {
        try {
            return this.conn.createStatement();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    public PreparedStatement criarPreparedStatement(String sql, int RETURN_GENERATED_KEYS) {
        try {
             System.out.println("Executando");
            return conn.prepareStatement(sql, RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public PreparedStatement criarPreparedStatement(String sql) {
        try {
            return this.conn.prepareStatement(sql);
        } catch (SQLException e) {
            return null;
        }

    }
}
