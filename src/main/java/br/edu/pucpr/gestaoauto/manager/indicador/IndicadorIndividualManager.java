package br.edu.pucpr.gestaoauto.manager.indicador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.edu.pucpr.gestaoauto.api.dto.indicador.GastoIndividualComManutencaoDTO;
import br.edu.pucpr.gestaoauto.dao.indicador.IndicadorIndividualDAO;

@Stateless
@LocalBean
public class IndicadorIndividualManager {

	@EJB
	IndicadorIndividualDAO dao;

	public List<GastoIndividualComManutencaoDTO> getGastosComManutencaoByVeiculo(String dataInicial, String dataFinal, Integer codigoVeiculo) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return dao.getGastoIndividualComManutencaoByVeiculo(codigoVeiculo, LocalDate.parse(dataInicial, formatter),
				LocalDate.parse(dataFinal, formatter));
	}
}
