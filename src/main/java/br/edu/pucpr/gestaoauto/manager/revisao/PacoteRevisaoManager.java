package br.edu.pucpr.gestaoauto.manager.revisao;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.edu.pucpr.gestaoauto.dao.revisao.PacoteRevisaoDAO;
import br.edu.pucpr.gestaoauto.model.revisao.PacoteRevisao;
import br.edu.pucpr.gestaoauto.model.veiculo.MarcaVeiculo;

@Stateless
@LocalBean
public class PacoteRevisaoManager {

	@EJB
	PacoteRevisaoDAO dao;

	public PacoteRevisao getPacoteRevisaoPorMarcaAnoVeiculo(MarcaVeiculo marca, Integer ano) {
		return dao.getPacoteRevisaoPorMarcaAnoVeiculo(marca, ano);
	}

}
