package br.edu.pucpr.gestaoauto.dao.job;

import br.edu.pucpr.gestaoauto.dao.DAO;
import br.edu.pucpr.gestaoauto.model.job.Job;
import br.edu.pucpr.gestaoauto.model.usuario.Perfil;

import javax.batch.runtime.BatchStatus;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Stateless
public class JobDAO extends DAO<Integer, Job> {
    @Override
    public Job save(Job entity){
        if(entity.getDataInicio() == null){
            entity.setDataInicio(LocalDateTime.now());
        }
        return super.save(entity);
    }

    @Override
    public Job update(Job entity){
        if(entity.getStatus() == BatchStatus.COMPLETED && entity.getDataFim() == null){
            entity.setDataFim(LocalDateTime.now());
        }
        return super.update(entity);
    }
}