package com.apap.tutorial7.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.repository.FlightDb;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightDb flightDb;

	@Override
	public FlightModel addFlight(FlightModel flight) {
		// TODO Auto-generated method stub
		return flightDb.save(flight);

	}

	@Override
	public void deleteFlightById(long id) {
		// TODO Auto-generated method stub
		flightDb.deleteById(id);
	}

	@Override
	public FlightModel getFlightDetailById(long id) {
		// TODO Auto-generated method stub
		return flightDb.findById(id);
	}

	@Override
	public List<FlightModel> getAllFlight() {
		// TODO Auto-generated method stub
		return flightDb.findAll();
	}
}
