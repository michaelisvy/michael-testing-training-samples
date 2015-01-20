package assertion.testng;


import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.weather.WeatherService;

public class TestNGAssertTest {
	private WeatherService weatherService;
	
	public TestNGAssertTest() {
		weatherService = new WeatherService();
		// TODO: inject it using Spring
	}
	
	@Test
	public void shouldVerifyIntsByValidatingCurrentTemperatureOfCity() {
		String city = "singapore";
		int temperature = this.weatherService.findCurrentTemperature(city);
		Assert.assertSame(temperature, 30); // API is not fluent
	}
	
	@Test
	public void shouldVerifyFloatsByValidatingCurrentTemperatureOfCity() {
		String city = "singapore";
		double temperature = this.weatherService.findCurrentTemperatureDecimal(city);
		Assert.assertEquals(temperature, 25.3357, 0.0001); //not fluent. How do I know that 3rd element is delta?
													// comparing 25.3357 versus 25.3356
	}
	
	@Test
	public void shouldVerifyThatArrayContainsSpecificValue() {
		String city = "singapore";
		Double[] temperatures = this.weatherService.findDayTemperaturesArray(city);
		
		Double expectedTemperature = 25.0;
		
		// TestNG has no method to test that an array contains an element
		for (int i = 0; i < temperatures.length; i++) {
			if(temperatures[i].equals(expectedTemperature)) {
				return;
			}
		}
		
		// should fail if expected value is not there
		Assert.fail("should have one temperature which value is  " + expectedTemperature);
		
	}
	
	@Test
	public void shouldVerifyThatCollectionContainsSpecificValue() {
		String city = "singapore";
		List<Double> temperatures = this.weatherService.findDayTemperaturesList(city);
		
		Double expectedTemperature = 25.0;
		Assert.assertTrue(temperatures.contains(expectedTemperature));	// fluent enough but limited to one value
	}
	
	
	
	@Test(expectedExceptions=IllegalStateException.class) // can declare exceptions on top
	public void shouldThrowExceptionForCityNotFound() {	
			String city = "cityDoesNotExist";
			this.weatherService.findCurrentTemperature(city);
	}

	
	
}
