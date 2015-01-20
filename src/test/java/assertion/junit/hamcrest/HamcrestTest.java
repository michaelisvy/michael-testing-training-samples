package assertion.junit.hamcrest;

import static org.hamcrest.MatcherAssert.assertThat; //only one assert method: assertThat
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is; //Eclipse shows as deprecated but it's fine
import static org.hamcrest.number.IsCloseTo.closeTo;

import java.util.List;

import org.junit.Test;

import com.weather.WeatherService;

public class HamcrestTest {
	private WeatherService weatherService;
	
	public HamcrestTest() {
		weatherService = new WeatherService();
		// TODO: inject it using Spring
	}
	
	@Test
	public void shouldValidateCurrentTemperatureOfCity() {
		String city = "singapore";
		int temperature = this.weatherService.findCurrentTemperature(city);
		assertThat(temperature, equalTo(30));
	}
	
	@Test
	public void shouldTestVariousAssertWaysToValidateCurrentTemperatureOfCity() {
		String city = "singapore";
		int temperature = this.weatherService.findCurrentTemperature(city);
		assertThat(temperature, equalTo(30));
		assertThat(temperature, is(30));
		assertThat(temperature, is(equalTo(30)));
	}
	
	@Test(expected=IllegalStateException.class)
	public void shouldThrowExceptionForCityNotFound() {		
			String city = "cityDoesNotExist";
			this.weatherService.findCurrentTemperature(city);
	}
	
	@Test
	public void shouldCompareFloatsByValidatingCurrentTemperatureOfCity() {
		String city = "singapore";
		double temperature = this.weatherService.findCurrentTemperatureDecimal(city);
		assertThat(temperature, equalTo(25.3356)); 
		assertThat(temperature, closeTo(25.3356, 0.0001));
		//still not that fluent (I need to know that second param is error)
		
	}
	
	@Test
	public void shouldVerifyThatArrayContainsSpecificValue() {
		String city = "singapore";
		Double[] temperatures = this.weatherService.findDayTemperaturesArray(city);
		
		assertThat(temperatures, hasItemInArray(25.0)); // easy to check for one element
														// no method for just 2 elements out of 5
		
		assertThat(temperatures,arrayContaining(25.0, 28.0, 29.0)); // ok for all elements in a table
		
		
	}
	
	@Test
	public void shouldVerifyThatCollectionContainsSpecificValue() {
		String city = "singapore";
		List<Double> temperaturesList = this.weatherService.findDayTemperaturesList(city);
		
		assertThat(temperaturesList,hasItems(25.0, 28.0));	// I can specify some elements only
		assertThat(temperaturesList,contains(25.0, 28.0, 29.0)); // I should specify all elements
	}

	
	
}
