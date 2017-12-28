package br.edu.pucpr.gestaoauto.api;

public class Message {
	private final String message;
	private final String type;

	public Message(String message) {
		this.message = message;
		this.type = "";
	}
	
	public Message(String message, String type) {
		this.message = message;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public String getType() {
		return type;
	}
}