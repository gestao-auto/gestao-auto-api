package br.edu.pucpr.gestaoauto.dao.veiculo;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;

@Stateless
public class VeiculoDAO extends DAO<Integer, Veiculo> {

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Veiculo> getListByUsuario(Integer codigoUsuario) {
		Query query = super.entityManager
				.createQuery("select v from Veiculo v where v.proprietario.usuario.codigo = :usuario");
		query.setParameter("usuario", codigoUsuario);
		return query.getResultList();
	}
}