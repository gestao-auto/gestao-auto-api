package br.edu.pucpr.gestaoauto.manager;

import br.edu.pucpr.gestaoauto.api.dto.manutencao.ManutencaoDTO;
import br.edu.pucpr.gestaoauto.api.dto.pessoaJuridica.PessoaJuridicaDTO;
import br.edu.pucpr.gestaoauto.dao.pessoaJuridica.PessoaJuridicaDAO;
import br.edu.pucpr.gestaoauto.model.pessoaJuridica.PessoaJuridica;
import br.edu.pucpr.gestaoauto.model.pessoaJuridica.Reparador;
import br.edu.pucpr.gestaoauto.model.pessoaJuridica.Seguradora;
import br.edu.pucpr.gestaoauto.util.ObjetoNaoEncontradoException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class PessoaJuridicaManager implements Manager<Integer, PessoaJuridica> {

	@Inject	PessoaJuridicaDAO pessoaJuridicaDAO;

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

	public Reparador carregaReparador(ManutencaoDTO dto) throws ObjetoNaoEncontradoException {
		Reparador reparador;
		if(dto.getCodigoReparador() == null && dto.getNomeReparador() == null){
		    throw new ObjetoNaoEncontradoException("error.pessoa.juridica.naoEncontrado",
                    new Object[] { dto.getCodigoReparador(), dto.getNomeReparador() });
        }
        else if(dto.getCodigoReparador() == null && dto.getNomeReparador() != null) {
            List<Reparador> reparadores = pessoaJuridicaDAO.getReparadorasByNome(dto.getNomeReparador());
            if(reparadores.isEmpty()) {
                reparador = new Reparador();
                reparador.setNomeFantasia(dto.getNomeReparador());
                this.save(reparador);
            } else reparador = reparadores.get(0);
		}
		else {
			reparador = (Reparador) this.getById(dto.getCodigoReparador());
		}
		return reparador;
	}

    public Seguradora carregaSeguradora(ManutencaoDTO dto) throws ObjetoNaoEncontradoException {
        Seguradora seguradora;
        if(dto.getCodigoSeguradora() == null && dto.getNomeSeguradora() == null){
            throw new ObjetoNaoEncontradoException("error.pessoa.juridica.naoEncontrado",
                    new Object[] { dto.getCodigoSeguradora(), dto.getNomeSeguradora() });
        }
        else if(dto.getCodigoSeguradora() == null && dto.getNomeSeguradora() != null) {
            List<Seguradora> seguradoras = pessoaJuridicaDAO.getSeguradorasByNome(dto.getNomeSeguradora());
            if(seguradoras.isEmpty()) {
                seguradora = new Seguradora();
                seguradora.setNomeFantasia(dto.getNomeSeguradora());
                this.save(seguradora);
            }
            else seguradora = seguradoras.get(0);
        }
        else {
            seguradora = (Seguradora) this.getById(dto.getCodigoSeguradora());
        }
        return seguradora;
    }
}