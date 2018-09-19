package com.novotempo.endofyeartoast.services.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novotempo.endofyeartoast.repository.donation.DonationRepository;

@Service
public class DonationService {
	
	@Autowired
	DonationRepository donationRepository;
	
	public void insertIntoTableTempToast(Integer product) {
		donationRepository.insertIntoTableTempToast(product);
	}
	
	public Integer finishPickingToast(Integer product, Integer userId, String nameLabel) {
		return donationRepository.finishPickingToast(product, userId, nameLabel);
	}
}
