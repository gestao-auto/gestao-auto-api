package br.edu.pucpr.gestaoauto.manager;

import br.edu.pucpr.gestaoauto.api.dto.notificacao.NotificacaoDTO;
import br.edu.pucpr.gestaoauto.dao.notificacao.NotificacaoDAO;
import br.edu.pucpr.gestaoauto.manager.usuario.UsuarioManager;
import br.edu.pucpr.gestaoauto.model.notificacao.Notificacao;
import br.edu.pucpr.gestaoauto.model.notificacao.StatusNotificacao;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class NotificacaoManager implements Manager<Integer, Notificacao> {

    @EJB
    NotificacaoDAO dao;

    @Inject
    UsuarioManager usuarioManager;

    @Override
    public Notificacao save(Notificacao entity) throws Exception {
        return dao.save(entity);
    }

    @Override
    public Notificacao update(Notificacao entity) throws Exception {
        return dao.update(entity);
    }

    @Override
    public void delete(Integer id) throws Exception {
        dao.delete(this.getById(id));
    }

    @Override
    public Notificacao getById(Integer id) throws Exception {
        return dao.getById(id);
    }

    public List<NotificacaoDTO> convertToDTO(List<Notificacao> notificacoes) {
        List<NotificacaoDTO> dtos = new ArrayList<>();
        for (Notificacao notificacao : notificacoes){
            dtos.add(this.convertToDTO(notificacao));
        }
        return dtos;
    }
    public NotificacaoDTO convertToDTO(Notificacao notificacao) {
        NotificacaoDTO dto = new NotificacaoDTO();
        dto.setCodigo(notificacao.getCodigo());
        dto.setTitulo(notificacao.getTitulo());
        dto.setMensagem(notificacao.getMensagem());
        return dto;
    }

    public List<Notificacao> lerNotificacoesPendentes(Integer codigoUsuario) throws Exception {
        List<Notificacao> notificacoes = dao.getByUserByStatus(
                usuarioManager.getById(codigoUsuario), StatusNotificacao.GERADA);
        for (Notificacao notificacao : notificacoes){
            notificacao.setStatus(StatusNotificacao.LIDA);
            this.save(notificacao);
        }
        return notificacoes;
    }
}