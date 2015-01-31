package mock.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.weather.WeatherController;
import com.weather.WeatherService;

public class MockAnnotationUnitTest {
	
	private WeatherController weatherController;
	
	@Mock
	private WeatherService mockedService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldFindCurrentWeatherReport() {
		when(mockedService.findCurrentTemperature("singapore")).thenReturn(30);
		weatherController = new WeatherController(mockedService);
		int temperature = weatherController.findCurrentTemperature("singapore");
		weatherController.findCurrentWeatherReport("");

		assertThat(temperature).isEqualTo(30);
		
	}

}
