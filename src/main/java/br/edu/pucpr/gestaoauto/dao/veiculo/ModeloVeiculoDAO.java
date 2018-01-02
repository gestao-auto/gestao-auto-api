package br.edu.pucpr.gestaoauto.dao.veiculo;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.veiculo.MarcaVeiculo;
import br.edu.pucpr.gestaoauto.model.veiculo.ModeloVeiculo;

@Stateless
public class ModeloVeiculoDAO extends DAO<Integer, ModeloVeiculo> {

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<ModeloVeiculo> getModeloVeiculoPorMarca(MarcaVeiculo marca) {
		Query query = super.entityManager.createQuery("select mv from ModeloVeiculo mv where mv.marca.codigo = :marca");
		query.setParameter("marca", marca.getCodigo());
		return query.getResultList();
	}

}
