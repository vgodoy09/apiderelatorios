package com.novotempo.endofyeartoast.controller;

import static com.novotempo.endofyeartoast.bankslip.ZipReport.createZip;
import static com.novotempo.endofyeartoast.bankslip.ZipReport.getHearder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novotempo.endofyeartoast.model.WrappedToast;

@RestController
@RequestMapping("/api")
public class ToastController {

	@GetMapping("/toastreportbyte/{productsId}")
	public ResponseEntity<?> generationToastAndDispatchLabelReport(@PathVariable(value = "productsId") String productsId) throws Exception {
	    return ResponseEntity.ok(new WrappedToast(createZip("15646")));
	}
	
	
	@GetMapping("/toastreportdownload/{productsId}")
	public ResponseEntity<?> downloadZip(@PathVariable(value = "productsId") String productsId) throws Exception {
		return new ResponseEntity<>(createZip(productsId), getHearder("brinde-fim-de-ano.zip","application/zip"), HttpStatus.OK);
	}//http://localhost:8080/end-of-year-toast/api/toastreportdownload/15646
	
}
