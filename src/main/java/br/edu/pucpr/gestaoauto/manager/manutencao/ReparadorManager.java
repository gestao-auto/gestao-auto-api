package br.edu.pucpr.gestaoauto.manager.manutencao;

import br.edu.pucpr.gestaoauto.api.dto.manutencao.ReparadorDTO;
import br.edu.pucpr.gestaoauto.model.manutencao.Reparador;

public class ReparadorManager {

	public ReparadorDTO convertReparadorToDTO(Reparador reparador) {
		ReparadorDTO dto = new ReparadorDTO();
		dto.setCodigo(reparador.getCodigo());
		dto.setCnpj(reparador.getCnpj());
		dto.setRazaoSocial(reparador.getRazaoSocial());
		dto.setNomeFantasia(reparador.getNomeFantasia());
		return dto;
	}

}
