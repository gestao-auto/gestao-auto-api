package br.edu.pucpr.gestaoauto.manager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.edu.pucpr.gestaoauto.api.dto.usuario.UsuarioCompletoDTO;
import br.edu.pucpr.gestaoauto.api.dto.usuario.UsuarioDTO;
import br.edu.pucpr.gestaoauto.dao.usuario.UsuarioDAO;
import br.edu.pucpr.gestaoauto.model.usuario.Usuario;

@Stateless
@LocalBean
public class UsuarioManager implements Manager<Integer, Usuario>  {

	@EJB
	UsuarioDAO dao;

	@Override
	public Usuario save(Usuario usuario) {
		return dao.save(usuario);
	}

	@Override
	public Usuario update(Usuario entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(dao.getById(id));
	}

	@Override
	public Usuario getById(Integer id) {
		return dao.getById(id);
	}

	public Usuario getEntity(UsuarioCompletoDTO dto) throws Exception {
		Usuario entity = new Usuario();
		entity.setCodigo(dto.getCodusuario());
		entity.setDataAceiteTermoUso(dto.getDataAceiteTermoUso());
		entity.setEmail(dto.getEmail());
		entity.setFoto(dto.getFoto());
		entity.setSenha(dto.getSenha());
		entity.setTokenRecuperarSenha(dto.getTokenRecuperarSenha());
		return entity;
	}

	public UsuarioCompletoDTO getDTOCompleto(Usuario entity){
		return (new UsuarioCompletoDTO())
				.setCodusuario(entity.getCodigo())
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
				.setCodusuario(entity.getCodigo())
				.setDataAceiteTermoUso(entity.getDataAceiteTermoUso())
				.setEmail(entity.getEmail())
				//XXX:Nao retornar a senha do usuario
				.setSenha("")
				;
	}
}