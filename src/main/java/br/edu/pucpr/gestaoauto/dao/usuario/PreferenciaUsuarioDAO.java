package br.edu.pucpr.gestaoauto.dao.usuario;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.usuario.PreferenciaUsuario;

@Stateless
@LocalBean
public class PreferenciaUsuarioDAO extends DAO<Integer, PreferenciaUsuario> {

}
