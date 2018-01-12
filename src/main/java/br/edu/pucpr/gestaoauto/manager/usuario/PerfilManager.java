package br.edu.pucpr.gestaoauto.manager.usuario;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.edu.pucpr.gestaoauto.dao.usuario.PerfilDAO;
import br.edu.pucpr.gestaoauto.dao.usuario.PermissaoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.usuario.Perfil;
import br.edu.pucpr.gestaoauto.model.usuario.Permissao;
import br.edu.pucpr.gestaoauto.model.usuario.Usuario;

@Stateless
@LocalBean
public class PerfilManager implements Manager<Integer, Perfil> {

	@EJB PerfilDAO perfilDAO;
	@EJB PermissaoDAO permissaoDAO;

	@Override
	public Perfil save(Perfil entity) throws Exception {
		return perfilDAO.save(entity);
	}

	@Override
	public Perfil update(Perfil entity) throws Exception {
		return perfilDAO.update(entity);
	}

	@Override
	public void delete(Integer id) throws Exception {
		perfilDAO.delete(this.getById(id));
	}

	@Override
	public Perfil getById(Integer id) throws Exception {
		return perfilDAO.getById(id);
	}

	public void createPerfilUsuarioFinal(Usuario usuario) {
		Perfil perfil = new Perfil();
		perfil.setUsuario(usuario);
		perfil.setPermissao(permissaoDAO.getById(Permissao.USUARIO_FINAL));
		perfilDAO.save(perfil);
	}

	public void createPerfilUsuarioAdministrador(Usuario usuario) {
		Perfil perfil = new Perfil();
		perfil.setUsuario(usuario);
		perfil.setPermissao(permissaoDAO.getById(Permissao.USUARIO_ADMINISTRADOR));
		perfilDAO.save(perfil);
	}

}
