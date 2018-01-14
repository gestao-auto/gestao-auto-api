package br.edu.pucpr.gestaoauto.util;

public class ObjetoNaoEncontradoException extends GestaoAutoException {

	private static final long serialVersionUID = 5201663163177641226L;

	public ObjetoNaoEncontradoException(String key) {
		super.key = key;
	}
	
	public ObjetoNaoEncontradoException(String key, Object... valores) {
		this.key = key;
		this.objects = valores;
	}
}
