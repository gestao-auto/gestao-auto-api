package br.edu.pucpr.gestaoauto.manager.usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.usuario.PreferenciaDTO;
import br.edu.pucpr.gestaoauto.dao.usuario.PreferenciaDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.manutencao.Revisao;
import br.edu.pucpr.gestaoauto.model.usuario.Preferencia;
import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;

@Stateless
@LocalBean
public class PreferenciaManager implements Manager<Integer, Preferencia> {

    @Inject PreferenciaDAO dao;

	@Inject UsuarioManager usuarioManager;

	@Override
	public Preferencia save(Preferencia entity) throws Exception {
		return dao.save(entity);
	}

	@Override
	public Preferencia update(Preferencia entity) throws Exception {
		return dao.update(entity);
	}

	@Override
	public void delete(Integer id) throws Exception {
		dao.delete(this.getById(id));
	}

	@Override
	public Preferencia getById(Integer id) throws Exception {
		return dao.getById(id);
	}

	public Preferencia getByUsuario(Integer codigoUsuario) {
		return dao.getByUsuario(codigoUsuario);
	}

	public PreferenciaDTO convertEntityToDTO(Preferencia preferencia) {
		PreferenciaDTO dto = new PreferenciaDTO();
		dto.setCodigo(preferencia.getCodigo());
		dto.setCodigoUsuario(preferencia.getUsuario().getCodigo());
		dto.setHorarioNotificacao(preferencia.getHorarioNoficacao().format(DateTimeFormatter.ofPattern("HH:mm")));
		dto.setDiasAntecedenciaNotificacaoRevisao(preferencia.getDiasAntecedenciaNotificacaoRevisao());
		dto.setHodometroAntecedenciaNotificacaoRevisao(preferencia.getHodometroAntecedenciaNotificacaoRevisao());
		return dto;
	}

	public Preferencia convertDTOToEntity(PreferenciaDTO dto) throws Exception {
		Preferencia preferencia = (dto.getCodigo() != null ? this.getById(dto.getCodigo()) : new Preferencia());
		preferencia.setUsuario(usuarioManager.getById(dto.getCodigoUsuario()));
		preferencia.setHorarioNoficacao(LocalTime.parse(dto.getHorarioNotificacao(), DateTimeFormatter.ofPattern("HH:mm")));
		preferencia.setDiasAntecedenciaNotificacaoRevisao(dto.getDiasAntecedenciaNotificacaoRevisao());
		preferencia.setHodometroAntecedenciaNotificacaoRevisao(dto.getHodometroAntecedenciaNotificacaoRevisao());
		return preferencia;
	}

    public boolean aptoAGerarNotificacao(Preferencia preferencia, Revisao proxima, Veiculo veiculo) {
		return (this.aptoNotificarPorKm(preferencia, proxima, veiculo)
                || this.aptoNotificarPorData(preferencia, proxima));
    }

    private boolean aptoNotificarPorKm(Preferencia preferencia, Revisao proxima, Veiculo veiculo) {
        return (preferencia.getHodometroAntecedenciaNotificacaoRevisao().intValue() > 0
            && ((proxima.getOdometroPrevisto().intValue()
                    - preferencia.getHodometroAntecedenciaNotificacaoRevisao().intValue())
				<= veiculo.getOdometro().intValue()));
    }

    private boolean aptoNotificarPorData(Preferencia preferencia, Revisao proxima) {
        return (preferencia.getDiasAntecedenciaNotificacaoRevisao().intValue() > 0
                && (proxima.getDataPrevista().minusDays(
                        preferencia.getHodometroAntecedenciaNotificacaoRevisao().longValue())
                    .isAfter(LocalDate.now())
                ));
    }
}