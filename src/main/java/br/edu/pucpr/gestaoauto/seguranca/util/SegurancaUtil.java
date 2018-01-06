package br.edu.pucpr.gestaoauto.seguranca.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class SegurancaUtil {

	private static final Random RANDOM = new SecureRandom();

	/**
	 * Gera um sal (salt) randomico
	 *
	 * @return array de 16 bytes
	 */
	public static byte[] getSaltRandomico() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		return salt;
	}

	/**
	 * Converte e retorna o sal (salt) randomico em formato String
	 *
	 * @return String
	 */
	public static String getNovoSalt() {
		return Base64.getEncoder().encodeToString(getSaltRandomico());
	}

	
	/**
	 * 
	 * Gera um salted password
	 * 
	 * @param senha: String 
	 * @param salt: String
	 *
	 * @return String
	 * @throws NoSuchAlgorithmException
	 */
	public static String gerarSaltedPassword(String senha, String salt) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-512");
		byte[] resultadoDigest = digest.digest((senha + salt).getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(resultadoDigest);
	}
	

}
