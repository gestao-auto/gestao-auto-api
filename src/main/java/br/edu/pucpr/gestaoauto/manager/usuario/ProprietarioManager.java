package br.edu.pucpr.gestaoauto.manager.usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.usuario.ProprietarioDTO;
import br.edu.pucpr.gestaoauto.dao.usuario.ProprietarioDAO;
import br.edu.pucpr.gestaoauto.dao.usuario.UsuarioDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.usuario.Proprietario;
import br.edu.pucpr.gestaoauto.util.ObjetoNaoEncontradoException;

@Stateless
@LocalBean
public class ProprietarioManager implements Manager<Integer, Proprietario>{

    @Inject ProprietarioDAO dao;
    @Inject UsuarioDAO usuarioDAO;

    public Proprietario save(Proprietario proprietario) {
        return dao.save(proprietario);
    }

    @Override
    public Proprietario update(Proprietario entity) {
        return dao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(dao.getById(id));
    }

    @Override
    public Proprietario getById(Integer id) {
        return dao.getById(id);
    }

	public Proprietario getByUsuario(Integer codigoUsuario) throws ObjetoNaoEncontradoException {
		return dao.getByUsuario(codigoUsuario);
	}

	public Proprietario convertDTOToEntity(ProprietarioDTO dto) throws Exception {
        Proprietario entity = new Proprietario();
        entity.setCodigo(dto.getCodigo());
        entity.setDataNascimento((dto.getDataNascimento() != null ? LocalDate.parse(dto.getDataNascimento(), DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null));
        entity.setNome(dto.getNome());
        entity.setSobrenome(dto.getSobrenome());
        entity.setSexo(dto.getSexo());
		entity.setUsuario(usuarioDAO.getById(dto.getCodigoUsuario()));
		entity.setIdioma(new Locale(dto.getIdioma()));
        return entity;
    }

	public ProprietarioDTO convertEntityToDTO(Proprietario entity) {
		ProprietarioDTO dto = new ProprietarioDTO();
		dto.setCodigo(entity.getCodigo());
		dto.setDataNascimento(entity.getDataNascimento() != null ? entity.getDataNascimento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null);
		dto.setNome(entity.getNome());
		dto.setSobrenome(entity.getSobrenome());
		dto.setSexo(entity.getSexo());
		dto.setCodigoUsuario(entity.getUsuario().getCodigo());
		dto.setIdioma(entity.getIdioma().toString());
		return dto;
    }
}