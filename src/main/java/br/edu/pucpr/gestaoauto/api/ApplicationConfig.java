package br.edu.pucpr.gestaoauto.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;

import br.edu.pucpr.gestaoauto.api.service.*;
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
		classes.add(PreferenciaUsuarioRest.class);
		classes.add(NotificacaoRest.class);
		//Filtros 
		classes.add(JWTRequestFilter.class);
		return classes;
		
	}
}