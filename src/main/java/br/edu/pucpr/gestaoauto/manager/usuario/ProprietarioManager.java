package br.edu.pucpr.gestaoauto.manager.usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.edu.pucpr.gestaoauto.api.dto.usuario.ProprietarioDTO;
import br.edu.pucpr.gestaoauto.dao.usuario.ProprietarioDAO;
import br.edu.pucpr.gestaoauto.dao.usuario.UsuarioDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.usuario.Proprietario;

@Stateless
@LocalBean
public class ProprietarioManager implements Manager<Integer, Proprietario>{
    @EJB
    ProprietarioDAO dao;
    @EJB UsuarioDAO usuarioDAO;

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

	public Proprietario getByUsuario(Integer codigoUsuario) {
		return dao.getByUsuario(codigoUsuario);
	}

	public Proprietario convertDTOToEntity(ProprietarioDTO dto) throws Exception {
        Proprietario entity = new Proprietario();
        entity.setCodigo(dto.getCodigo());
        entity.setDataNascimento((dto.getDataNascimento() != null ? LocalDate.parse(dto.getDataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null));
        entity.setNome(dto.getNome());
        entity.setSobrenome(dto.getSobrenome());
        entity.setSexo(dto.getSexo());
		entity.setUsuario(usuarioDAO.getById(dto.getCodigoUsuario()));
        return entity;
    }

    public ProprietarioDTO convertEntityToDTO(Proprietario entity){
		ProprietarioDTO dto = new ProprietarioDTO();
		dto.setCodigo(entity.getCodigo());
		dto.setDataNascimento(entity.getDataNascimento() != null ? entity.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null);
		dto.setNome(entity.getNome());
		dto.setSobrenome(entity.getSobrenome());
		dto.setSexo(entity.getSexo());
		dto.setCodigoUsuario(entity.getUsuario().getCodigo());
		return dto;
    }
}