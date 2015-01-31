package com.weather;

import java.util.List;

// Class used so we can test mocks
public class WeatherController {
	
	private WeatherService service;
	
	

	public WeatherController(WeatherService service) {
		super();
		this.service = service;
	}

	public WeatherReport findCurrentWeatherReport(String city) {
		return service.findCurrentWeatherReport(city);
	}

	public int findCurrentTemperature(String city) {
		return service.findCurrentTemperature(city);
	}

	public List<Double> findDayTemperaturesList(String city) {
		return service.findDayTemperaturesList(city);
	}

}
