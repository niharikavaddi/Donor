package com.Donor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Donor.model.Donor;

public interface Repository extends JpaRepository<Donor, Integer> {

	public Optional<List<Donor>> findByDonorName(String donorName);

	public Optional<List<Donor>> findByBloodGroup(String bloodGroup);
}
