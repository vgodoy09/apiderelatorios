package com.novotempo.endofyeartoast.bankslip.validate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

import com.novotempo.endofyeartoast.bankslip.boleto.Boleto;
import com.novotempo.endofyeartoast.bankslip.exception.BoletoException;

public class ValidacaoBoleto {

	/**
	 * Valida o campo baseado no numero de posicoes da string (varia de banco a banco)
	 * 
	 * @param numero
	 * @param tamanho - se for zero, nao valida o tamanho
	 * @param nomeCampo - caso queira informar na mensagem o nome do campo validado
	 * @return
	 * @throws BoletoException
	 */
	public static boolean validaNumeroTamanhoEObrigatoriedade(String numero, int tamanho, String nomeCampo) throws BoletoException{
		boolean result = true;
		String msg = (nomeCampo == null ? "Este campo" : nomeCampo);
		// numero do documento nao pode ser vazio
		if (numero == null || numero.equals("")){
			throw new BoletoException(msg+" n�o pode ser nulo ou vazio");

		}
		// deve ser somente numerico
		if (!StringUtils.isNumeric(numero)){
			throw new BoletoException(msg+" deve ser exclusivamente num�rico");
		}
		// verifica o tamanho precisa ter (depende de cada banco)
		if (tamanho> 0 && numero.length() != tamanho ){
			throw new BoletoException(msg+" deve possuir ("+tamanho+") caracteres");
		}

		return result;
	}
	/**
	 * Valida o campo baseado no numero de posicoes da string (varia de banco a banco)
	 * 
	 * @param numero
	 * @param tamanho - se for zero, nao valida o tamanho
	 * @return
	 * @throws BoletoException
	 */
	public static boolean validaNumeroTamanhoEObrigatoriedade(String numero, int tamanho) throws BoletoException{
		return validaNumeroTamanhoEObrigatoriedade(numero, tamanho, null);
	}
	/**
	 * preenche a esqueda com zero
	 * @param numero
	 * @param total
	 * @return
	 * @throws BoletoException
	 */
	public static String preencheAEsquerdaComZero(String numero, int total) throws BoletoException{
		long n = 0;
		try {
			n = Long.parseLong(numero);	
		} catch (Exception e) {
			throw new BoletoException("O n�mero do documento deve ser excluisivamente num�rico");
		}


		return String.format("%0"+total+"d", n);
	}

	/**
	 * aplica o fator peso e retorna o resto de mod 11
	 * @param numero
	 * @param peso
	 * @param crescente
	 * @return
	 */
	public static String aplicaFatorPorPesoModulo11(String numero, short peso, boolean crescente){
		// quebra a string em array
		char[] quebra = numero.toCharArray();

		short pesoTmp = peso; 
		int somatoria = 0;

		if (crescente){
			pesoTmp = 2;
			// multiplica cada elemento pelo peso
			for (short i= (short)(quebra.length-1); i >= 0; i--){
				// a conta vai de peso a 2
				if (pesoTmp > peso)
					pesoTmp = 2;
				// faz a multiplicacao pelo peso e faz a somatoria
				somatoria = somatoria + (Integer.parseInt(String.valueOf(quebra[i])) * pesoTmp);
				pesoTmp++;
			}
		}else{
			// multiplica cada elemento pelo peso
			for (short i= (short)(quebra.length-1); i >= 0; i--){
				// a conta vai de peso a 2
				if (pesoTmp < 2)
					pesoTmp = peso;
				// faz a multiplicacao pelo peso e faz a somatoria
				somatoria = somatoria + (Integer.parseInt(String.valueOf(quebra[i])) * pesoTmp);
				pesoTmp--;
			}
		}
		// pega o resto
		String resto = String.valueOf(somatoria % 11);
		return resto;
	}
	/**
	 * aplica o fator peso e retorna o digito da linha digitavel
	 * @param numero
	 * @param peso
	 * @param crescente
	 * @return
	 */
	public static String aplicaFatorPorPesoModulo10(String numero, short peso, boolean crescente){
		// quebra a string em array
		char[] quebra = numero.toCharArray();

		String digito = "";

		short pesoTmp = peso; 
		int somatoria = 0;

		if (crescente){
			pesoTmp = 1;
			// multiplica cada elemento pelo peso
			for (short i= (short)(quebra.length-1); i >= 0; i--){
				// a conta vai de peso a 2
				if (pesoTmp > peso)
					pesoTmp = 1;
				// faz a multiplicacao pelo peso e faz a somatoria
				int sum = (Integer.parseInt(String.valueOf(quebra[i])) * pesoTmp);
				if (sum >= 10){
					sum = Integer.parseInt(String.valueOf(sum).substring(0,1)) + Integer.parseInt(String.valueOf(sum).substring(0,2));
				}
				somatoria = somatoria + sum; 
				pesoTmp++;
			}
		}else{
			// multiplica cada elemento pelo peso
			for (short i= (short)(quebra.length-1); i >= 0; i--){
				// a conta vai de peso a 2
				if (pesoTmp < 1)
					pesoTmp = peso;
				// faz a multiplicacao pelo peso e faz a somatoria
				// faz a multiplicacao pelo peso e faz a somatoria
				int sum = (Integer.parseInt(String.valueOf(quebra[i])) * pesoTmp);
				if (sum >= 10){
					sum = Integer.parseInt(String.valueOf(sum).substring(0,1)) + Integer.parseInt(String.valueOf(sum).substring(0,2));
				}
				somatoria = somatoria + sum; 
				pesoTmp--;
			}
		}

		if (somatoria < 10){
			digito = String.valueOf(10 - somatoria);
		}else{
			// pega o resto
			int resto = 10 - (somatoria % 10);
			if (resto == 10)
				digito = "0";
			else
				digito = String.valueOf(resto);
		}

		return digito;
	}

	/**
	 * monta as 20 primeiras posicoes do codigo de barras, sem o digito verificador 
	 * @param boleto
	 * @return
	 * @throws BoletoException
	 */
	public static String montaCodigoBarrasPrimeiras20posicoes(Boleto boleto) throws BoletoException{
		String msgError = "Erro ao gerar o c�digo de barras,";
		// verifica se boleto esta nulo
		if (boleto == null){
			throw new BoletoException("Boleto n�o pode estar nulo");
		}

		String numeroBanco = boleto.getNumeroBanco();
		int moeda		   = boleto.getMoeda();
		String fatorVencimento = "";
		String valor		= "000";
		// pega o numero do banco
		try {
			numeroBanco = numeroBanco.split("-")[0];
		} catch (Exception e) {
			throw new BoletoException(msgError+" verique se o n�mero do banco esta neste formato: XXX-X ");
		}

		// se for doacao o valor para o codigo de barras eh '0'
//		if (boleto.isDonation()){
//			valor = "000";
//		}else{
//			try {
//				DecimalFormat decimal = new DecimalFormat( "0.00" );  
//				valor = decimal.format(0.0).replace(".", "").replace(",", "");
//			} catch (Exception e) {
//				throw new BoletoException(msgError+" valor do boleto com problema");
//			}
//		}

		// verifica o numero do banco
		validaNumeroTamanhoEObrigatoriedade(numeroBanco, 3, "N�mero do banco");
		// verifica a moeda
		validaNumeroTamanhoEObrigatoriedade(String.valueOf(moeda), 1, "Moeda");

		// fator de vencimento
		fatorVencimento = preencheAEsquerdaComZero(String.valueOf(fatorDeVencimento(boleto.getDataVencimento())), 4);
		// valor do Documento
		valor = preencheAEsquerdaComZero(valor, 10);


		return numeroBanco+moeda+fatorVencimento+valor;
	}

	/**
	 * Monta as 4 primeiras posicoes da linha digitavel
	 * @param boleto
	 * @return
	 */
	public static String montaLinhaDigitavel4PrimeirasPosicoes(Boleto boleto) throws BoletoException{

		String msgError = "Erro ao gerar o c�digo de barras,";
		String numeroBanco = boleto.getNumeroBanco();
		int moeda		   = boleto.getMoeda();

		// pega o numero do banco
		try {
			numeroBanco = numeroBanco.split("-")[0];
		} catch (Exception e) {
			throw new BoletoException(msgError+" verique se o n�mero do banco esta neste formato: XXX-X ");
		}

		// verifica o numero do banco
		validaNumeroTamanhoEObrigatoriedade(numeroBanco, 3, "N�mero do banco");
		// verifica a moeda
		validaNumeroTamanhoEObrigatoriedade(String.valueOf(moeda), 1, "Moeda");

		return numeroBanco+moeda;
	}

	/**
	 * cria o fator de vencimento
	 * @param vencimento
	 * @return
	 * @throws BoletoException
	 */
	public static int fatorDeVencimento(Date vencimento) throws BoletoException{
		int result = 0;
		try {
			if (vencimento == null)
				return result;

			GregorianCalendar dataBase2 = new GregorianCalendar(1997, Calendar.OCTOBER, 7);
			GregorianCalendar vencimento2 = new GregorianCalendar();
			vencimento2.setTime(vencimento);	    

			long diferenca = vencimento2.getTimeInMillis() - dataBase2.getTimeInMillis();
			long diferencaDias = diferenca/(24*60*60*1000);

			result = (int) diferencaDias;

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return result;
	}



	/**
	 * Monta o digito verificador do codigo de barras
	 * @param numeroCodigoBarra
	 * @return
	 * @throws BoletoException
	 */
	public static String montaDigitoVerificadorCodigoBarras(String numeroCodigoBarra) throws BoletoException{

		validaNumeroTamanhoEObrigatoriedade(numeroCodigoBarra, 43, "N�mero de C�digo Barra");

		String resto = aplicaFatorPorPesoModulo11(numeroCodigoBarra, (short)9, true);

		if (Integer.parseInt(resto) <= 1 || Integer.parseInt(resto) > 9){
			resto = "1";
		}else{
			resto = String.valueOf(11-Short.parseShort(resto));
		}
		return resto;
	}

	/**
	 * pega a data em formato juliano
	 * @param vencimento
	 * @return
	 */
	public static String formataDataJuliano(Date vencimento){
		String result = "0000";
		if (vencimento == null)
			return result;

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(vencimento); 
		return gc.get(Calendar.DAY_OF_YEAR)+ String.valueOf(gc.get(Calendar.YEAR)).substring(3,4);		
	}

	/**
	 * Adiciona um ponto na posicao 5 do campo da linha digitavel
	 * @param campo
	 * @return
	 */
	public static String adicionaPontoNoCampoLinhaDigitavel(String campo){
		String result = "";
		char[] chars = campo.toCharArray();
		short i = 0;
		for (char c : chars){
			if (i == 5)
				result += ".";
			result += c;
			i++;
		}
		return result;
	}

	public static String montaCampo5LinhaDigitavel(Boleto boleto) throws BoletoException{
		// posicao 34-37 - Fator Vencimento
		String fatorVencimento = ValidacaoBoleto.preencheAEsquerdaComZero(String.valueOf(ValidacaoBoleto.fatorDeVencimento(boleto.getDataVencimento())),4);
		String valor		= "000";
		// se for doacao o valor para o codigo de barras eh '0'
//		if (boleto.isDonation()){
//			valor = "000";
//		}else{
//			try {
//				DecimalFormat decimal = new DecimalFormat( "0.00" );  
//				valor = decimal.format(boleto.getValor()).replace(".", "").replace(",", "");
//			} catch (Exception e) {
//				throw new BoletoException("Valor do boleto com problema");
//			}
//		}
		valor = ValidacaoBoleto.preencheAEsquerdaComZero(valor, 10);

		return fatorVencimento+valor;
	}
}
