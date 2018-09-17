package com.novotempo.endofyeartoast.services.donation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novotempo.endofyeartoast.model.donation.LotesPrintedDTO;
import com.novotempo.endofyeartoast.repository.donation.LotesPrintedRepository;

@Service
public class LotesPrintedService {
	
	@Autowired
	LotesPrintedRepository lotesPrintedRepository;
	
	public List<LotesPrintedDTO> listLotesPrinted() {
		return lotesPrintedRepository.listLotesPrinted();
	}
}
