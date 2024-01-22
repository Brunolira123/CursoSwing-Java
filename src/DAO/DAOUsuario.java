package DAO;

import model.ModelUsuario;
import util.ConexaoPostgreSQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    /**
     * exclui usuario pelo código, exclui direto no banco! sem exclusão lógica
     *
     * @param codigoUsuario
     * @return
     */
    public boolean excluirUsuarioDAO(int codigoUsuario) {
        conectar();
        PreparedStatement ps;
        String sql = "DELETE FROM tbl_usuario WHERE pk_usu_id = '" + codigoUsuario + "'";
        ps = this.criarPreparedStatement(sql);

        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        this.desconectar();
        return true;
    }

    public ModelUsuario getUsuario(int codigoUsuario) {
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
                + "FROM tbl_usuario "
                + "WHERE pk_usu_id = '" + codigoUsuario + "'";
        ps = criarPreparedStatement(sql);
        try {
            rs = ps.executeQuery();

            while (rs.next()) {
                usuario = new ModelUsuario();
                usuario.setId(rs.getInt("pk_usu_id"));
                usuario.setUsuNome(rs.getString("usu_nome"));
                usuario.setUsuLogin(rs.getString("usu_login"));
                usuario.setUsuSenha(rs.getString("usu_senha"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
        return usuario;
    }
}
