package br.edu.pucpr.gestaoauto.dao.revisao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.revisao.PacoteRevisao;
import br.edu.pucpr.gestaoauto.model.veiculo.Modelo;

@Stateless
public class PacoteRevisaoDAO extends DAO<Integer, PacoteRevisao> {

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public PacoteRevisao getPacoteRevisaoPorModeloVeiculo(Modelo modelo) {
		Query query = super.entityManager.createQuery("select p from PacoteRevisao p where p.modeloVeiculo.codigo = :modeloVeiculo");
		query.setParameter("modeloVeiculo", modelo.getCodigo());
		try {
			return (PacoteRevisao) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
