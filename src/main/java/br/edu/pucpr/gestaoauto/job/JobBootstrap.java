package br.edu.pucpr.gestaoauto.job;

import br.edu.pucpr.gestaoauto.dao.job.JobDAO;
import br.edu.pucpr.gestaoauto.model.job.Job;

import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class JobBootstrap {
    @Inject JobDAO dao;

    @Schedule(hour = "*", minute = "*", second = "0", persistent = false)
    public void hourly() {
        Job entity = new Job();
        entity.setStatus(BatchStatus.STARTED);
        entity = dao.save(entity);
        BatchRuntime.getJobOperator().start("notificacaoJob", null);
        this.afterRun(entity);
    }

    protected void afterRun(Job entity) {
        entity.setStatus(BatchStatus.COMPLETED);
        dao.update(entity);
    }
}