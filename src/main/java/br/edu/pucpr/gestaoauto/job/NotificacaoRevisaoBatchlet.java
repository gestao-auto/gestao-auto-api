package br.edu.pucpr.gestaoauto.job;

import br.edu.pucpr.gestaoauto.manager.NotificacaoManager;
import br.edu.pucpr.gestaoauto.manager.manutencao.ManutencaoManager;
import br.edu.pucpr.gestaoauto.model.manutencao.Revisao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.util.List;

@Named("NotificacaoRevisaoBatchlet")
public class NotificacaoRevisaoBatchlet extends AbstractBatchlet {
    private static Logger log = LoggerFactory.getLogger(NotificacaoRevisaoBatchlet.class);

    @Inject JobContext jobContext;
    @Inject ManutencaoManager manutencaoManager;
    @Inject NotificacaoManager notificacaoManager;

    public NotificacaoRevisaoBatchlet(){}

    @Override
    public String process() {
        log.info("NotificacaoRevisaoBatchlet - process - " + this.jobContext.getExecutionId());

        List<Revisao> revisoes = this.manutencaoManager.findByDate(LocalDate.now());
        log.info("NotificacaoRevisaoBatchlet - process - LocalDate:" + LocalDate.now());
        log.info("NotificacaoRevisaoBatchlet - process - revisoes:" + revisoes.size());
        for (Revisao revisao: revisoes) {
            try {
                this.notificacaoManager.conferirRevisao(revisao.getVeiculo());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return BatchStatus.COMPLETED.toString();
    }
}