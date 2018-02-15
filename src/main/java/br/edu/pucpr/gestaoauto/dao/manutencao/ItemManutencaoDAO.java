package br.edu.pucpr.gestaoauto.dao.manutencao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.manutencao.ItemManutencao;

@Stateless
public class ItemManutencaoDAO extends DAO<Integer, ItemManutencao> {

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<ItemManutencao> saveAll(List<ItemManutencao> itemManutencaoList) {
		for (ItemManutencao itemManutencao : itemManutencaoList) {
			super.save(itemManutencao);
		}
		return itemManutencaoList;
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public void removeByManutencao(Integer manutencao) {
		String query = "SELECT i FROM ItemManutencao i INNER JOIN i.manutencao m WHERE m.codigo = :codigo";  
		List<ItemManutencao> itens = entityManager.createQuery(query).setParameter("codigo", manutencao).getResultList();  
		for (ItemManutencao m: itens) {  
			entityManager.remove(m);
		}
		
	}

}
