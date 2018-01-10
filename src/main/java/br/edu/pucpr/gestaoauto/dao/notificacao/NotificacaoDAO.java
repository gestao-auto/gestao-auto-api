package br.edu.pucpr.gestaoauto.dao.notificacao;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.notificacao.Notificacao;
import br.edu.pucpr.gestaoauto.model.notificacao.StatusNotificacao;
import br.edu.pucpr.gestaoauto.model.usuario.Usuario;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class NotificacaoDAO extends DAO<Integer, Notificacao> {

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Notificacao> getByUserIdByStatus(Integer codigoUsuario, StatusNotificacao statusNotificacao) {
        TypedQuery<Notificacao> query = super.entityManager.createQuery(
                "select n from Notificacao n where n.usuario.codigo = :usuario and n.status = :status"
                , Notificacao.class)
                .setParameter("usuario", codigoUsuario)
                .setParameter("status", statusNotificacao);
        List<Notificacao> notificacoes = query.getResultList();
        return notificacoes;
    }

    public List<Notificacao> getByUserByStatus(Usuario usuario, StatusNotificacao statusNotificacao) {
        TypedQuery<Notificacao> query = super.entityManager.createQuery(
                "select n from Notificacao n where n.usuario = :usuario and n.status = :status"
                , Notificacao.class)
                .setParameter("usuario", usuario)
                .setParameter("status", statusNotificacao);
        List<Notificacao> notificacoes = query.getResultList();
        return notificacoes;
    }
}
