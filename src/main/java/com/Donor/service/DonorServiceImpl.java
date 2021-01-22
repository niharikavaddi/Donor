package com.Donor.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Donor.exception.NoContentException;
import com.Donor.model.Donor;
import com.Donor.repository.Repository;

@Service("service")
public class DonorServiceImpl implements DonorService {

	@Autowired
	private Repository repository;

	@Override
	public Donor getDonorById(int id) throws NoContentException {
		Optional<Donor> donor = repository.findById(id);
		if (donor.isPresent()) {
			return donor.get();

		} else {
			throw new NoContentException("Data not found");
		}
	}

	@Override
	public List<Donor> getDonorByName(String donorName) {
		Optional<List<Donor>> donor = repository.findByDonorName(donorName);
		if (donor.isPresent()) {
			return donor.get();

		} else {
			return null;
		}

	}

	@Override
	public List<Donor> getDonorByBloodGroup(String bloodGroup) {
		Optional<List<Donor>> donors = repository.findByBloodGroup(bloodGroup);
		if (donors.isPresent()) {
			return donors.get();
		} else {
			return null;
		}
	}

	@Override
	public Donor createDonor(Donor donor) throws SQLIntegrityConstraintViolationException {
		return repository.save(donor);
	}

	@Override
	public Donor approveDonor(int id) {
		Optional<Donor> donor = repository.findById(id);
		if (donor.isPresent()) {
			Donor newdonor = donor.get();
			newdonor.setApproveDonor("approved");
			return repository.save(newdonor);
		} else {
			return null;
		}
	}

	@Override
	public Donor UpdateDonor(int id, Donor donor) {
		Optional<Donor> updateDonor = repository.findById(id);
		if (updateDonor.isPresent()) {
			Donor newDonor = updateDonor.get();
			newDonor = donor;
			return repository.save(newDonor);

		} else {
			return repository.save(updateDonor.get());
		}
	}

	@Override
	public boolean deleteDonorById(int id) {
		repository.deleteById(id);
		return true;
	}

	@Override
	public List<Donor> getAllDonors() {
		return repository.findAll();
	}

	@Override
	public Donor authenticateDonor(Donor donor) throws NoSuchElementException {
		List<Donor> donors = repository.findAll();
		return donors.stream().filter(check -> check.getUserName().equals(donor.getUserName()))
				.filter(check -> check.getPassword().equals(donor.getPassword())).findFirst().get();
	}

}