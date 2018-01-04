package br.edu.pucpr.gestaoauto.dao.manutencao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.manutencao.Manutencao;

@Stateless
public class ManutencaoDAO extends DAO<Integer, Manutencao> {

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Manutencao> getListManutencaoPorVeiculo(Integer codigoVeiculo) {
		Query query = super.entityManager.createQuery("select m from Manutencao m where m.veiculo.codigo = :veiculo");
		query.setParameter("veiculo", codigoVeiculo);
		List<Manutencao> resultado = query.getResultList();
		return resultado;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Manutencao> saveAll(List<Manutencao> manutencaoList) {
		for (Manutencao manutencao : manutencaoList) {
			super.save(manutencao);
		}
		return manutencaoList;
	}
}
