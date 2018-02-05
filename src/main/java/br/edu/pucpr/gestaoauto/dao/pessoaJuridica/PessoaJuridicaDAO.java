package br.edu.pucpr.gestaoauto.dao.pessoaJuridica;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.pessoaJuridica.PessoaJuridica;
import br.edu.pucpr.gestaoauto.model.pessoaJuridica.Reparador;
import br.edu.pucpr.gestaoauto.model.pessoaJuridica.Seguradora;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class PessoaJuridicaDAO extends DAO<Integer, PessoaJuridica> {
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Reparador> getReparadorasByNome(String nome) {
        TypedQuery<Reparador> query = super.entityManager.createQuery(
                "select p from Reparador p where p.nomeFantasia = :nome order by p.nomeFantasia desc"
                , Reparador.class);
        query.setParameter("nome", nome);
        query.setMaxResults(10);
        return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Seguradora> getSeguradorasByNome(String nome) {
        TypedQuery<Seguradora> query = super.entityManager.createQuery(
                "select p from Seguradora p where p.nomeFantasia = :nome order by p.nomeFantasia desc"
                , Seguradora.class);
        query.setParameter("nome", nome);
        query.setMaxResults(10);
        return query.getResultList();
    }
}