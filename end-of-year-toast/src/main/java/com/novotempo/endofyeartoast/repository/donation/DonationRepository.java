package com.novotempo.endofyeartoast.repository.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.novotempo.endofyeartoast.model.donation.DonationModel;

public interface DonationRepository extends JpaRepository<DonationModel, Integer> {
	@Procedure(name="insertIntoTableTempToast")
	void insertIntoTableTempToast(@Param("product") String product);
	@Procedure(name="finishPickingToast")
	Integer finishPickingToast(@Param("product") String product, @Param("userId") Integer userId, @Param("nameLabel") String nameLabel);
}
