package br.edu.pucpr.gestaoauto.seguranca;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class TokenHandler {

	private static Logger log = LoggerFactory.getLogger(TokenHandler.class);

	private static String CHAVE_SEGURANCA = "lnqDSP9QJpKEM3u7XRY8xH";
	private static String CONTEXTO = "gestaoautoapi";

	public static String getToken(String codusuario, String usuario)
			throws Exception {

		Algorithm algorithm = null;
		Date dataAcesso = null;
		String token = null;

		try {
			algorithm = Algorithm.HMAC256(CHAVE_SEGURANCA);
			dataAcesso = new Date();

			token = JWT.create().withIssuer(CONTEXTO).withSubject(codusuario).withIssuedAt(dataAcesso)
					.withExpiresAt(getDataExpiracao(dataAcesso))
					.withClaim("login", usuario).sign(algorithm);

		} catch (Exception e) {
			log.error(e.toString());
			throw e;
		}

		return token;
	}

	public static DecodedJWT verificarToken(String token) throws Exception {

		//	SespAuthenticationInfo authInfo = null;
		Algorithm algorithm = null;
		DecodedJWT jwt = null;

		try {
			// Decodificação temporária sem verificação de assinatura para obter o "KeyId"
			jwt = JWT.decode(token);

			algorithm = Algorithm.HMAC256(CHAVE_SEGURANCA);
			JWTVerifier verifier = JWT.require(algorithm).withIssuer(jwt.getIssuer()).build();

			// Decodificação com verificação de assinatura
			jwt = verifier.verify(token);

		//	authInfo = getDecodedTokenFromJWT(jwt);
		} catch (Exception e) {
			log.error(e.toString());
			throw e;
		}
		return jwt;
	}

	private static Date getDataExpiracao(Date dataAcesso) {

		Calendar cal = null;

		cal = Calendar.getInstance();
		cal.setTime(dataAcesso);
		cal.add(Calendar.HOUR_OF_DAY, 24);

		return cal.getTime();
	}
}
