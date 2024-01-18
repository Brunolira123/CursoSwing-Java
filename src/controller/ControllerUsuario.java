package controller;

import DAO.DAOUsuario;
import model.ModelUsuario;

/**
 *
 * @author Bruno
 */
public class ControllerUsuario {

    DAOUsuario dao = new DAOUsuario();

    public boolean salvarUsuarioController(ModelUsuario usuario) {
        return this.dao.salvarUsuarioDAO(usuario);
    }

}
