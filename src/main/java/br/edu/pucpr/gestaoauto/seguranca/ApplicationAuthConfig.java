package br.edu.pucpr.gestaoauto.seguranca;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import br.edu.pucpr.gestaoauto.api.service.AutenticacaoRest;

@ApplicationPath("api/seguranca")
public class ApplicationAuthConfig extends Application{

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		
		classes.add(AutenticacaoRest.class);
		
		return classes;
		
	}
	
	@Override
	public Set<Object> getSingletons() {
		CorsFilter corsFilter = new CorsFilter();
        corsFilter.getAllowedOrigins().add("*");
        corsFilter.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH");
        corsFilter.setCorsMaxAge(-1);
        corsFilter.setAllowedHeaders("Origin, Content-Type, Accept, Authorization");
        corsFilter.setExposedHeaders("X-Pagination-Total-Count, X-Pagination-Current-Page, X-Pagination-Per-Page, X-Pagination-Page-Count, Link");
        Set<Object> cors = new HashSet<>();
        cors.add(corsFilter);
		
		return cors;
	}
}
