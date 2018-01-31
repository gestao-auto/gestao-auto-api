package br.edu.pucpr.gestaoauto.manager.indicador;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.indicador.GastoIndividualComManutencaoDTO;
import br.edu.pucpr.gestaoauto.dao.indicador.IndicadorIndividualDAO;
import br.edu.pucpr.gestaoauto.manager.manutencao.ManutencaoManager;
import br.edu.pucpr.gestaoauto.model.manutencao.Manutencao;

@Stateless
@LocalBean
public class IndicadorIndividualManager {

	@Inject	IndicadorIndividualDAO dao;
	@Inject ManutencaoManager manutencaoManager;

	public GastoIndividualComManutencaoDTO getGastosComManutencaoByVeiculo(String dataInicial, String dataFinal, Integer codigoVeiculo) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<Manutencao> manutencaoList = dao.getManutencaoByVeiculoAndPeriodo(codigoVeiculo, LocalDate.parse(dataInicial, formatter),
				LocalDate.parse(dataFinal, formatter));

		if (manutencaoList.isEmpty()) {
			return null;
		}

		Double valorTotal = new Double(0);
		Integer quilometragemPercorrida = 0;
		Integer quilometragemManutencaoAnterior = null;

		for (Manutencao manutencao : manutencaoList) {
			if (quilometragemManutencaoAnterior == null) {
				quilometragemManutencaoAnterior = this.getQuilometragemManutencaoAnterior(manutencao);
			}
			quilometragemPercorrida += manutencao.getOdometro() - quilometragemManutencaoAnterior;
			valorTotal += manutencaoManager.getValorTotal(manutencao);
			quilometragemManutencaoAnterior = manutencao.getOdometro();
		}

		GastoIndividualComManutencaoDTO dto = new GastoIndividualComManutencaoDTO();
		DecimalFormat df = new DecimalFormat("###,###.##");
		df.setRoundingMode(RoundingMode.UP);
		Integer dias = (int) ChronoUnit.DAYS.between(LocalDate.parse(dataInicial, formatter), LocalDate.parse(dataFinal, formatter));
		dto.setDias(dias);
		dto.setCustoTotal(df.format(valorTotal));
		dto.setCustoPorDia(df.format(valorTotal / dias));
		dto.setCustoPorQuilometragem(df.format(valorTotal / quilometragemPercorrida));
		dto.setItensManuteidos(dao.getItemManutencaoDTOByVeiculoAndPeriodo(codigoVeiculo, LocalDate.parse(dataInicial, formatter),
				LocalDate.parse(dataFinal, formatter)));
		dto.setRegistros(manutencaoList.size());
		return dto;
	}

	private Integer getQuilometragemManutencaoAnterior(Manutencao manutencao) {
		Manutencao manutencaoAnterior = dao.getManutencaoAnterior(manutencao);
		if (manutencaoAnterior != null) {
			return manutencaoAnterior.getOdometro();
		}
		return 0;
	}
}