package com.weather;

public class WeatherReport {
	
	private Double temperature;
	private Integer aqiPollutionIndex;
	private Double humidity;
	
	public WeatherReport(Double temperature, Integer aqiPollutionIndex, Double humidity) {
		super();
		this.temperature = temperature;
		this.aqiPollutionIndex = aqiPollutionIndex;
		this.humidity = humidity;
	}
	public Double getTemperature() {
		return temperature;
	}
	public Integer getAqiPollutionIndex() {
		return aqiPollutionIndex;
	}
	public Double getHumidity() {
		return humidity;
	}
	
	

}
