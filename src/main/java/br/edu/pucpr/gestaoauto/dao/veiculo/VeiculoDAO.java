package br.edu.pucpr.gestaoauto.dao.veiculo;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

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