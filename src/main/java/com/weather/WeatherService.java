package com.weather;

import java.util.List;

public interface WeatherService {

	public abstract WeatherReport findCurrentWeatherReport(String city);

	public abstract int findCurrentTemperature(String city);
	
	public double findCurrentTemperatureDecimal(String city);
	
	public Double[] findDayTemperaturesArray(String city);
	
	public List<Double>findDayTemperaturesList(String city);

}