package br.edu.pucpr.gestaoauto.manager.home;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.home.HomeManutencaoDTO;
import br.edu.pucpr.gestaoauto.manager.manutencao.ManutencaoManager;
import br.edu.pucpr.gestaoauto.model.manutencao.Revisao;

@Stateless
@LocalBean
public class HomeManager {

	@Inject
	ManutencaoManager manutencaoManager;

	public HomeManutencaoDTO getProximaRevisaoByVeiculo(Integer codigoVeiculo) {
		List<Revisao> revisao = manutencaoManager.getProximaRevisaoByVeiculo(codigoVeiculo);
		if (revisao.isEmpty()) {
			return null;
		}
		return this.convertEntityToDTO(revisao.get(0));
	}

	private HomeManutencaoDTO convertEntityToDTO(Revisao revisao) {
		HomeManutencaoDTO dto = new HomeManutencaoDTO();
		dto.setCodigo(revisao.getCodigo());
		dto.setCodigoVeiculo(revisao.getVeiculo().getCodigo());
		dto.setDescricao(revisao.getDescricao());
		dto.setTempoUsoPrevisto(revisao.getModeloRevisao().getTempoUso());
		dto.setQuilometragemPrevista(revisao.getOdometroPrevisto());
		dto.setDiasRestantes((int) ChronoUnit.DAYS.between(LocalDate.now(), revisao.getDataPrevista()));
		dto.setQuilometrosRestantes(revisao.getOdometroPrevisto() - revisao.getVeiculo().getOdometro());
		dto.setStatus(revisao.getStatus().getNome());
		dto.setValorMedio(null);
		return dto;
	}
}
