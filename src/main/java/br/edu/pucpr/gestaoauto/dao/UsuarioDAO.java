package br.edu.pucpr.gestaoauto.dao;

import br.edu.pucpr.gestaoauto.model.usuario.Usuario;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;

@Stateless
public class UsuarioDAO extends DAO<Integer, Usuario> {

}
