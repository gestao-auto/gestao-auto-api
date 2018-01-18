package br.edu.pucpr.gestaoauto.dao.indicador;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.edu.pucpr.gestaoauto.api.dto.indicador.GastoIndividualComManutencaoDTO;

@Stateless
public class IndicadorIndividualDAO {

	@PersistenceContext(name = "gestaoAuto")
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<GastoIndividualComManutencaoDTO> getGastoIndividualComManutencaoByVeiculo(Integer codigoVeiculo, 
			LocalDate dataInicial, LocalDate dataFinal) {
		Query query = entityManager.createQuery(
				  "		select "
				+ "			new br.edu.pucpr.gestaoauto.api.dto.indicador.GastoIndividualComManutencaoDTO(man) "
				+ "		from Manutencao man "
				+ "		inner join man.veiculo vei "
				+ "		where vei.codigo = :veiculo "
				+ "			  and man.data between :dataInicial and :dataFinal "
				+ "		order by man.data desc");

		query.setParameter("veiculo", codigoVeiculo);
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);

		return query.getResultList();
	}
}
