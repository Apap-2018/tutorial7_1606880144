package com.apap.tutorial7.service;

import java.util.List;

import com.apap.tutorial7.model.FlightModel;

public interface FlightService {
	FlightModel addFlight(FlightModel flight);

	FlightModel getFlightDetailById(long id);

	List<FlightModel> getAllFlight( );

	void deleteFlightById(long id);
}
