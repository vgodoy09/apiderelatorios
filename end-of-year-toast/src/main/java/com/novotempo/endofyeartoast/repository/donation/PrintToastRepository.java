package com.novotempo.endofyeartoast.repository.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.novotempo.endofyeartoast.model.donation.PrintToast;

public interface PrintToastRepository extends JpaRepository<PrintToast, Integer> {
	@Query(value=                                                                                              
			"select                                    " +
		    "    product_id,                           " +
		    "    productName,                          " +
		    "    COUNT(*) amount                       " +
		    "from Attendance..vw_ForPrinting f         " +
		    "where f.company_id = ?1          		   " +
		    "    and f.financialRecord_id IS NOT NULL  " +
			"group by product_id,                      " +
			"		productName						   "
			, nativeQuery=true	)
	PrintToast getToastToBePrint(Integer companyId);
}
