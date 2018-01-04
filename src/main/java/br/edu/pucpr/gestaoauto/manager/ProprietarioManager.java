package br.edu.pucpr.gestaoauto.manager;

import br.edu.pucpr.gestaoauto.api.dto.usuario.ProprietarioDTO;
import br.edu.pucpr.gestaoauto.dao.usuario.ProprietarioDAO;
import br.edu.pucpr.gestaoauto.dao.usuario.UsuarioDAO;
import br.edu.pucpr.gestaoauto.model.usuario.Proprietario;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class ProprietarioManager implements Manager<Integer, Proprietario>{
    @EJB
    ProprietarioDAO dao;
    @EJB
    UsuarioDAO usuarioDAO;

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

    public Proprietario getEntity(ProprietarioDTO dto) throws Exception {
        Proprietario entity = new Proprietario();
        entity.setCodigo(dto.getCodigo());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setNome(dto.getNome());
        entity.setSobrenome(dto.getSobrenome());
        entity.setSexo(dto.getSexo());
        entity.setUsuario(usuarioDAO.getById(dto.getUsuario()));
        return entity;
    }

    public ProprietarioDTO getDTO(Proprietario entity){
        return (new ProprietarioDTO())
                .setCodigo(entity.getCodigo())
                .setDataNascimento(entity.getDataNascimento())
                .setNome(entity.getNome())
                .setSobrenome(entity.getSobrenome())
                .setSexo(entity.getSexo())
                .setUsuario(entity.getUsuario().getCodigo())
                ;
    }
}