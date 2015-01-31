package mock.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.weather.WeatherService;
import com.weather.WeatherServiceImpl;

public class SpyMockTest {
	
	private WeatherService mock = mock(WeatherServiceImpl.class);
	private WeatherService spy = Mockito.spy(WeatherServiceImpl.class);
	
	@Test
	public void shouldGetZeroWhenMockNotInitialized() {
			
		// No when: No need to setup Mock object because it's ready
		
		// method not implemented. Return value is 0
		int mockTemperature = mock.findCurrentTemperature("singapore");
		
		assertThat(mockTemperature).isEqualTo(0);
		
		}
	
	@Test
	public void shouldCallRealMethodWhenSpyDoesNotOverrideBehaviour() {
					
		// No when: No need to setup Mock object because it's ready
		
		// method implemented and not redefined in Mockito. Method really called
		int spyTemperature = spy.findCurrentTemperature("singapore");
		
		assertThat(spyTemperature).isEqualTo(30);
		
	}
	
	
	@Test
	public void shouldCallUseStubMethodWhenOverriden() {
							
		// when method behaviour redefined in Mockito, real implementation not called anymore
		when(spy.findCurrentTemperature("singapore")).thenReturn(30);
		
		int spyTemperature = spy.findCurrentTemperature("singapore");
		
		assertThat(spyTemperature).isEqualTo(30);
	}	
	
	@Test
	public void shouldUseBddStyle() {
							
		// GIVEN
		given(spy.findCurrentTemperature("singapore")).willReturn(30);
		
		// WHEN
		int spyTemperature = spy.findCurrentTemperature("singapore");
		
		// THEN
		assertThat(spyTemperature).isEqualTo(30);
	}	

}
