package br.edu.pucpr.gestaoauto.util;

public abstract class GestaoAutoException extends Exception {

	private static final long serialVersionUID = 5659727868077870230L;

	protected String key;
	protected Object[] objects;

	public String getKey() {
		return key;
	}

	public Object[] getObjects() {
		return objects;
	}
}
