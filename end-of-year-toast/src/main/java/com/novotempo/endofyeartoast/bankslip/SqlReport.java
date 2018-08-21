package com.novotempo.endofyeartoast.bankslip;

import static com.novotempo.endofyeartoast.utils.Utils.getPath;

public class SqlReport {
	public static String sqlDispatchLabelToast(Integer company_id, String products, String lot) {
		String sql = 
		"declare @company int = "+company_id+", @product varchar(500) = '"+products+"'                              " +
		"SELECT                                                                                                     " +
        "        att.id numberAtendance,                                                                            " +
		"		p.fullName [name],                                                                                  " +                                                                                                                                                             
		"		ISNULL(ad.streetComplete,'') [address],                                                             " +                                                     
		"		ISNULL(ad.complement,'') complement,                                                                " +                                                      
		"		ISNULL(ad.block, '') district,                                                                      " +                                                     
		"		ISNULL(ad.countyName,'') city,                                                                      " +
		"		ISNULL(ad.state,'') [state],                                                                        " +                                                   
		"		ISNULL(ad.countryName,'') country,                                                                  " +                                                   
		"		ISNULL(ad.zipCode,'000000000') cep,                                                                 " +                                                        
		"		CASE                                                                                                " +                                
		"			WHEN ad.number = '00'                                                                           " +                                
		"			THEN 'SN'                                                                                       " +                                
		"			ELSE ad.streetComplete                                                                          " +                                
		"		END streetComplete,                                                                                 " +                                
		"		( SELECT TOP 1                                                                                      " +
		"			isnull(cc.checkAddress, 0)                                                                      " +
		"		  FROM Corporate..CountryCompany cc                                                                 " +
		"		  where cc.country_id = ad.country_id ) checkAddress,                                               " +
		"		ad.abbreviationState,                                                                               " +                                
		"		ad.address_id,                                                     				                    " +                                
		"		ad.country_id,                                                     				                    " +                                                                                                                                                    
		"		lote.num arrestedCode,                                                                              " +    
		"		t.name treatmentName,                                                                               " +                                
		"		att.id attendanceId,                                                                                " +                                
		"		att.closeDate,                                                                                      " +
		"		i.user_id,                                                                                          " +
		"		CASE                                                                                                " +                                
		"			WHEN (u.userType_id = 1 or u.userType_id = 4) and u.resignationDate is not null then ''         " +                                    
		"			WHEN (u.userType_id = 1 or u.userType_id = 4) and u.resignationDate is null then ' - FUNCIONÁRIO'              " +  
		"           ELSE ''	 																						" +
		"		END employee,                                                                                     	" +
		"       '"+getPath("image/"+DispatchLabelReport.LOGO_NAME)+"' logoName,										" +
        "          p.id numberUser                                                                         			" +
		"FROM (                                                                                                     " +
		"		  SELECT DISTINCT                                                                                   " +                                  
		"			(SELECT                                                                                         " +                                
		"					CONVERT(VARCHAR(200), fp.product_id) + ',' AS [text()]                                  " +                                
		"					FROM Attendance..vw_ForPrinting fp                                                      " +                                
		"					INNER JOIN Stock..Product p ON p.id = fp.product_id                                     " +                                
		"					WHERE                                                                                   " +                                
		"						fp.id = bds.id                                                                      " +                                    
		"					ORDER BY fp.product_id ASC FOR XML PATH('') ) products,	                                " +                                
		"			bds.id as attendance_id                                                                         " +                                
		"		  FROM Attendance..vw_ForPrinting bds                                                               " +                                  
		"		  WHERE bds.company_id = @company ) AS productsByAttendance                                         " +                                  
		"	inner join Attendance.dbo.Attendance att on att.id = productsByAttendance.attendance_id                 " +
        "    inner join Attendance.dbo.Issue i on att.issue_id = i.id                           					" +			
		"	inner join Corporate.dbo.vw_PersonNormal p on p.id = att.person_id                                      " +                                
		"	inner join Corporate..vw_AddressAll ad on ad.person_id = p.id                                           " +
		"	left join Corporate.dbo.vw_UserInfo u on u.person_id = p.id                                             " +                            
		"	left join Corporate.dbo.Treatment t on t.id = p.treatment_id                                            " +                                
		"	inner join Attendance..Service ss on ss.attendance_id = att.id                                          " +                                
		"	inner join Attendance.dbo.Service_FinancialRecord sr on sr.service_id = ss.id                           " +                                
		"	inner join Donation..Donation_FinancialRecord dfr on dfr.financialRecord_id = sr.financialRecord_id     " +
        "    left join Corporate..CompanyConfig compc ON compc.company_id = @company	                            " +                                    
		"	left join Financial..FinancialCompany fc on fc.id = compc.billetFinancialCompany_id                     " +                                
		"	left join Corporate..Company com on com.id = fc.company_id                                              " +     								                                      									
		"	cross apply( select                                                                                     " +
		"					case                                                                                    " +
		"						when ad.zipCode between '01000000' and '09999999' then 1                            " +
		"						when ad.zipCode between '12300000' and '12349999' then 2                            " +
		"						when ad.zipCode between '12200000' and '12899999' then 3                            " +
		"						when ad.zipCode between '18000000' and '18599999' then 4                            " +
		"						when ad.zipCode between '18600000' and '18999999' then 5                            " +
		"						when ad.zipCode like '13%' then 6                                                   " +
		"						when ad.zipCode like '14%' then 7                                                   " +
		"						when ad.zipCode like '15%' then 8                                                   " +
		"						when ad.zipCode like '16%' then 9                                                   " +
		"						when ad.zipCode like '17%' then 10                                                  " +
		"						when ad.zipCode like '19%' then 11                                                  " +
		"						when ad.zipCode between '20000000' and '59999999' then 12                           " +
		"						when ad.zipCode between '60000000' and '99999999' then 13                           " +
		"						when ad.zipCode between '10000000' and '12199999' then 14                           " +
		"						when ad.zipCode between '12900000' and '12999999' then 15                           " +
		"						else 0                                                                              " +
		"					end lote ) lote(num)                                                                    " +
		"where                                                                                                      " +                                
		"	REPLACE(productsByAttendance.products + ',', ',,', '') = @product                                       " +                                
		"	and ad.addressType_id = (select isnull(                                                                 " +                               
		"											(                                                               " +                                
		"												select mailingToAddressType_id                              " +                                
		"												from Corporate.dbo.CompanyConfig                            " +                                
		"												where company_id = @company                                 " +                                
		"												),                                                          " +                                
		"											4)                                                              " +                                
		"									)                                                                       " +
		"	and lote.num in("+lot+")                                                  								" +
		"order by fullName																							";
		return sql;
	}
}
