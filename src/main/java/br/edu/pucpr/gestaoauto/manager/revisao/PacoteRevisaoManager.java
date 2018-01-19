package br.edu.pucpr.gestaoauto.manager.revisao;

import br.edu.pucpr.gestaoauto.dao.revisao.PacoteRevisaoDAO;
import br.edu.pucpr.gestaoauto.model.revisao.PacoteRevisao;
import br.edu.pucpr.gestaoauto.model.veiculo.Modelo;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@LocalBean
public class PacoteRevisaoManager {
	@Inject	PacoteRevisaoDAO dao;

	public PacoteRevisao getPacoteRevisaoPorModeloVeiculo(Modelo modelo) {
		return dao.getPacoteRevisaoPorModeloVeiculo(modelo);
	}
}