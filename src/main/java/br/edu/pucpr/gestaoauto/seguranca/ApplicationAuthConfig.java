package br.edu.pucpr.gestaoauto.seguranca;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.edu.pucpr.gestaoauto.api.service.AutenticacaoRest;

@ApplicationPath("api/seguranca")
public class ApplicationAuthConfig extends Application{

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		
		classes.add(AutenticacaoRest.class);
		
		return classes;
		
	}
}
