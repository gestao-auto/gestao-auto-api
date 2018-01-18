package br.edu.pucpr.gestaoauto.dao.usuario;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.usuario.Perfil;

@Stateless
public class PerfilDAO extends DAO<Integer, Perfil> {

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Perfil getByUsuario(Integer codigoUsuario) {
		Query query = super.entityManager.createQuery("select p from Perfil p where p.usuario.codigo = :usuario");
		query.setParameter("usuario", codigoUsuario);
		return (Perfil) query.getSingleResult();
	}
}