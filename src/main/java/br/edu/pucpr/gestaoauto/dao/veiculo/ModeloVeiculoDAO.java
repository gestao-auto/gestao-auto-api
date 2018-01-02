package br.edu.pucpr.gestaoauto.dao.veiculo;

import java.util.List;
import javax.ejb.Stateless;
import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.veiculo.MarcaVeiculo;
import br.edu.pucpr.gestaoauto.model.veiculo.ModeloVeiculo;

@Stateless
public class ModeloVeiculoDAO extends DAO<Integer, ModeloVeiculo> {

	public List<ModeloVeiculo> getModeloVeiculoPorMarca(MarcaVeiculo marca) {
		// TODO query
		return null;
	}

}
