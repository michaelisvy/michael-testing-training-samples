package assertion.junit.assertj;



import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.util.List;

import org.junit.Test;

import com.weather.WeatherServiceImpl;

public class AssertJTest {
	private WeatherServiceImpl weatherServiceImpl;
	
	public AssertJTest() {
		weatherServiceImpl = new WeatherServiceImpl();
		// TODO: inject it using Spring
	}
	
	@Test
	public void shouldValidateCurrentTemperatureOfCity() {
		String city = "singapore";
		int temperature = this.weatherServiceImpl.findCurrentTemperature(city);
		assertThat(temperature).isEqualTo(30);
	}
	
	@Test
	public void shouldTestVariousAssertWaysToValidateCurrentTemperatureOfCity() {
		String city = "singapore";
		int temperature = this.weatherServiceImpl.findCurrentTemperature(city);
		assertThat(temperature).isEqualTo(30);
	}
	
	@Test(expected=IllegalStateException.class)
	public void shouldThrowExceptionForCityNotFound() {		
			String city = "cityDoesNotExist";
			this.weatherServiceImpl.findCurrentTemperature(city);
	}
	
	@Test
	public void shouldCompareFloatsByValidatingCurrentTemperatureOfCity() {
		String city = "singapore";
		double temperature = this.weatherServiceImpl.findCurrentTemperatureDecimal(city);
		assertThat(temperature).isEqualTo(25.3356);
		assertThat(temperature).isCloseTo(25.3356, within(0.0001));
		//still not that fluent (I need to know that second param is error)
		
	}
	
	@Test
	public void shouldVerifyThatArrayContainsSpecificValue() {
		String city = "singapore";
		Double[] temperatures = this.weatherServiceImpl.findDayTemperaturesArray(city);
		
		assertThat(temperatures).contains(25.0); // easy to check for one element
														// no method for just 2 elements out of 5
		
		assertThat(temperatures).contains(25.0, 28.0, 29.0); // ok for all elements in a table
		
		
	}
	
	@Test
	public void shouldVerifyThatCollectionContainsSpecificValue() {
		String city = "singapore";
		List<Double> temperaturesList = this.weatherServiceImpl.findDayTemperaturesList(city);
		
		assertThat(temperaturesList).contains(25.0, 28.0);	// I can specify some elements only
		assertThat(temperaturesList).contains(25.0, 28.0, 29.0); // I should specify all elements
	}

	
	
}
