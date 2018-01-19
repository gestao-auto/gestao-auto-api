package br.edu.pucpr.gestaoauto.manager.indicador;

import br.edu.pucpr.gestaoauto.api.dto.indicador.GastoIndividualComManutencaoDTO;
import br.edu.pucpr.gestaoauto.dao.indicador.IndicadorIndividualDAO;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Stateless
@LocalBean
public class IndicadorIndividualManager {

	@Inject	IndicadorIndividualDAO dao;

	public List<GastoIndividualComManutencaoDTO> getGastosComManutencaoByVeiculo(String dataInicial, String dataFinal, Integer codigoVeiculo) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return dao.getGastoIndividualComManutencaoByVeiculo(codigoVeiculo, LocalDate.parse(dataInicial, formatter),
				LocalDate.parse(dataFinal, formatter));
	}
}
