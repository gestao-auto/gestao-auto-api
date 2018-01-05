package br.edu.pucpr.gestaoauto.manager.manutencao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.ObjectNotFoundException;
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
import br.edu.pucpr.gestaoauto.model.manutencao.Revisao;
import br.edu.pucpr.gestaoauto.model.manutencao.Status;
import br.edu.pucpr.gestaoauto.model.revisao.ItemRevisao;
import br.edu.pucpr.gestaoauto.model.revisao.ModeloRevisao;
import br.edu.pucpr.gestaoauto.model.revisao.PacoteRevisao;
import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;

@Stateless
@LocalBean
public class ManutencaoManager implements Manager<Integer, Manutencao> {
	
	@EJB ManutencaoDAO manutencaoDAO;
	@EJB ItemManutencaoDAO itemManutencaoDAO;
	@Inject VeiculoManager veiculoManager;
	@Inject PacoteRevisaoManager pacoteRevisaoManager;

	public Manutencao save(Manutencao entity) {
		return manutencaoDAO.save(entity);
	}

	@Override
	public Manutencao update(Manutencao entity) {
		return manutencaoDAO.update(entity);
	}

	@Override
	public void delete(Integer id) {
		manutencaoDAO.delete(this.getById(id));
	}

	@Override
	public Manutencao getById(Integer id) {
		return manutencaoDAO.getById(id);
	}

	public List<Manutencao> getListManutencaoPorVeiculo(Integer codigoVeiculo) throws ObjectNotFoundException {
		List<Manutencao> manutencaoList = manutencaoDAO.getListManutencaoPorVeiculo(codigoVeiculo);
		if (manutencaoList.isEmpty()) {
			return this.carregarPacoteRevisaoParaManutencao(codigoVeiculo);
		}
		return manutencaoList;

	}

	public List<Manutencao> carregarPacoteRevisaoParaManutencao(Integer codigoVeiculo) throws ObjectNotFoundException {
		Veiculo veiculo = veiculoManager.getById(codigoVeiculo);
		PacoteRevisao pacoteRevisao = pacoteRevisaoManager.getPacoteRevisaoPorModeloVeiculo(veiculo.getModelo());

		if (pacoteRevisao == null) {
			throw new ObjectNotFoundException("Programação de revisão não encontrada para o veículo marca: " 
					+ veiculo.getModelo().getMarca() + " ano: " + veiculo.getAno());
		}

		List<Manutencao> manutencaoList = new ArrayList<>();
		for (ModeloRevisao modelo : pacoteRevisao.getModeloRevisaoList()) {
			Revisao manutencaoProgramada = new Revisao();
			manutencaoProgramada.setDescricao(modelo.getDescricao());
			manutencaoProgramada.setVeiculo(veiculo);
			manutencaoProgramada.setDataPrevista(this.getCalcularDataPrevistaRevisao(veiculo, modelo));
			manutencaoProgramada.setStatus(this.getStatusRevisaoPorTempoDeUsoOuOdometroPrevisto(veiculo, modelo));

			List<ItemManutencao> itemManutencaoList = new ArrayList<>();
			for (ItemRevisao itemRevisao : modelo.getItemRevisaoList()) {
				ItemManutencao item = new ItemManutencao();
				item.setPecaServico(itemRevisao.getPecaServico());
				item.setQuantidade(item.getQuantidade());
				item.setManutencao(manutencaoProgramada);
				itemManutencaoList.add(item);
			}

			manutencaoDAO.save(manutencaoProgramada);
			itemManutencaoDAO.saveAll(itemManutencaoList);

			manutencaoProgramada.setItemManutencao(itemManutencaoList);
			manutencaoList.add(manutencaoProgramada);
		}
		return manutencaoList;
	}

	private Date getCalcularDataPrevistaRevisao(Veiculo veiculo, ModeloRevisao modelo) {
		// TODO Auto-generated method stub
		return new Date();
	}

	private Status getStatusRevisaoPorTempoDeUsoOuOdometroPrevisto(Veiculo veiculo, ModeloRevisao revisao) {
		// TODO validar se o tempo de aquisição do veículo ou sua quilometragem é maior
		// ou igual a informada na revisão.
		return Status.PENDENTE;
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
		dto.setReparador(null);
		dto.setItemManuteido(null); // TODO: alterar aqui para retornar a lista de itens manuteidos
		dto.setVeiculo(veiculoManager.convertVeiculoToDTO(manutencao.getVeiculo()));
		return dto;
	}
}
