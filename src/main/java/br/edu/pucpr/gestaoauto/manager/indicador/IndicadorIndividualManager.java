package br.edu.pucpr.gestaoauto.manager.indicador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.indicador.GastoIndividualComManutencaoDTO;
import br.edu.pucpr.gestaoauto.api.dto.indicador.ItemManutencaoDTO;
import br.edu.pucpr.gestaoauto.dao.indicador.IndicadorIndividualDAO;
import br.edu.pucpr.gestaoauto.manager.manutencao.ManutencaoManager;
import br.edu.pucpr.gestaoauto.manager.veiculo.VeiculoManager;
import br.edu.pucpr.gestaoauto.model.manutencao.Manutencao;
import br.edu.pucpr.gestaoauto.util.GestaoAutoException;
import br.edu.pucpr.gestaoauto.util.MoedaUtil;

@Stateless
@LocalBean
public class IndicadorIndividualManager {

	@Inject	IndicadorIndividualDAO dao;
	@Inject ManutencaoManager manutencaoManager;
	@Inject VeiculoManager veiculoManager;

	public GastoIndividualComManutencaoDTO getGastosComManutencaoByVeiculo(String dataInicial, String dataFinal, Integer codigoVeiculo)
			throws GestaoAutoException {
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
		Locale locale = veiculoManager.getById(codigoVeiculo).getProprietario().getIdioma();
		Integer dias = (int) ChronoUnit.DAYS.between(LocalDate.parse(dataInicial, formatter), LocalDate.parse(dataFinal, formatter));
		dto.setDias(dias);
		dto.setCustoTotal(MoedaUtil.getValorTexto(valorTotal, locale));
		dto.setCustoPorDia(MoedaUtil.getValorTexto((valorTotal / dias), locale));
		dto.setCustoPorQuilometragem(MoedaUtil.getValorTexto((valorTotal / quilometragemPercorrida), locale));
		dto.setRegistros(manutencaoList.size());
		this.getItensManutencaoParaGrafico(LocalDate.parse(dataInicial, formatter), LocalDate.parse(dataFinal, formatter), codigoVeiculo, dto);
		return dto;
	}

	private void getItensManutencaoParaGrafico(LocalDate dataInicial, LocalDate dataFinal, Integer codigoVeiculo,
			GastoIndividualComManutencaoDTO dto) {
		List<ItemManutencaoDTO> lista = dao.getItemManutencaoDTOByVeiculoAndPeriodo(codigoVeiculo, dataInicial, dataFinal);
		String[] legenda = new String[lista.size()];
		Double[] quantidade = new Double[lista.size()];
		
		for (int i = 0; i < lista.size(); i++) {
			ItemManutencaoDTO item = lista.get(i);
			legenda[i] = item.getNome();
			quantidade[i] = item.getQuantidade();
		}
		dto.setNomeItensManuteidos(legenda);
		dto.setQuantidadeItensManuteidos(quantidade);
	}

	private Integer getQuilometragemManutencaoAnterior(Manutencao manutencao) {
		Manutencao manutencaoAnterior = dao.getManutencaoAnterior(manutencao);
		if (manutencaoAnterior != null) {
			return manutencaoAnterior.getOdometro();
		}
		return 0;
	}
}