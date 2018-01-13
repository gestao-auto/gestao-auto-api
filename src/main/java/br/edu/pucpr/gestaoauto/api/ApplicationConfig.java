package br.edu.pucpr.gestaoauto.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import br.edu.pucpr.gestaoauto.api.service.ManutencaoRest;
import br.edu.pucpr.gestaoauto.api.service.NotificacaoRest;
import br.edu.pucpr.gestaoauto.api.service.PecaServicoRest;
import br.edu.pucpr.gestaoauto.api.service.PessoaJuridicaRest;
import br.edu.pucpr.gestaoauto.api.service.PreferenciaRest;
import br.edu.pucpr.gestaoauto.api.service.ProprietarioRest;
import br.edu.pucpr.gestaoauto.api.service.UsuarioRest;
import br.edu.pucpr.gestaoauto.api.service.veiculo.MarcaRest;
import br.edu.pucpr.gestaoauto.api.service.veiculo.ModeloRest;
import br.edu.pucpr.gestaoauto.api.service.veiculo.VeiculoRest;
import br.edu.pucpr.gestaoauto.seguranca.JWTRequestFilter;

@ApplicationPath("api/rest")
public class ApplicationConfig extends javax.ws.rs.core.Application {
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		
		//Adicionar todos os serviços a serem publicados aqui:
		classes.add(ManutencaoRest.class);
		classes.add(MarcaRest.class);
		classes.add(ModeloRest.class);
		classes.add(PecaServicoRest.class);
		classes.add(PessoaJuridicaRest.class);
		classes.add(UsuarioRest.class);
		classes.add(VeiculoRest.class);
		classes.add(PreferenciaRest.class);
		classes.add(NotificacaoRest.class);
		classes.add(ProprietarioRest.class);
		//Filtros 
		classes.add(JWTRequestFilter.class);
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