package br.edu.pucpr.gestaoauto.manager.usuario;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.usuario.PreferenciaUsuarioDTO;
import br.edu.pucpr.gestaoauto.dao.usuario.PreferenciaUsuarioDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.usuario.PreferenciaUsuario;

@Stateless
@LocalBean
public class PreferenciaUsuarioManager implements Manager<Integer, PreferenciaUsuario> {

	@EJB PreferenciaUsuarioDAO dao;
	@Inject
	UsuarioManager usuarioManager;

	@Override
	public PreferenciaUsuario save(PreferenciaUsuario entity) throws Exception {
		return dao.save(entity);
	}

	@Override
	public PreferenciaUsuario update(PreferenciaUsuario entity) throws Exception {
		return dao.update(entity);
	}

	@Override
	public void delete(Integer id) throws Exception {
		dao.delete(this.getById(id));
	}

	@Override
	public PreferenciaUsuario getById(Integer id) throws Exception {
		return dao.getById(id);
	}

	public PreferenciaUsuarioDTO convertPreferenciaUsuarioToDTO(PreferenciaUsuario preferenciaUsuario) {
		PreferenciaUsuarioDTO dto = new PreferenciaUsuarioDTO();
		dto.setCodigo(preferenciaUsuario.getCodigo());
		dto.setCodigoUsuario(preferenciaUsuario.getUsuario().getCodigo());
		dto.setHorarioNotificacao(preferenciaUsuario.getHorarioNoficacao().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		dto.setDiasAntecedenciaNotificacaoRevisao(preferenciaUsuario.getDiasAntecedenciaNotificacaoRevisao());
		dto.setHodometroAntecedenciaNotificacaoRevisao(preferenciaUsuario.getHodometroAntecedenciaNotificacaoRevisao());
		return dto;
	}

	public PreferenciaUsuario convertPreferenciaUsuarioDTOToEntity(PreferenciaUsuarioDTO dto) throws Exception {
		PreferenciaUsuario preferenciaUsuario = (dto.getCodigo() != null ? this.getById(dto.getCodigo()) : new PreferenciaUsuario());
		preferenciaUsuario.setUsuario(usuarioManager.getById(dto.getCodigoUsuario()));
		preferenciaUsuario.setHorarioNoficacao(LocalTime.parse(dto.getHorarioNotificacao(), DateTimeFormatter.ofPattern("HH:mm:ss")));
		preferenciaUsuario.setDiasAntecedenciaNotificacaoRevisao(dto.getDiasAntecedenciaNotificacaoRevisao());
		preferenciaUsuario.setHodometroAntecedenciaNotificacaoRevisao(dto.getHodometroAntecedenciaNotificacaoRevisao());
		return preferenciaUsuario;
	}
}
