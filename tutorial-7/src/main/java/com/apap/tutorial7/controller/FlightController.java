package com.apap.tutorial7.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.service.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	private FlightService flightService;


//	@PostMapping(value = "/add")
//	public PilotModel addPilotSubmit(@RequestBody PilotModel pilot) {
//		return pilotService.addPilot(pilot);
//	}

//	@RequestMapping(value="/flight/add/{licenseNumber}", method=RequestMethod.GET)
//	private String add(@PathVariable(value="licenseNumber") String licenseNumber, Model model) {
//		FlightModel flight = new FlightModel();
//		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
//		flight.setPilot(pilot);
//		
//		model.addAttribute("flight", flight);
//		return "addFlight";
//	}

	@PostMapping(value = "/add")
	public FlightModel addFlightSubmit(@RequestBody FlightModel flight) {
		return flightService.addFlight(flight);
	}

	@GetMapping(value = "/view/{flightId}")
	public FlightModel flightView(@PathVariable("flightId") long flightId) {
		FlightModel flight = flightService.getFlightDetailById(flightId);
		return flight;
	}

	@GetMapping(value = "/viewall")
	public List<FlightModel> flightAll() {
		return flightService.getAllFlight();
	}

	@DeleteMapping(value = "/delete/{flightId}")
	public String deleteFlight(@PathVariable("flightId") long flightId) {
		flightService.deleteFlightById(flightId);
		return "Success delete flight";
	}
	
	 @PutMapping(value = "/update/{flightId}")
	    public String updateFlightSubmit(@PathVariable("flightId") long flightId,
	    		@RequestParam("destination") String destination,
	    		@RequestParam("origin") String origin,
	    		@RequestParam("time") Date time) {
	    	FlightModel flight = flightService.getFlightDetailById(flightId);
	    	if (flight.equals(null)) {
	    		return "Couldn't find your flight";
	    	}
	    	
	    	flight.setDestination(destination);
	    	flight.setOrigin(origin);
	    	flight.setTime(time);
	    	flightService.addFlight(flight);
	    	return "Flight update success";
	    }
//	@RequestMapping(value="/flight/delete", method=RequestMethod.POST)
//	private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
//		for(FlightModel flight : pilot.getPilotFlight()) {
//			flightService.deleteFlightById(flight.getId());
//		}
//		return "delete-pilot";
//	}

}
