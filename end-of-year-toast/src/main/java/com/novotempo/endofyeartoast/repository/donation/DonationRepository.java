package com.novotempo.endofyeartoast.repository.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.novotempo.endofyeartoast.model.donation.DonationModel;

public interface DonationRepository extends JpaRepository<DonationModel, Integer> {
//	@Query(value = "exec Donation..STP_InsertIntoTableTempToast ?1", nativeQuery = true)
	@Procedure(name="insertIntoTableTempToast")
	void insertIntoTableTempToast(@Param("product") String product);
//	@Query(value = "exec Donation..STP_FinishPickingToast", nativeQuery = true)
	@Procedure(name="finishPickingToast")
	void finishPickingToast();
}
