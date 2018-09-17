package com.novotempo.endofyeartoast.resources;

import static com.novotempo.endofyeartoast.bankslip.ZipReport.createZip;
import static com.novotempo.endofyeartoast.bankslip.ZipReport.getHearder;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novotempo.endofyeartoast.model.donation.PrintingToast;
import com.novotempo.endofyeartoast.services.donation.DonationService;
import com.novotempo.endofyeartoast.services.donation.LotesPrintedService;
import com.novotempo.endofyeartoast.services.donation.PrintToastService;

@RestController
@RequestMapping("/api")
public class ToastController {
	
	@Autowired
	DonationService donationService;
	
	@Autowired
	LotesPrintedService lotesPrintedService;
	
	@Autowired
	PrintToastService printToastService;

	@PostMapping("/toastreportbyte")
	public ResponseEntity<?> generationToastAndDispatchLabelReport(@RequestBody PrintingToast printing) throws Exception {
	    return ResponseEntity.ok(createZip(printing.getProduct_id()+"", donationService, printing.getUser_id(), printing.getLabelName()));
	}
	
	@GetMapping("/listlotesprinted")
	public ResponseEntity<?> listlotesprinted() throws Exception {
		return ResponseEntity.ok(lotesPrintedService.listLotesPrinted());
	}
	
	@GetMapping("/gettoasttobeprint/{company}/company")
	public ResponseEntity<?> getToastToBePrint(@PathVariable(value = "company") Integer companyId) throws Exception {
		return ResponseEntity.ok(Arrays.asList(printToastService.getToastToBePrint(companyId)));
	}
	
	
	@GetMapping("/toastreportdownload/{productsId}/{userId}/{nameLabel}")
	public ResponseEntity<?> downloadZip(@PathVariable(value = "productsId") String productsId, @PathVariable(value = "userId") Integer userId, @PathVariable(value = "nameLabel") String nameLabel) throws Exception {
		return new ResponseEntity<>(createZip(productsId,donationService, userId, nameLabel).getBankSlipPdf(), getHearder("brinde-fim-de-ano.zip","application/zip"), HttpStatus.OK);
	}//http://localhost:8080/end-of-year-toast/api/toastreportdownload/15646/355822/15646-BOLETO
	
}
