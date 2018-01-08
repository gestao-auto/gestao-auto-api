package br.edu.pucpr.gestaoauto.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.edu.pucpr.gestaoauto.api.dto.pessoaJuridica.PessoaJuridicaDTO;
import br.edu.pucpr.gestaoauto.dao.pessoaJuridica.PessoaJuridicaDAO;
import br.edu.pucpr.gestaoauto.model.pessoaJuridica.PessoaJuridica;

@Stateless
@LocalBean
public class PessoaJuridicaManager implements Manager<Integer, PessoaJuridica> {

	@EJB
	PessoaJuridicaDAO pessoaJuridicaDAO;

	@Override
	public PessoaJuridica save(PessoaJuridica entity) {
        return pessoaJuridicaDAO.save(entity);
	}

	@Override
	public PessoaJuridica update(PessoaJuridica entity) {
        return pessoaJuridicaDAO.update(entity);
	}

	@Override
	public void delete(Integer id) {
		pessoaJuridicaDAO.delete(this.getById(id));
	}

	@Override
	public PessoaJuridica getById(Integer id) {
		return pessoaJuridicaDAO.getById(id);
	}

	public List<PessoaJuridica> getListPessoaJuridica() {
		return pessoaJuridicaDAO.findAll();
	}

	public List<PessoaJuridicaDTO> convertListToDTO(List<PessoaJuridica> pessoaJuridicas) {
		List<PessoaJuridicaDTO> dtoList = new ArrayList<>();
		for (PessoaJuridica pessoaJuridica : pessoaJuridicas) {
			dtoList.add(this.convertPessoaJuridicaToDTO(pessoaJuridica));
		}
		return dtoList;
	}

	public PessoaJuridicaDTO convertPessoaJuridicaToDTO(PessoaJuridica pessoaJuridica) {
		PessoaJuridicaDTO pessoaJuridicaDTO = new PessoaJuridicaDTO();
		pessoaJuridicaDTO.setCodigo(pessoaJuridica.getCodigo());
		pessoaJuridicaDTO.setCnpj(pessoaJuridica.getCnpj());
		pessoaJuridicaDTO.setNomefantasia(pessoaJuridica.getNomeFantasia());
		pessoaJuridicaDTO.setRazaosocial(pessoaJuridica.getRazaoSocial());
		return pessoaJuridicaDTO;
	}
}