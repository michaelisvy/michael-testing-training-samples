package assertion.testng;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.weather.WeatherReport;
import com.weather.WeatherService;
import com.weather.WeatherServiceImpl;

public class TestNGSoftAssertTest {
	
	private WeatherService weatherServiceImpl = new WeatherServiceImpl();
	
	private SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void shouldTestSoftAsserts() {
		
		WeatherReport report = this.weatherServiceImpl.findCurrentWeatherReport("singapore");
		softAssert.assertEquals(report.getTemperature(), 24.0); // error messages are not good: "null"
		softAssert.assertEquals(report.getHumidity(), 0.9);
		softAssert.assertAll();
		
	}

}
