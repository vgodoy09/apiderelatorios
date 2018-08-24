package com.novotempo.endofyeartoast.repository.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.novotempo.endofyeartoast.model.donation.DonationModel;

public interface DonationRepository extends JpaRepository<DonationModel, Integer> {

	@Query(value = "exec Donation..STP_InsertIntoTableTempToast ?1", nativeQuery = true)
	void insertIntoTableTempToast(String product);
}
