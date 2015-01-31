package mock.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.weather.WeatherService;

public class VerifyTest {
	private WeatherService mock = mock(WeatherService.class);

	
	@Test
	public void shouldVerifyMethodHasBeenCalledTwice() throws Exception {
		
		// arrange
		when(mock.findCurrentTemperature("singapore")).thenReturn(30);
		
		// act
		int temperature = mock.findCurrentTemperature("singapore");
		temperature = mock.findCurrentTemperature("singapore");
		
		// assert
		verify(mock, times(2)).findCurrentTemperature("singapore"); 
		// by default, verifies that method has been called only one time. We have specified 2 times instead
		verify(mock, never()).findDayTemperaturesArray("");
		assertThat(temperature).isEqualTo(30);
		
		
	}

}
