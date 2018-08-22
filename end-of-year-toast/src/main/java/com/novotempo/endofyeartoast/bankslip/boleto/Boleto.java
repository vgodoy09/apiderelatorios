package com.novotempo.endofyeartoast.bankslip.boleto;

import java.awt.Color;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lowagie.text.pdf.BarcodePostnet;
import com.novotempo.endofyeartoast.model.enums.Aceite;

import com.novotempo.endofyeartoast.bankslip.exception.BoletoException;

public abstract class Boleto {
	
	private int company;
	public static final boolean REGISTER_BILLET = true;
	private String localPagamento;
	private String cedenteCNPJ; 
	
	
	private String numeroBanco;
	//metodo de pagamento e pais respectivo
	private String nomePais;
	private String metodoDePagamento;
	// Parcelas
	private int numeroParcela;
	private int totalParcela;
	// Datas
	private Date dataVencimento;
	private Date dataDoDocumento = new Date();
	private Date dataProcessamento = new Date();
	private Date dataVencimentoPorExtenso = new Date();
	// Cedente
	private String nomeCedente;
	private String enderecoCedente = "Euryale de Jesus Zerbine";
	private String bairroCedente = "JD. Gabriel";
	private String complementoCedente;
	private String cepCedente = "12340-010";
	private String cidadeCedente = "Jacare�";
	private String estadoCedente ="SP";
	private String codigoUnidadeCedente;
	private String nomeUnidadeCedente;
	private String agencia;
	private String codigoCedente;
	// do boleto
	private String image;
	private String numeroDocumento;
	private int moeda = 9;
	private String especie = "R$"; // CONST
	private Aceite aceite; // CONST
	private String carteira; // CONST
	private double quantidadeMoeda;
	private double valor = 0.0;
	private boolean donation = false;
	private String CIP;
	private String address;
	private boolean checkAddress;
	private int donation_id;
	
	// do anjinho
	private Integer angelNum;
	private Double angelValue;
	private Double valueResponsible;
	
	// sacado
	private String nomeSac;
	private String enderecoSac 		= "";
	private String bairroSac 		= "";
	private String complementoSac 	= "";
	private String cepSac 			= "00000000";
	private String cidadeSac 		= "";
	private String estadoSac 		= "";
	private String cpfSac			= "";
	// mensagem
	private String mensagemExtra;
	private String mensagem1;
	private String mensagem2;
	private String mensagem3;
	private String mensagem4;
	private String mensagem5;
	private String mensagem6;
	private String mensagem7;
	private String mensagem8;
	private String mensagem9;
	private String mensagem10;
	
	// customizacao de cima
	private String customizado;
	private int idCustomizado;
	
	
	public Boleto() {
		if(REGISTER_BILLET) {
			localPagamento = "Este boleto pode ser pago em qualquer ag�ncia banc�ria ou casa lot�rica. Ap�s a data de vencimento o boleto dever� ser pago no banco Bradesco em at� 59 dias. ";
			cedenteCNPJ = " CNPJ: 01.385.423/0001-30";
		} else {
			localPagamento = "Pagar preferencialmente em ag�ncias Bradesco";
			cedenteCNPJ = "";
		}
	}
	
	public String getLocalPagamento() {
		return localPagamento;
	}

	public void setLocalPagamento(String localPagamento) {
		this.localPagamento = localPagamento;
	}

	public int getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(int numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public int getTotalParcela() {
		return totalParcela;
	}

	public void setTotalParcela(int totalParcela) {
		this.totalParcela = totalParcela;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public Boleto setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
		return this;
	}

	public Date getDataDoDocumento() {
		return dataDoDocumento;
	}

	public void setDataDoDocumento(Date dataEmissao) {
		this.dataDoDocumento = dataEmissao;
	}
	
	public Date getDataEmissao() {
		return dataDoDocumento;
	}

	public void setDataEmissao(Date dataDoDocumento) {
		this.dataDoDocumento = dataDoDocumento;
	}

	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public String getNomeCedente() {
		return nomeCedente;
	}

	public void setNomeCedente(String nomeCedente) {
		this.nomeCedente = nomeCedente;
	}

	public String getCedenteCNPJ() {
		return cedenteCNPJ;
	}

	public void setCedenteCNPJ(String cedenteCNPJ) {
		this.cedenteCNPJ = cedenteCNPJ;
	}

	public String getEnderecoCedente() {
		return enderecoCedente;
	}

	public void setEnderecoCedente(String enderecoCedente) {
		this.enderecoCedente = enderecoCedente;
	}

	public String getComplementoCedente() {
		return complementoCedente;
	}

	public void setComplementoCedente(String complementoCedente) {
		this.complementoCedente = complementoCedente;
	}

	public String getCepCedente() {
		return cepCedente;
	}

	public void setCepCedente(String cepCedente) {
		this.cepCedente = cepCedente;
	}

	public String getCidadeCedente() {
		return cidadeCedente;
	}

	public void setCidadeCedente(String cidadeCedente) {
		this.cidadeCedente = cidadeCedente;
	}

	public String getEstadoCedente() {
		return estadoCedente;
	}

	public void setEstadoCedente(String estadoCedente) {
		this.estadoCedente = estadoCedente;
	}

	public String getCodigoUnidadeCedente() {
		return codigoUnidadeCedente;
	}

	public void setCodigoUnidadeCedente(String codigoUnidadeCedente) {
		this.codigoUnidadeCedente = codigoUnidadeCedente;
	}

	public String getNomeUnidadeCedente() {
		return nomeUnidadeCedente;
	}

	public void setNomeUnidadeCedente(String nomeUnidadeCedente) {
		this.nomeUnidadeCedente = nomeUnidadeCedente;
	}

	public String getAgencia() {
		return agencia;
	}

	public Boleto setAgencia(String agencia) throws BoletoException{
		this.agencia = agencia;
		return this;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public Boleto setNumeroDocumento(String numeroDocumento){
		this.numeroDocumento = numeroDocumento;
		return this;
	}
	
//	public void setNossoNumero(String nossoNumero) {
//		this.nossoNumero = nossoNumero;
//	}

	public Integer getAngelNum() {
		return angelNum;
	}

	public void setAngelNum(Integer angelNum) {
		this.angelNum = angelNum;
	}

	public Double getAngelValue() {
		return angelValue;
	}

	public void setAngelValue(Double angelValue) {
		this.angelValue = angelValue;
	}

	public Double getValueResponsible() {
		return valueResponsible;
	}

	public void setValueResponsible(Double valueResponsible) {
		this.valueResponsible = valueResponsible;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public Aceite getAceite() {
		return aceite;
	}

	public void setAceite(Aceite aceite) {
		this.aceite = aceite;
	}

	public String getCarteira() {
		return carteira;
	}

	public Boleto setCarteira(String carteira) throws BoletoException{
		this.carteira = carteira;
		return this;
	}

	public String getNomeSac() {
		return nomeSac;
	}

	public void setNomeSac(String nomeSac) {
		this.nomeSac = nomeSac;
	}

	public String getEnderecoSac() {
		return enderecoSac;
	}

	public void setEnderecoSac(String enderecoSac) {
		this.enderecoSac = enderecoSac;
	}

	public String getComplementoSac() {
		return (complementoSac == null ? "" : complementoSac);
	}

	public void setComplementoSac(String complementoSac) {
		this.complementoSac = complementoSac;
	}

	public String getCepSac() {
		return cepSac;
	}

	public void setCepSac(String cepSac) {
		this.cepSac = cepSac;
	}

	public String getCidadeSac() {
		return cidadeSac;
	}

	public void setCidadeSac(String cidadeSac) {
		this.cidadeSac = cidadeSac;
	}

	public String getEstadoSac() {
		return estadoSac;
	}

	public void setEstadoSac(String estadoSac) {
		this.estadoSac = estadoSac;
	}

	public String getMensagem1() {
		return mensagem1;
	}

	public String getMensagem2() {
		return mensagem2;
	}

	public String getMensagem3() {
		return mensagem3;
	}

	public String getMensagem4() {
		return mensagem4;
	}

	public String getMensagem5() {
		return mensagem5;
	}

	public String getMensagem6() {
		return mensagem6;
	}

	public String getMensagem7() {
		return mensagem7;
	}

	public String getMensagem8() {
		return mensagem8;
	}

	public String getMensagem9() {
		return mensagem9;
	}

	public String getMensagem10() {
		return mensagem10;
	}

	public String getCustomizado() {
		return customizado;
	}

	public void setCustomizado(String customizado) {
		this.customizado = customizado;
	}	 

	public String getNumeroBanco() {
		return numeroBanco;
	}

	public void setNumeroBanco(String numeroBanco) throws BoletoException {
		this.numeroBanco = numeroBanco;
	}

	public int getMoeda() {
		return moeda;
	}

	public void setMoeda(int moeda) {
		this.moeda = moeda;
	}

	public double getQuantidadeMoeda() {
		return quantidadeMoeda;
	}

	public void setQuantidadeMoeda(double quantidadeMoeda) {
		this.quantidadeMoeda = quantidadeMoeda;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getCodigoCedente() {
		return codigoCedente;
	}

	public Boleto setCodigoCedente(String codigoCedente) throws BoletoException{
		this.codigoCedente = codigoCedente;
		return this;
	}

	public String getCIP() {
		return (CIP == null ? "" : CIP);
	}

	public void setCIP(String cIP) {
		CIP = cIP;
	}
	
	public String getBairroCedente() {
		return bairroCedente;
	}

	public void setBairroCedente(String bairroCedente) {
		this.bairroCedente = bairroCedente;
	}

	public String getBairroSac() {
		return bairroSac;
	}

	public void setBairroSac(String bairroSac) {
		this.bairroSac = bairroSac;
	}

	
	public void setMensagem(int posicao, String mensagem) throws BoletoException{
		if (posicao < 1 && posicao > 10)
			throw new BoletoException("A posi��o deve ser entre 1 a 10");
		
		switch (posicao) {
		case 1:
			mensagem1 = mensagem;
			break;
		case 2:
			mensagem2 = mensagem;
			break;
		case 3:
			mensagem3 = mensagem;
			break;
		case 4:
			mensagem4 = mensagem;
			break;
		case 5:
			mensagem5 = mensagem;
			break;
		case 6:
			mensagem6 = mensagem;
			break;
		case 7:
			mensagem7 = mensagem;
			break;
		case 8:
			mensagem8 = mensagem;
			break;
		case 9:
			mensagem9 = mensagem;
			break;
		case 10:
			mensagem10 = mensagem;
			break;
		}
	}
	
	/**
	 * calcula o cepNet deste cep
	 * @return
	 * @throws BoletoException
	 */
	public String getCepNetNumber() throws BoletoException{
		 
		String result = "";
		
		String digito = "0";
		int calcCep = 0;
		String cepTmp = cepSac.replace("-", "").replace(".", "");
		char[] cepChar = cepTmp.toCharArray();
		
		for (char c : cepChar){
			calcCep += Integer.parseInt(""+c);
		}
		
		if (calcCep > 9){
			digito = (""+calcCep).substring(0, 1);
		}else{
			digito = (""+calcCep);
		}

		if (digito.equals("0")){
			result = "/" + cepTmp + digito + "\\";
		}else{
			result = "/" + cepTmp + (10 - Integer.parseInt(digito)) + "\\";
		}
		return result;
	}
	
	public Image getImageCepNet() throws BoletoException {
		Image image = null;
		
		try {
			String cep = getCepSac().replace(".", "").replace("-", ""); 
			BarcodePostnet iTextPostnet = new BarcodePostnet();
			iTextPostnet.setCode(cep);
			image = iTextPostnet.createAwtImage(new Color(1), new Color(2));
			image = new javax.swing.ImageIcon(image).getImage();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return image;
	} 

	
	/**
	 * se for doacao, os valores da linha digitavel e codigo de barras serao 0
	 * @return
	 */
	public boolean isDonation() {
		return donation;
	}
	/**
	 * se for doacao, os valores da linha digitavel e codigo de barras serao 0
	 * @param donation
	 */
	public void setDonation(boolean donation) {
		this.donation = donation;
	}

	public String getVencimentoParaImpressao() {
		String result = "� Vista";
		
		if (getDataVencimento() != null){
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			result = df.format(getDataVencimento());
		}
		return result;
	}

	/**
	 * gera o codigo de barras
	 * @return
	 * @throws BoletoException
	 */
	public abstract String getCodigoBarras() throws BoletoException;
	/**
	 * gera o nosso numero
	 * @return
	 * @throws BoletoException
	 */
	public abstract String getNossoNumero() throws BoletoException;
	
	/**
	 * gera a linha digitavel
	 * @return
	 * @throws BoletoException
	 */
	public abstract String getLinhaDigitavel() throws BoletoException;

	public int getIdCustomizado() {
		return idCustomizado;
	}

	public void setIdCustomizado(int idCustomizado) {
		this.idCustomizado = idCustomizado;
	}

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMensagemExtra() {
		return mensagemExtra;
	}

	public void setMensagemExtra(String mensagemExtra) {
		this.mensagemExtra = mensagemExtra;
	}

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

	public String getMetodoDePagamento() {
		return metodoDePagamento;
	}

	public void setMetodoDePagamento(String metodoDePagamento) {
		this.metodoDePagamento = metodoDePagamento;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the checkAddress
	 */
	public boolean isCheckAddress() {
		return checkAddress;
	}

	/**
	 * @param checkAddress the checkAddress to set
	 */
	public void setCheckAddress(boolean checkAddress) {
		this.checkAddress = checkAddress;
	}

	/**
	 * @return the donation_id
	 */
	public int getDonation_id() {
		return donation_id;
	}

	/**
	 * @param donation_id the donation_id to set
	 */
	public void setDonation_id(int donation_id) {
		this.donation_id = donation_id;
	}

	public String getCpfSac() {
		return cpfSac;
	}

	public void setCpfSac(String cpfSac) {
		this.cpfSac = cpfSac;
	}

	/**
	 * @return the dataVencimentoPorExtenso
	 */
	public Date getDataVencimentoPorExtenso() {
		return dataVencimentoPorExtenso;
	}

	/**
	 * @param dataVencimentoPorExtenso the dataVencimentoPorExtenso to set
	 */
	public void setDataVencimentoPorExtenso(Date dataVencimentoPorExtenso) {
		this.dataVencimentoPorExtenso = dataVencimentoPorExtenso;
	}	
	
	
}