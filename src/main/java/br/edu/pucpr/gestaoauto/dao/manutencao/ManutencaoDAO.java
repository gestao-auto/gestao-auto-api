package br.edu.pucpr.gestaoauto.dao.manutencao;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.manutencao.Manutencao;
import br.edu.pucpr.gestaoauto.model.manutencao.Revisao;
import br.edu.pucpr.gestaoauto.model.manutencao.Status;

@SuppressWarnings("unchecked")
@Stateless
public class ManutencaoDAO extends DAO<Integer, Manutencao> {


	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Manutencao> getListManutencaoByVeiculo(Integer codigoVeiculo) {
		Query query = super.entityManager.createQuery("select m from Manutencao m where m.veiculo.codigo = :veiculo");
		query.setParameter("veiculo", codigoVeiculo);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Manutencao> saveAll(List<Manutencao> manutencaoList) {
		for (Manutencao manutencao : manutencaoList) {
			super.save(manutencao);
		}
		return manutencaoList;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Revisao> getProximaRevisaoByVeiculo(Integer codigoVeiculo) {
		return super.entityManager.createQuery(
		        "select m from Revisao m where m.veiculo.codigo = :veiculo and m.status = :status")
                .setParameter("veiculo", codigoVeiculo)
                .setParameter("status", Status.PENDENTE)
                .setMaxResults(1)
                .getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Revisao> revisaoPendenteByData(LocalDate data) {
        return super.entityManager
                .createQuery("select m from Revisao m " +
                        "where m.dataPrevista = :data" +
                        " and m.status = :status")
                .setParameter("data", data)
                .setParameter("status", Status.PENDENTE)
				.getResultList();
	}
}
