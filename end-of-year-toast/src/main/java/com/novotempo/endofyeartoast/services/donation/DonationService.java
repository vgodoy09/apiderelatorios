package com.novotempo.endofyeartoast.services.donation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novotempo.endofyeartoast.repository.donation.DonationRepository;

@Service
public class DonationService {
	
	@Autowired
	DonationRepository donationRepository;
	
	public void insertIntoTableTempToast(String product) {
		donationRepository.insertIntoTableTempToast(product);
	}
	
	public void finishPickingToast(String product, Integer userId, String nameLabel) {
		donationRepository.finishPickingToast(product, userId, nameLabel);
	}
}
