package util;

import java.sql.Connection;
import java.sql.*;

public class ConexaoPostgreSQL {

    private Connection conn;

    public boolean conectar() {
        try {
            String url = "jdbc:postgresql://localhost:5432/estoque";

            this.conn = DriverManager.getConnection(url);
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
