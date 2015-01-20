package assertion.testng;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.weather.WeatherReport;
import com.weather.WeatherService;

public class TestNGSoftAssertTest {
	
	private WeatherService weatherService = new WeatherService();
	
	private SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void shouldTestSoftAsserts() {
		
		WeatherReport report = this.weatherService.findCurrentWeatherReport("singapore");
		softAssert.assertEquals(report.getTemperature(), 24.0); // error messages are not good: "null"
		softAssert.assertEquals(report.getHumidity(), 0.9);
		softAssert.assertAll();
		
	}

}
