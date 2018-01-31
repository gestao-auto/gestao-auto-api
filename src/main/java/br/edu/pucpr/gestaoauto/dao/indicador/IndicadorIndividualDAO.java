package br.edu.pucpr.gestaoauto.dao.indicador;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.edu.pucpr.gestaoauto.api.dto.indicador.ItemManutencaoDTO;
import br.edu.pucpr.gestaoauto.model.manutencao.Manutencao;
import br.edu.pucpr.gestaoauto.model.manutencao.Status;

@Stateless
public class IndicadorIndividualDAO {

	@PersistenceContext(name = "gestaoAuto")
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Manutencao> getManutencaoByVeiculoAndPeriodo(Integer codigoVeiculo, LocalDate dataInicial, LocalDate dataFinal) {
		Query query = entityManager.createQuery("select man "
				+ "		from Manutencao man "
				+ "		inner join man.veiculo vei "
				+ "		where vei.codigo = :veiculo "
				+ "			  and man.data between :dataInicial and :dataFinal "
				+ "			  and man.status = :status "
				+ "		order by man.data asc");
		query.setParameter("veiculo", codigoVeiculo);
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		query.setParameter("status", Status.REALIZADA);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<ItemManutencaoDTO> getItemManutencaoDTOByVeiculoAndPeriodo(Integer codigoVeiculo, LocalDate dataInicial, LocalDate dataFinal) {
		Query query = entityManager.createQuery(
				"  select new br.edu.pucpr.gestaoauto.api.dto.indicador.ItemManutencaoDTO(ps.nome, sum(itm.quantidade)) "
				+ " from ItemManutencao itm "
				+ " inner join itm.manutencao man "
				+ " inner join itm.pecaServico ps "
				+ " inner join man.veiculo vei "
				+ " where man.status = :status "
				+ "		  and man.data between :dataInicial and :dataFinal "
				+ "		  and vei.codigo = :veiculo "
				+ " group by ps.nome");
	
		query.setParameter("veiculo", codigoVeiculo);
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		query.setParameter("status", Status.REALIZADA);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Manutencao getManutencaoAnterior(Manutencao manutencao) {
		Query query = entityManager.createQuery("select man "
				+ " from Manutencao man "
				+ " where man.status = :status "
				+ "		  and man.data < :data"
				+ "		  and man.veiculo.codigo = :veiculo "
				+ " order by man.data desc ");
	
		query.setParameter("veiculo", manutencao.getVeiculo().getCodigo());
		query.setParameter("data", manutencao.getData());
		query.setParameter("status", Status.REALIZADA);

		List<Manutencao> result = query.getResultList();
		if (result.isEmpty()) {
			return null;
		}
		return (Manutencao) result.get(0);
	}
}
