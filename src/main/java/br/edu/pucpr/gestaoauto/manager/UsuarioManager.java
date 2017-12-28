package br.edu.pucpr.gestaoauto.manager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.edu.pucpr.gestaoauto.api.dto.UsuarioCompletoDTO;
import br.edu.pucpr.gestaoauto.api.dto.UsuarioDTO;
import br.edu.pucpr.gestaoauto.dao.UsuarioDAO;
import br.edu.pucpr.gestaoauto.model.usuario.Usuario;

import java.util.HashSet;

@Stateless
@LocalBean
public class UsuarioManager implements Manager{

	@EJB
	UsuarioDAO dao;

	public Usuario save(Usuario usuario) {
		return dao.save(usuario);
	}

	public Usuario getById(int id) {
		return dao.getById(id);
	}

	public Usuario update(UsuarioCompletoDTO dto) throws Exception {
		return dao.update(this.getEntity(dto));
	}

	public void delete(int id) {
		dao.delete(dao.getById(id));
	}

	public Usuario getEntity(UsuarioCompletoDTO dto) throws Exception {
		Usuario entity = new Usuario();
		entity.setCodusuario(dto.getCodusuario());
		entity.setDataAceiteTermoUso(dto.getDataAceiteTermoUso());
		entity.setEmail(dto.getEmail());
		entity.setFoto(dto.getFoto());
		entity.setSenha(dto.getSenha());
		entity.setTokenRecuperarSenha(dto.getTokenRecuperarSenha());
		return entity;
	}

	public UsuarioCompletoDTO getDTOCompleto(Usuario entity){
		return (new UsuarioCompletoDTO())
				.setCodusuario(entity.getCodusuario())
				.setDataAceiteTermoUso(entity.getDataAceiteTermoUso())
				.setEmail(entity.getEmail())
				.setFoto(entity.getFoto())
				//XXX:Nao retornar a senha do usuario
				.setSenha("")
				.setTokenRecuperarSenha(entity.getTokenRecuperarSenha())
				;
	}

	public UsuarioDTO getDTO(Usuario entity){
		return (new UsuarioDTO())
				.setCodusuario(entity.getCodusuario())
				.setDataAceiteTermoUso(entity.getDataAceiteTermoUso())
				.setEmail(entity.getEmail())
				//XXX:Nao retornar a senha do usuario
				.setSenha("")
				;
	}
}