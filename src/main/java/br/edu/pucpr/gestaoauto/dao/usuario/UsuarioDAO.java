package br.edu.pucpr.gestaoauto.dao.usuario;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.usuario.Usuario;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
public class UsuarioDAO extends DAO<Integer, Usuario> {

	public Usuario getByLogin(String login) {
		Usuario user;
		try {
			user = (Usuario) entityManager.createQuery(
				    "SELECT u FROM Usuario u WHERE u.email = :email")
				    .setParameter("email", login).getSingleResult();
		} catch (NoResultException  e) {
			user = null;
		}catch (Exception e) {
			throw e;
		}
		return user;
	}
}