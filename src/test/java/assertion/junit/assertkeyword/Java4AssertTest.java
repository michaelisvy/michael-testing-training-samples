package assertion.junit.assertkeyword;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.weather.WeatherServiceImpl;

public class Java4AssertTest {
	private WeatherServiceImpl weatherServiceImpl;
	
	public Java4AssertTest() {
		weatherServiceImpl = new WeatherServiceImpl();
		// TODO: inject it using Spring
	}
	
	@Test
	public void shouldVerifyIntsByValidatingCurrentTemperatureOfCity() {
		String city = "singapore";
		int temperature = this.weatherServiceImpl.findCurrentTemperature(city);
		assertSame(temperature, 30);
	}
	
	@Test
	public void shouldVerifyFloatsByValidatingCurrentTemperatureOfCity() {
		String city = "singapore";
		double temperature = this.weatherServiceImpl.findCurrentTemperatureDecimal(city);
		assertEquals(temperature, 25.3357, 0.0001); //not fluent. How do I know that 3rd element is delta?
													// comparing 25.3357 versus 25.3356
	}
	
	@Test
	public void shouldVerifyThatArrayContainsSpecificValue() {
		String city = "singapore";
		Double[] temperatures = this.weatherServiceImpl.findDayTemperaturesArray(city);
		
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
		List<Double> temperatures = this.weatherServiceImpl.findDayTemperaturesList(city);
		
		Double expectedTemperature = 25.0;
		assertTrue(temperatures.contains(expectedTemperature));	// fluent enough but limited to one value
	}
	
	
	
	@Test(expected=IllegalStateException.class)
	public void shouldThrowExceptionForCityNotFound() {	
			String city = "cityDoesNotExist";
			this.weatherServiceImpl.findCurrentTemperature(city);
	}

	
	
}
