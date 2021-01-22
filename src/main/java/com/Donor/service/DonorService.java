package com.Donor.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

import com.Donor.exception.NoContentException;
import com.Donor.model.Donor;

public interface DonorService {

	public Donor getDonorById(int id) throws NoContentException;

	public List<Donor> getDonorByName(String PatientName) throws NoContentException;

	public List<Donor> getDonorByBloodGroup(String bloodGroup) throws NoContentException;

	public Donor createDonor(Donor donor) throws SQLIntegrityConstraintViolationException;

	public Donor approveDonor(int id);

	public Donor UpdateDonor(int id, Donor donor);

	public boolean deleteDonorById(int id);

	public List<Donor> getAllDonors() throws NoContentException;

	public Donor authenticateDonor(Donor donor) throws NoSuchElementException;

}