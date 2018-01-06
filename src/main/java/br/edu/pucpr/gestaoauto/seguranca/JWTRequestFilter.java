package br.edu.pucpr.gestaoauto.seguranca;

import java.io.IOException;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.interfaces.DecodedJWT;

import br.edu.pucpr.gestaoauto.api.Message;

@Priority(Priorities.AUTHENTICATION)
public class JWTRequestFilter implements ContainerRequestFilter {

	private static Logger log = LoggerFactory.getLogger(JWTRequestFilter.class);

	@Context
	private HttpServletRequest httpServletRequest;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String msgErro = null;
		Status status = Status.INTERNAL_SERVER_ERROR;// Prepara o sistema para um erro inesperado

		try {

			DecodedJWT jwt = this.validaJWT(requestContext);
			log.info(jwt.getSubject());
			status = Status.OK;// Tudo OK
		} catch (NotAuthorizedException ex) { // Token inválido ou problema na validação
			String challenges = null;
			if (ex.getChallenges() != null && ex.getChallenges().size() > 0) {
				challenges = (String) ex.getChallenges().get(0);
			}
			msgErro = ex.getMessage() + (challenges != null ? challenges : "");
			status = Status.UNAUTHORIZED;
		} catch (BadRequestException ex) { // Problema nos parâmetros da requisição
			msgErro = ex.getMessage();
			status = Status.BAD_REQUEST;
		} catch (Exception ex) { // Erro inesperado
			log.error(ex.toString());
			msgErro = ex.getMessage();
			status = Status.INTERNAL_SERVER_ERROR;
		}

		if (status != Status.OK) {
			requestContext.abortWith(Response.status(status).entity(new Message(msgErro, ValidationException.class.getName())).build());
		}
	}

	private DecodedJWT validaJWT(ContainerRequestContext requestContext) throws Exception, NotAuthorizedException {
		String authorizationHeader = requestContext.getHeaderString("Authorization");

		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new NotAuthorizedException(Response.status(Status.UNAUTHORIZED)
					.entity(new Message("autentica\u00E7o inv\u00E1lida", ValidationException.class.getName()))
					.build());
		}

		String token = authorizationHeader.substring("Bearer".length()).trim();

		return TokenHandler.verificarToken(token);
	}
}
