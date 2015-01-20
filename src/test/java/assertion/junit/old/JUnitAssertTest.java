package assertion.junit.old;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.weather.WeatherService;

public class JUnitAssertTest {
	private WeatherService weatherService;
	
	public JUnitAssertTest() {
		weatherService = new WeatherService();
		// TODO: inject it using Spring
	}
	
	@Test
	public void shouldVerifyIntsByValidatingCurrentTemperatureOfCity() {
		String city = "singapore";
		int temperature = this.weatherService.findCurrentTemperature(city);
		assertSame(temperature, 30);
	}
	
	@Test
	public void shouldVerifyFloatsByValidatingCurrentTemperatureOfCity() {
		String city = "singapore";
		double temperature = this.weatherService.findCurrentTemperatureDecimal(city);
		assertEquals(temperature, 25.3357, 0.0001); //not fluent. How do I know that 3rd element is delta?
													// comparing 25.3357 versus 25.3356
	}
	
	@Test
	public void shouldVerifyThatArrayContainsSpecificValue() {
		String city = "singapore";
		Double[] temperatures = this.weatherService.findDayTemperaturesArray(city);
		
		Double expectedTemperature = 25.0;
		
		// table should contain at least one value that matches expected temperature
		for (int i = 0; i < temperatures.length; i++) {
			if(temperatures[i].equals(expectedTemperature)) {
				return;
			}
		}
		
		// should fail if expected value is not there
		fail("should have one temperature which value is  " + expectedTemperature);
		
	}
	
	@Test
	public void shouldVerifyThatCollectionContainsSpecificValue() {
		String city = "singapore";
		List<Double> temperatures = this.weatherService.findDayTemperaturesList(city);
		
		Double expectedTemperature = 25.0;
		assertTrue(temperatures.contains(expectedTemperature));	// fluent enough but limited to one value
	}
	
	
	
	@Test(expected=IllegalStateException.class)
	public void shouldThrowExceptionForCityNotFound() {	
			String city = "cityDoesNotExist";
			this.weatherService.findCurrentTemperature(city);
	}

	
	
}
