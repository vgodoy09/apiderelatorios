package com.novotempo.endofyeartoast.resources;

import static com.novotempo.endofyeartoast.bankslip.ZipReport.createZip;
import static com.novotempo.endofyeartoast.bankslip.ZipReport.getHearder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novotempo.endofyeartoast.model.WrappedToast;
import com.novotempo.endofyeartoast.services.donation.DonationService;

@RestController
@RequestMapping("/api")
public class ToastController {
	
	@Autowired
	DonationService donationService;

	@GetMapping("/toastreportbyte/{productsId}/{userId}/{nameLabel}")
	public ResponseEntity<?> generationToastAndDispatchLabelReport(@PathVariable(value = "productsId") String productsId, @PathVariable(value = "userId") Integer userId, @PathVariable(value = "nameLabel") String nameLabel) throws Exception {
	    return ResponseEntity.ok(new WrappedToast(createZip("15646", donationService, userId, nameLabel)));
	}
	
	
	@GetMapping("/toastreportdownload/{productsId}/{userId}/{nameLabel}")
	public ResponseEntity<?> downloadZip(@PathVariable(value = "productsId") String productsId, @PathVariable(value = "userId") Integer userId, @PathVariable(value = "nameLabel") String nameLabel) throws Exception {
		return new ResponseEntity<>(createZip(productsId,donationService, userId, nameLabel), getHearder("brinde-fim-de-ano.zip","application/zip"), HttpStatus.OK);
	}//http://localhost:8080/end-of-year-toast/api/toastreportdownload/15646/355822/15646-BOLETO
	
}
