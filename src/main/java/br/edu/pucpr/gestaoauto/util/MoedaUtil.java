package br.edu.pucpr.gestaoauto.util;

import java.text.NumberFormat;
import java.util.Locale;

public class MoedaUtil {

	public static String getValorTexto(Double valor, Locale locale) {
		NumberFormat moedaFormat = NumberFormat.getCurrencyInstance(locale);
		return moedaFormat.format(valor);
	}

}
