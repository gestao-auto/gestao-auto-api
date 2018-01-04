package br.edu.pucpr.gestaoauto.dao.pessoaJuridica;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.pessoaJuridica.PessoaJuridica;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class PessoaJuridicaDAO extends DAO<Integer, PessoaJuridica> {
}