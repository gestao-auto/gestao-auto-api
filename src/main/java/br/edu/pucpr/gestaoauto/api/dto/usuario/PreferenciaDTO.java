package br.edu.pucpr.gestaoauto.api.dto.usuario;

public class PreferenciaDTO {

	private Integer codigo;
	private Integer codigoUsuario;
	private String horarioNotificacao;
	private Integer diasAntecedenciaNotificacaoRevisao;
	private Integer hodometroAntecedenciaNotificacaoRevisao;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getHorarioNotificacao() {
		return horarioNotificacao;
	}

	public void setHorarioNotificacao(String horarioNotificacao) {
		this.horarioNotificacao = horarioNotificacao;
	}

	public Integer getDiasAntecedenciaNotificacaoRevisao() {
		return diasAntecedenciaNotificacaoRevisao;
	}

	public void setDiasAntecedenciaNotificacaoRevisao(Integer diasAntecedenciaNotificacaoRevisao) {
		this.diasAntecedenciaNotificacaoRevisao = diasAntecedenciaNotificacaoRevisao;
	}

	public Integer getHodometroAntecedenciaNotificacaoRevisao() {
		return hodometroAntecedenciaNotificacaoRevisao;
	}

	public void setHodometroAntecedenciaNotificacaoRevisao(Integer hodometroAntecedenciaNotificacaoRevisao) {
		this.hodometroAntecedenciaNotificacaoRevisao = hodometroAntecedenciaNotificacaoRevisao;
	}

}
