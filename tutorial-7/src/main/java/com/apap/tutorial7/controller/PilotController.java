package com.apap.tutorial7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.rest.PilotDetail;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.PilotService;

@RestController
@RequestMapping("/pilot")
public class PilotController {

	@Autowired
	private PilotService pilotService;

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}

	@RequestMapping("/")
	private String home() {
		return "home";
	}

	@GetMapping(value = "/status/{licenseNumber}")
	public String getStatus(@PathVariable("licenseNumber") String licenseNumber) throws Exception {
		String path = Setting.pilotUrl + "/pilot?licenseNumber=" + licenseNumber;
		return restTemplate.getForEntity(path, String.class).getBody();
	}

	@GetMapping(value = "/full/{licenseNumber}")
	public PilotDetail postStatus(@PathVariable("licenseNumber") String licenseNumber) throws Exception {
		String path = Setting.pilotUrl + "/pilot";
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		PilotDetail detail = restTemplate.postForObject(path, pilot, PilotDetail.class);
		return detail;
	}

	@PostMapping(value = "/add")
	public PilotModel addPilotSubmit(@RequestBody PilotModel pilot) {
		return pilotService.addPilot(pilot);
	}

	@GetMapping(value = "/view/{licenseNumber}")
	public PilotModel viewPilot(@PathVariable("licenseNumber") String licenseNumber) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		return pilot;
	}

	@DeleteMapping("/delete")
	public String deletePilot(@RequestParam("pilotId") long pilotId) {
		PilotModel pilot = pilotService.getPilotDetailById(pilotId);
		pilotService.deletePilot(pilot);
		return "delete-pilot";
	}

	@PutMapping(value = "/update/{pilotId}")
	public String updatePilotSubmit(@PathVariable("pilotId") long pilotId, @RequestParam("name") String name,
			@RequestParam("flyHour") int flyHour) {
		PilotModel pilot = pilotService.getPilotDetailById(pilotId);
		if (pilot.equals(null)) {
			return "not-found";
		}
		pilot.setName(name);
		pilot.setFlyHour(flyHour);
		pilotService.addPilot(pilot);
		return "update";
	}

//	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
//	private String add(Model model) {
//		model.addAttribute("pilot", new PilotModel());
//		return "addPilot";
//	}
//
//	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
//	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
//		pilotService.addPilot(pilot);
//		;
//		return "add";
//	}

//	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
//	private String viewPilot(@RequestParam("licenseNumber") String licenseNumber, Model model) {
//		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
//
//		model.addAttribute("pilot", pilot);
//		return "view-pilot";
//	}

//	@RequestMapping("/pilot/delete")
//	public String delete(@RequestParam("licenseNumber") String licenseNumber, Model model) {
//		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
//		pilotService.deletePilot(pilot);
//		return "delete-pilot";
//	}

//	@RequestMapping(value = "/pilot/update/{id}", method = RequestMethod.GET)
//	private String updatePilot(@PathVariable(value = "id") long id, Model model) {
//		PilotModel pilot = pilotService.getPilotDetailById(id);
//		if (pilot != null) {
//			model.addAttribute("pilot", pilot);
//			return "updatePilot";
//		} else {
//			return "not-found";
//		}
//	}
//
//	@RequestMapping(value = "/pilot/update", method = RequestMethod.POST)
//	private String updatePilotSubmit(@ModelAttribute PilotModel pilot) {
//		pilotService.addPilot(pilot);
//		return "update";
//	}

}
