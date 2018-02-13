package br.edu.pucpr.gestaoauto.dao.manutencao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.manutencao.PosicaoItem;

@Stateless
public class PosicaoItemDAO extends DAO<Integer, PosicaoItem> {

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<PosicaoItem> saveAll(List<PosicaoItem> posicaoItemList) {
		for (PosicaoItem posicaoItem : posicaoItemList) {
			super.save(posicaoItem);
		}
		return posicaoItemList;
	}
	
}
