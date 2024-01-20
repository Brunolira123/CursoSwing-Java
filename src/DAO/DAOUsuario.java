package DAO;

import model.ModelUsuario;
import util.ConexaoPostgreSQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.getMessage();
            ex.printStackTrace();
            return false;
        }

        desconectar();
        return true;
    }

    public List<ModelUsuario> listaUsuarioDAO() {
        List<ModelUsuario> list = new ArrayList<>();
        ModelUsuario usuario = new ModelUsuario();
        conectar();

        ResultSet rs = null;
        PreparedStatement ps = null;

        String sql
                = "SELECT "
                + "pk_usu_id, "
                + "usu_nome ,"
                + "usu_login, "
                + "usu_senha "
                + "FROM tbl_usuario";

        try {
            ps = criarPreparedStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                usuario = new ModelUsuario();
                usuario.setId(rs.getInt("pk_usu_id"));
                usuario.setUsuNome(rs.getString("usu_nome"));
                usuario.setUsuLogin(rs.getString("usu_login"));
                usuario.setUsuSenha(rs.getString("usu_senha"));

                list.add(usuario);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        desconectar();
        return list;
    }
}
