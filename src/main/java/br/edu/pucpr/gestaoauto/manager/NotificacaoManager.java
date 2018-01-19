package br.edu.pucpr.gestaoauto.manager;

import br.edu.pucpr.gestaoauto.api.dto.notificacao.NotificacaoDTO;
import br.edu.pucpr.gestaoauto.dao.notificacao.NotificacaoDAO;
import br.edu.pucpr.gestaoauto.manager.manutencao.ManutencaoManager;
import br.edu.pucpr.gestaoauto.manager.usuario.PreferenciaManager;
import br.edu.pucpr.gestaoauto.manager.usuario.UsuarioManager;
import br.edu.pucpr.gestaoauto.model.manutencao.Revisao;
import br.edu.pucpr.gestaoauto.model.notificacao.Notificacao;
import br.edu.pucpr.gestaoauto.model.notificacao.StatusNotificacao;
import br.edu.pucpr.gestaoauto.model.notificacao.TipoNotificacao;
import br.edu.pucpr.gestaoauto.model.usuario.Preferencia;
import br.edu.pucpr.gestaoauto.model.usuario.Proprietario;
import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Stateless
@LocalBean
public class NotificacaoManager implements Manager<Integer, Notificacao> {

    @Inject NotificacaoDAO dao;

    @Inject UsuarioManager usuarioManager;
    @Inject PreferenciaManager preferenciaManager;
    @Inject ManutencaoManager manutencaoManager;

    @Override
    public Notificacao save(Notificacao entity) throws Exception {
        entity.setStatus(StatusNotificacao.GERADA);
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
        dto.setTipoNotificacao(notificacao.getTipoNotificacao());
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

    public void conferirRevisao(Veiculo veiculo) throws Exception {
        final Proprietario proprietario = veiculo.getProprietario();
        final List<Revisao> revisoes = manutencaoManager.getProximaRevisaoByVeiculo(veiculo.getCodigo());
        final Preferencia preferencia = preferenciaManager.getByUsuario(proprietario.getCodigo());
        if(!revisoes.isEmpty()){
            final Revisao proxima = revisoes.get(0);
            if(preferenciaManager.aptoAGerarNotificacao(preferencia, proxima, veiculo)){
                this.notificarRevisao(veiculo);
            }
        }
    }

    private void notificarRevisao(Veiculo veiculo) throws Exception {
        final ResourceBundle bundle = ResourceBundle.getBundle("messages", veiculo.getProprietario().getIdioma());

        Notificacao notificacao = new Notificacao();
        notificacao.setTipoNotificacao(TipoNotificacao.REVISAO);
        notificacao.setMensagem(bundle.getString("revisao.aviso").concat(veiculo.getPlaca()));
        this.save(notificacao);
    }
}