package br.edu.pucpr.gestaoauto.dao.usuario;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.usuario.Proprietario;

@Stateless
public class ProprietarioDAO extends DAO<Integer, Proprietario> {

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Proprietario getByUsuario(Integer codigoUsuario) {
		Query query = super.entityManager.createQuery("select p from Proprietario p where p.usuario.codigo = :usuario");
		query.setParameter("usuario", codigoUsuario);
		return (Proprietario) query.getSingleResult();
	}

}
