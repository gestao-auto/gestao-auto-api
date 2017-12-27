package br.edu.pucpr.gestaoauto.manager;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.dao.UsuarioDAO;
import br.edu.pucpr.gestaoauto.model.usuario.Usuario;

@Stateless
public class UsuarioManager {
	
	@Inject
	UsuarioDAO usuarioDAO;

	public void saveUsuario(Usuario usuario) {
		usuarioDAO.save(usuario);
	}

}
