package br.edu.pucpr.gestaoauto.api.service;

import javax.ws.rs.core.Response;

import br.edu.pucpr.gestaoauto.api.Message;

abstract class AbstractRest {
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
		return Response.serverError()
				.entity(new Message(
						this.rootCause(e).getMessage(), 
						this.rootCause(e).getClass().getName()))
				.build();
	}
}