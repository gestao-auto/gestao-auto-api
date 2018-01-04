package br.edu.pucpr.gestaoauto.manager.manutencao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.manutencao.ManutencaoDTO;
import br.edu.pucpr.gestaoauto.dao.manutencao.ItemManutencaoDAO;
import br.edu.pucpr.gestaoauto.dao.manutencao.ManutencaoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.manager.revisao.PacoteRevisaoManager;
import br.edu.pucpr.gestaoauto.manager.veiculo.VeiculoManager;
import br.edu.pucpr.gestaoauto.model.manutencao.ItemManutencao;
import br.edu.pucpr.gestaoauto.model.manutencao.Manutencao;
import br.edu.pucpr.gestaoauto.model.revisao.ItemRevisao;
import br.edu.pucpr.gestaoauto.model.revisao.PacoteRevisao;
import br.edu.pucpr.gestaoauto.model.revisao.Revisao;
import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;

@Stateless
@LocalBean
public class ManutencaoManager implements Manager<Integer, Manutencao> {
	
	@EJB ManutencaoDAO manutencaoDAO;
	@EJB ItemManutencaoDAO itemManutencaoDAO;
	@Inject ReparadorManager reparadorManager;
	@Inject VeiculoManager veiculoManager;
	@Inject PacoteRevisaoManager pacoteRevisaoManager;


	@Override
	public void save(Manutencao entity) {
		manutencaoDAO.save(entity);
	}

	@Override
	public void update(Manutencao entity) {
		manutencaoDAO.update(entity);
	}

	@Override
	public void delete(Integer id) {
		manutencaoDAO.delete(this.getById(id));
	}

	@Override
	public Manutencao getById(Integer id) {
		return manutencaoDAO.getById(id);
	}

	public List<Manutencao> getListManutencaoPorVeiculo(Integer codigoVeiculo) {
		List<Manutencao> manutencaoList = manutencaoDAO.getListManutencaoPorVeiculo(codigoVeiculo);
		if (manutencaoList.isEmpty()) {
			return this.carregarPacoteRevisaoParaManutencao(codigoVeiculo);
		}
		return manutencaoList;

	}

	public List<Manutencao> carregarPacoteRevisaoParaManutencao(Integer codigoVeiculo) {
		Veiculo veiculo = veiculoManager.getById(codigoVeiculo);
		PacoteRevisao pacoteRevisao = pacoteRevisaoManager.getPacoteRevisaoPorMarcaAnoVeiculo(veiculo.getModeloVeiculo().getMarca(), veiculo.getAno());

		List<Manutencao> manutencaoList = new ArrayList<>();
		for (Revisao revisao : pacoteRevisao.getRevisaoList()) {
			Manutencao manutencao = new Manutencao();
			manutencao.setOdometroPrevisto(revisao.getOdometro());
			manutencao.setTempoUsoPrevisto(revisao.getTempouso());
			manutencao.setStatus(this.definirStatusRevisao(veiculo, revisao));
			manutencao.setVeiculo(veiculo);
			
			List<ItemManutencao> itemList = new ArrayList<>();
			for (ItemRevisao itemRevisao : revisao.getItemRevisaoList()) {
				ItemManutencao item = new ItemManutencao();
				item.setPecaServico(itemRevisao.getPecaServico());
				item.setQuantidade(item.getQuantidade());
				item.setManutencao(manutencao);
				itemList.add(item);
			}
			manutencaoDAO.save(manutencao);
			itemManutencaoDAO.saveAll(itemList);

			manutencao.setItemManutencao(itemList);
			manutencaoList.add(manutencao);
		}
		return manutencaoList;
	}

	private String definirStatusRevisao(Veiculo veiculo, Revisao revisao) {
		// TODO validar se o tempo de aquisição do veículo ou sua quilometragem é maior
		// ou igual a informada na revisão.
		return "PENDENTE";
	}

	public List<ManutencaoDTO> convertListManutencaoToDTO(List<Manutencao> listManutencaoPorVeiculo) {
		List<ManutencaoDTO> dtoList = new ArrayList<>();
		for (Manutencao manutencao : listManutencaoPorVeiculo) {
			dtoList.add(this.convertManutencaoToDTO(manutencao));
		}
		return dtoList;
	}

	public ManutencaoDTO convertManutencaoToDTO(Manutencao manutencao) {
		ManutencaoDTO dto = new ManutencaoDTO();
		dto.setCodigo(manutencao.getCodigo());
		dto.setData(manutencao.getData());
		dto.setOdometro(manutencao.getOdometro());
		dto.setStatus(manutencao.getStatus());
		dto.setReparador(manutencao.getReparador() != null ? reparadorManager.convertReparadorToDTO(manutencao.getReparador()) : null);
		dto.setItemManuteido(null); // TODO: alterar aqui para retornar a lista de itens manuteidos
		dto.setVeiculo(veiculoManager.convertVeiculoToDTO(manutencao.getVeiculo()));
		return dto;
	}
}
