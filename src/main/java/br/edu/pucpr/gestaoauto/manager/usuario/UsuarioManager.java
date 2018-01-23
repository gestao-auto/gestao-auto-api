package br.edu.pucpr.gestaoauto.manager.usuario;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.usuario.UsuarioCompletoDTO;
import br.edu.pucpr.gestaoauto.api.dto.usuario.UsuarioDTO;
import br.edu.pucpr.gestaoauto.dao.usuario.UsuarioDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.manager.manutencao.ManutencaoManager;
import br.edu.pucpr.gestaoauto.manager.veiculo.VeiculoManager;
import br.edu.pucpr.gestaoauto.model.usuario.Usuario;
import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;
import br.edu.pucpr.gestaoauto.seguranca.REQUEST_Autenticacao;
import br.edu.pucpr.gestaoauto.seguranca.util.SegurancaUtil;

@Stateless
@LocalBean
public class UsuarioManager implements Manager<Integer, Usuario>  {

	@Inject UsuarioDAO dao;

	@Inject ManutencaoManager manutencaoManager;
	@Inject VeiculoManager veiculoManager;
	@Inject ProprietarioManager proprietarioManager;
	@Inject PreferenciaManager preferenciaManager;
	@Inject PerfilManager perfilManager;

	@Override
	public Usuario save(Usuario usuario) throws NoSuchAlgorithmException {
		this.criptografarSenha(usuario);
		dao.save(usuario);
		perfilManager.createPerfilUsuarioFinal(usuario);
		return usuario;
	}

	@Override
	public Usuario update(Usuario usuario) throws NoSuchAlgorithmException {
		this.criptografarSenha(usuario);
		dao.update(usuario);
		return usuario;
	}

	@Override
	public void delete(Integer id) throws Exception {
		List<Veiculo> veiculoList = veiculoManager.getListByUsuario(id);
		for (Veiculo veiculo : veiculoList) {
			manutencaoManager.deleteAll(manutencaoManager.getListManutencaoByVeiculo(veiculo.getCodigo()));
			veiculoManager.delete(veiculo.getCodigo());
		}
		proprietarioManager.delete(proprietarioManager.getByUsuario(id).getCodigo());
		preferenciaManager.delete(preferenciaManager.getByUsuario(id).getCodigo());
		perfilManager.delete(perfilManager.getByUsuario(id).getCodigo());
		dao.delete(dao.getById(id));
	}

	@Override
	public Usuario getById(Integer id) {
		return dao.getById(id);
	}

	public Usuario convertUsuarioCompletoDTOToEntity(UsuarioCompletoDTO dto) throws Exception {
		Usuario entity = (dto.getCodigo() != null ? this.getById(dto.getCodigo()) : new Usuario());
		entity.setCodigo(dto.getCodigo());
		entity.setDataAceiteTermoUso((dto.getDataAceiteTermoUso() != null ? LocalDate.parse(dto.getDataAceiteTermoUso(), DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null));
		entity.setEmail(dto.getEmail());
		entity.setFoto(dto.getFoto());
		entity.setSenha(dto.getSenha());
		entity.setTokenRecuperarSenha(dto.getTokenRecuperarSenha());
		return entity;
	}

	public UsuarioCompletoDTO convertUsuarioToDTOCompleto(Usuario entity){
		UsuarioCompletoDTO dto = new UsuarioCompletoDTO();
		dto.setCodigo(entity.getCodigo());
		dto.setDataAceiteTermoUso(entity.getDataAceiteTermoUso() != null ? entity.getDataAceiteTermoUso().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null);
		dto.setEmail(entity.getEmail());
		dto.setFoto(entity.getFoto());
		dto.setTokenRecuperarSenha(entity.getTokenRecuperarSenha());
		return dto;
	}

	public UsuarioDTO convertUsuarioToDTO(Usuario entity) {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setCodigo(entity.getCodigo());
		dto.setDataAceiteTermoUso(entity.getDataAceiteTermoUso() != null ? entity.getDataAceiteTermoUso().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null);
		dto.setEmail(entity.getEmail());
		return dto;
	}
	
	public Usuario validaAcesso(REQUEST_Autenticacao credenciais) throws NoSuchAlgorithmException {
		Usuario usuario = dao.getByLogin(credenciais.getUsuario());
		if (usuario != null && validarAcesso(credenciais, usuario))
			return usuario;
		return null;
	}

	private boolean validarAcesso(REQUEST_Autenticacao credenciais, Usuario usuario) throws NoSuchAlgorithmException {
		return usuario.getSenha().equals(SegurancaUtil.gerarSaltedPassword(credenciais.getSenha(), usuario.getSalt()));
	}
	
	private void criptografarSenha(Usuario usuario) throws NoSuchAlgorithmException {
		usuario.setSalt(SegurancaUtil.getNovoSalt());
		usuario.setSenha(SegurancaUtil.gerarSaltedPassword(usuario.getSenha(), usuario.getSalt()));
	}
}