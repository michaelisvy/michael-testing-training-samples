package com.weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherServiceImpl implements WeatherService {
	
	
	/* (non-Javadoc)
	 * @see com.weather.WeatherService#findCurrentWeatherReport(java.lang.String)
	 */
	@Override
	public WeatherReport findCurrentWeatherReport(String city) {
		if(city.equals("singapore")) {
			return new WeatherReport(24.0, 57, 0.9);
		} 
		else throw new IllegalStateException("City not found");
	}
	
	/* (non-Javadoc)
	 * @see com.weather.WeatherService#findCurrentTemperature(java.lang.String)
	 */
	@Override
	public int findCurrentTemperature(String city) {
		if(city.equals("singapore")) {
			return 30;
		} else if (city.equals("sydney")) {
			return 25;
		}
		else throw new IllegalStateException("City not found");
	}
	
	/**
	 * hardcoded, just meant to test assertions on floats
	 */
	public double findCurrentTemperatureDecimal(String city) {
		return 25.3356;
	}
	
	public List<Double>findDayTemperaturesList(String city) {
		ArrayList<Double> temperatures = new ArrayList<Double>();
		temperatures.add(25.0);
		temperatures.add(28.0);
		temperatures.add(29.0);
		return temperatures;		
	}
	
	public Double[] findDayTemperaturesArray(String city) {
		return new Double[] {25.0, 28.0, 29.0};
	}

}
