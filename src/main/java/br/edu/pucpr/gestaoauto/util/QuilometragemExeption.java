package br.edu.pucpr.gestaoauto.util;

public class QuilometragemExeption extends GestaoAutoException {

	private static final long serialVersionUID = 2837024313321822853L;

	public QuilometragemExeption(String key) {
		super.key = key;
	}
	
	public QuilometragemExeption(String key, Object... valores) {
		this.key = key;
		this.objects = valores;
	}
}
