package DAO;

import model.ModelUsuario;
import util.ConexaoPostgreSQL;
import java.sql.*;

/**
 *
 * @author Bruno
 */
public class DAOUsuario extends ConexaoPostgreSQL {

    public boolean salvarUsuarioDAO(ModelUsuario usuario) {
        conectar();
        //executar SQL
        String sql
                = "INSERT INTO tbl_usuario("
                + "usu_nome, "
                + "usu_login, "
                + "usu_senha) "
                + "VALUES (?,?,?)";

        PreparedStatement ps = criarPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            ps.setString(1, usuario.getUsuNome());
            ps.setString(2, usuario.getUsuLogin());
            ps.setString(3, usuario.getUsuSenha());

            ps.executeQuery();

        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
            return false;
        }

        desconectar();
        return true;
    }

}
