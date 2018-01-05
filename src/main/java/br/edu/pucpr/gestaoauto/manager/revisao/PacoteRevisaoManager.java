package br.edu.pucpr.gestaoauto.manager.revisao;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.edu.pucpr.gestaoauto.dao.revisao.PacoteRevisaoDAO;
import br.edu.pucpr.gestaoauto.model.revisao.PacoteRevisao;
import br.edu.pucpr.gestaoauto.model.veiculo.Modelo;

@Stateless
@LocalBean
public class PacoteRevisaoManager {

	@EJB
	PacoteRevisaoDAO dao;

	public PacoteRevisao getPacoteRevisaoPorModeloVeiculo(Modelo modelo) {
		return dao.getPacoteRevisaoPorModeloVeiculo(modelo);
	}

}
