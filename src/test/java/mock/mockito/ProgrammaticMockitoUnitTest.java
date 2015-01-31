package mock.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.weather.WeatherController;
import com.weather.WeatherService;

public class ProgrammaticMockitoUnitTest {
	
	private WeatherController weatherController;
	
	@Test
	public void shouldFindCurrentWeatherReport() {
		// makes lots of sense if WeatherService is a Web Service
		WeatherService mockedService = mock(WeatherService.class);
		when(mockedService.findCurrentTemperature("singapore")).thenReturn(30);
		weatherController = new WeatherController(mockedService);
		int temperature = weatherController.findCurrentTemperature("singapore");
		weatherController.findCurrentWeatherReport(""); // Additional method call. Mockito still ok with that
		
		verify(mockedService).findCurrentTemperature("singapore");
		assertThat(temperature).isEqualTo(30);
		
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldNotFindCityBecauseNameDoesNotExist() {
		WeatherService mockedService = mock(WeatherService.class);
		when(mockedService.findCurrentTemperature("Penang")).thenThrow(new IllegalStateException(""));
		// I purposely decided to return RuntimeExcetion instead of IllegalStateException because I want to be able to 
		// refactor my test easily if needed. Exact type of exception is not mandatory as long as it is a RuntimeException
		WeatherController controller = new WeatherController(mockedService);
		controller.findCurrentTemperature("Penang");	
	}
	
	

}
