package com.loja.autos.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class MoneyUtil {

	public static String converterString(BigDecimal valor) {
		NumberFormat formatador = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		return formatador.format(valor);
	}

}
