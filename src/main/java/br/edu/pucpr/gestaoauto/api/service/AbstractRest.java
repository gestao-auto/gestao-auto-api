package br.edu.pucpr.gestaoauto.api.service;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import br.edu.pucpr.gestaoauto.api.Message;
import br.edu.pucpr.gestaoauto.util.GestaoAutoException;

public abstract class AbstractRest {

	@Context HttpHeaders header;

	protected Throwable rootCause(Throwable e){
		Throwable root = e;
		if(e.getCause() != null){
			Throwable cause = e.getCause();
			while(null != (cause = cause.getCause())  && (e != cause) ) {
				root = cause;
		    }
		}
		return root;
	}
	
	protected Response serverError(Exception e) {
		if (e instanceof GestaoAutoException) {
			Message message = new Message(
					this.getMessage(((GestaoAutoException) e).getKey(), ((GestaoAutoException) e).getObjects()), e.getClass().getName());
			return Response.serverError().entity(message).build();
		}
		Message message = new Message(this.rootCause(e).getMessage(), this.rootCause(e).getClass().getName());
		return Response.serverError().entity(message).build();
	}
	
	private String getMessage(String key, Object[] param) {
		Locale locale = header.getAcceptableLanguages().size() > 0 ? header.getAcceptableLanguages().get(0) : Locale.getDefault();
		ResourceBundle resource = ResourceBundle.getBundle("i18n/messages", locale);
		return MessageFormat.format(resource.getString(key), param);
	}
}