package com.novotempo.endofyeartoast.repository.donation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.novotempo.endofyeartoast.model.donation.LotesPrintedDTO;

public interface LotesPrintedRepository extends JpaRepository<LotesPrintedDTO, Integer> {
	@Query(value=                                                                                              
			"SELECT                                                                                            " +
		    " distinct b.id,                                                                                   " +
	        "  p.product_id,                                                                                   " +
		    "  b.labelName,                                                                                    " +
		    "  b.registryDate,                                                                                 " +
		    "  b.amount,                                                                                       " +
		    "  u.login                                                                                 		   " +
		    "     FROM Attendance..BatchLabelPrinting b                                                        " +
	        "        INNER JOIN Attendance..BatchLabelPrintingProduct p on p.batchLabelPrinting_id = b.id      " +
	        "        INNER JOIN Attendance..BatchLabelPrintingDetail d on d.batchLabelPrinting_id = b.id       " +
	        "        INNER JOIN Attendance..Service s ON s.attendance_id = d.attendance_id                     " +
	        "        INNER JOIN Attendance..Service_FinancialRecord sr on sr.service_id = s.id                 " +
	        "        INNER JOIN Corporate..UserInfo u on u.person_id = b.registeredByUser_id                   " +
	        "order by registryDate desc   	 																   ", nativeQuery=true	)
	List<LotesPrintedDTO> listLotesPrinted();
}
