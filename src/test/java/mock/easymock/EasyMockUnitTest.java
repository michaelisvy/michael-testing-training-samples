package mock.easymock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;

import org.easymock.EasyMock;
import org.junit.Test;

import com.weather.WeatherController;
import com.weather.WeatherService;

public class EasyMockUnitTest {
	
	private WeatherController weatherController;
	
	@Test
	public void shouldFindCurrentWeatherReport() {
		// makes lots of sense if WeatherService is a Web Service
		WeatherService mockedService = createMock(WeatherService.class);
		expect(mockedService.findCurrentTemperature("singapore")).andReturn(30);
		weatherController = new WeatherController(mockedService);
		EasyMock.replay(mockedService);
		int temperature = weatherController.findCurrentTemperature("singapore");
		// weatherController.findCurrentWeatherReport("");
		assertThat(temperature).isEqualTo(30);
		
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldNotFindCityBecauseNameDoesNotExist() {
		WeatherService mockedService = createMock(WeatherService.class);
		expect(mockedService.findCurrentTemperature("Penang")).andThrow(new IllegalStateException(""));
		// I purposely decided to return RuntimeExcetion instead of IllegalStateException because I want to be able to 
		// refactor my test easily if needed. Exact type of exception is not mandatory as long as it is a RuntimeException
		WeatherController controller = new WeatherController(mockedService);
		EasyMock.replay(mockedService);
		controller.findCurrentTemperature("Penang");	
	}
	
	

}
