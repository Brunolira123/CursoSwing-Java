package controller;

import DAO.DAOUsuario;
import java.util.List;
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

    //acessa o dao e retorna a lista de usuarios
    public List<ModelUsuario> getListaUsuariosController() {
       return this.dao.listaUsuarioDAO();
    }

}
