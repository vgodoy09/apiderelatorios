package com.novotempo.endofyeartoast.services.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novotempo.endofyeartoast.model.donation.PrintToast;
import com.novotempo.endofyeartoast.repository.donation.PrintToastRepository;

@Service
public class PrintToastService {
	
	@Autowired
	PrintToastRepository printToastRepository;
	
	public PrintToast getToastToBePrint(Integer companyId) {
		return printToastRepository.getToastToBePrint(companyId);
	}
}
