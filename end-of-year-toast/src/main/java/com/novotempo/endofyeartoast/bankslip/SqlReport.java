package com.novotempo.endofyeartoast.bankslip;

public class SqlReport {
	public static String sqlDispatchLabelToast(String lot) {
		String sql = 
				"select  d.numberAtendance,               " +
				"        d.fullName,                      " +
				"        d.enderecoSac,                   " +
				"        d.complementoSac,                " +
				"        d.bairroSac,                     " +
				"        d.cidadeSac,                     " +
				"        d.state,                         " +
				"        d.country,                       " +
				"        d.cepSac,                        " +
				"        d.employee,                      " +
				"        d.closeDate,                     " +
				"        d.numberUser                     " +
				"from ##DispatchLabelTemp d     		  " +
				"where d.num in ("+lot+")                 " +
				"order by d.fullName            		  " ;
		return sql;
	}
	
	public static String sqlBankSlipToast(String lot) {
		String sql = 
				"select                         " +
		        "   b.codigoCedente,            " +
				"	b.agencia,                  " +
				"	b.bairroSac,                " +
				"	b.cepSac,                   " +
				"	b.cidadeSac,                " +
				"	b.estadoSac,                " +
				"	b.enderecoSac,              " +
				"	b.complementoSac,           " +
				"	b.cpfSac,                   " +
				"	b.nomeSac,                  " +
				"	b.carteira,                 " +
				"	b.customizado,              " +
				"	b.dataEmissao,              " +
				"	b.dataProcessamento,        " +
				"	b.dataVencimento,           " +
				"	b.mensagem1,                " +
				"	b.mensagem2,                " +
				"	b.mensagem3,                " +
				"	b.mensagem8,                " +
				"	b.mensagem4,                " +
				"	b.mensagem5,                " +
				"	b.mensagem6,                " +
				"	b.mensagem7,                " +
				"	b.nossoNumero,              " +
				"	b.numeroBanco,              " +
				"	b.numeroDocumento,          " +
				"	b.valor,                    " +
				"	b.angelValue,               " +
				"	b.angelNum,                 " +
				"	b.valueResponsible,         " +
				"	b.company,                  " +
				"	b.dataVencimentoPorExtenso  " +
		        "from ##DispatchLabelTemp b     " +
		        "where b.num in ("+lot+")       " +
		        "order by b.fullName            " ;
		return sql;
	}
}
