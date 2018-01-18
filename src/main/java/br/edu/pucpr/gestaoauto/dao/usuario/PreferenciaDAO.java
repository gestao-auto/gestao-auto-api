package br.edu.pucpr.gestaoauto.dao.usuario;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.usuario.Preferencia;

@Stateless
@LocalBean
public class PreferenciaDAO extends DAO<Integer, Preferencia> {

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Preferencia getByUsuario(Integer codigoUsuario) {
		Query query = super.entityManager.createQuery("select pu from Preferencia pu where pu.usuario.codigo = :usuario");
		query.setParameter("usuario", codigoUsuario);
		return (Preferencia) query.getSingleResult();
	}
}