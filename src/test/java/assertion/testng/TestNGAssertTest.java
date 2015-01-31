package assertion.testng;


import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.weather.WeatherServiceImpl;

public class TestNGAssertTest {
	private WeatherServiceImpl weatherServiceImpl;
	
	public TestNGAssertTest() {
		weatherServiceImpl = new WeatherServiceImpl();
		// TODO: inject it using Spring
	}
	
	@Test
	public void shouldVerifyIntsByValidatingCurrentTemperatureOfCity() {
		String city = "singapore";
		int temperature = this.weatherServiceImpl.findCurrentTemperature(city);
		Assert.assertSame(temperature, 30); // API is not fluent
	}
	
	@Test
	public void shouldVerifyFloatsByValidatingCurrentTemperatureOfCity() {
		String city = "singapore";
		double temperature = this.weatherServiceImpl.findCurrentTemperatureDecimal(city);
		Assert.assertEquals(temperature, 25.3357, 0.0001); //not fluent. How do I know that 3rd element is delta?
													// comparing 25.3357 versus 25.3356
	}
	
	@Test
	public void shouldVerifyThatArrayContainsSpecificValue() {
		String city = "singapore";
		Double[] temperatures = this.weatherServiceImpl.findDayTemperaturesArray(city);
		
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
		List<Double> temperatures = this.weatherServiceImpl.findDayTemperaturesList(city);
		
		Double expectedTemperature = 25.0;
		Assert.assertTrue(temperatures.contains(expectedTemperature));	// fluent enough but limited to one value
	}
	
	
	
	@Test(expectedExceptions=IllegalStateException.class) // can declare exceptions on top
	public void shouldThrowExceptionForCityNotFound() {	
			String city = "cityDoesNotExist";
			this.weatherServiceImpl.findCurrentTemperature(city);
	}

	
	
}
