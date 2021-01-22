package com.Donor.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Donor.exception.NoContentException;
import com.Donor.model.Donor;
import com.Donor.service.DonorService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DonorController {

	@Autowired
	DonorService service;

	@GetMapping(value = "/getdonors")
	public ResponseEntity<List<Donor>> getAllDonors() throws NoContentException {
		log.info("Fetching all donors");
		return new ResponseEntity<List<Donor>>(service.getAllDonors(), HttpStatus.OK);
	}

	@GetMapping(value = "/getdonorbyid/{id}")
	public ResponseEntity<Donor> getDonorById(@PathVariable("id") int id) throws NoContentException {
		log.info("Fetching donor by id");
		return new ResponseEntity<Donor>(service.getDonorById(id), HttpStatus.OK);
	}

	@GetMapping(value = "/getdonorbyname/{DonorName}")
	public ResponseEntity<List<Donor>> getDonorByName(@PathVariable("DonorName") String DonorName)
			throws NoContentException {
		try {
			log.info("Fetching donor by name");
			return new ResponseEntity<List<Donor>>(service.getDonorByName(DonorName), HttpStatus.OK);
		} catch (NoContentException ex) {
			throw new NoContentException("Data not found");
		}
	}

	@GetMapping(value = "/getdonorbybloodgroup/{bloodGroup}")
	public ResponseEntity<List<Donor>> getDonorByBloodGroup(@PathVariable("bloodGroup") String bloodGroup)
			throws NoContentException {
		try {
			log.info("Fetching donor by blood group");
			return new ResponseEntity<List<Donor>>(service.getDonorByBloodGroup(bloodGroup), HttpStatus.OK);
		} catch (NoContentException ex) {
			throw new NoContentException("Data not found");
		}
	}

	@PostMapping(value = "/authenticatedonor")
	public ResponseEntity<Donor> authenticateRequestor(@RequestBody Donor donor) throws NoSuchElementException {
		log.debug("Authenticating donor");
		return new ResponseEntity<Donor>(service.authenticateDonor(donor), HttpStatus.OK);
	}

	@PostMapping(value = "/createdonor")
	public ResponseEntity<Donor> createDonor(@RequestBody Donor donor) throws SQLIntegrityConstraintViolationException {
		try {
			log.info("Donor added");
			return new ResponseEntity<Donor>(service.createDonor(donor), HttpStatus.CREATED);
		} catch (SQLIntegrityConstraintViolationException ex) {
			throw new SQLIntegrityConstraintViolationException();
		}
	}

	@GetMapping(value = "/approvedonor/{id}")
	public ResponseEntity<Donor> approveDonor(@PathVariable("id") int id) {
		log.info("Donor approved");
		return new ResponseEntity<Donor>(service.approveDonor(id), HttpStatus.OK);
	}

	@PutMapping(value = "/updatedonor/{id}")
	public ResponseEntity<Donor> updateDonor(int id, @RequestBody Donor donor) {
		log.info("Donor updated");
		return new ResponseEntity<Donor>(service.UpdateDonor(id, donor), HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/deletedonor/{id}")
	public boolean deleteDonor(@PathVariable("id") int id) {
		log.info("Deleted donor");
		return service.deleteDonorById(id);
	}

}
