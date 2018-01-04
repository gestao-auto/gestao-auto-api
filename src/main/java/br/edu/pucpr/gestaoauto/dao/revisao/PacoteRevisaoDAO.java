package br.edu.pucpr.gestaoauto.dao.revisao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.revisao.PacoteRevisao;
import br.edu.pucpr.gestaoauto.model.veiculo.Marca;

@Stateless
public class PacoteRevisaoDAO extends DAO<Integer, PacoteRevisao> {

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public PacoteRevisao getPacoteRevisaoPorMarcaAnoVeiculo(Marca marca, Integer ano) {
		Query query = super.entityManager.createQuery("select p from PacoteRevisao p where p.marca.codigo = :marca and p.ano = :ano");
		query.setParameter("marca", marca.getCodigo());
		query.setParameter("ano", ano);
		return (PacoteRevisao) query.getSingleResult();
	}
}
