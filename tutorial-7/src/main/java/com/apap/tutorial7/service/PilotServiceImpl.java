package com.apap.tutorial7.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.repository.PilotDb;

@Service
@Transactional
public class PilotServiceImpl implements PilotService {
	
	@Autowired
	private PilotDb pilotDb;

	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		return pilotDb.findByLicenseNumber(licenseNumber);
	}

	@Override
	public PilotModel addPilot(PilotModel pilot) {
		// TODO Auto-generated method stub
		return pilotDb.save(pilot);
	}

	@Override
	public void deletePilot(PilotModel pilot) {
		// TODO Auto-generated method stub
		pilotDb.delete(pilot);
	}

	@Override
	public PilotModel getPilotDetailById(long id) {
		// TODO Auto-generated method stub
		return pilotDb.findById(id);
	}

}
