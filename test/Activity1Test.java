import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import core.data.DataSource;

class Activity1Test {

	@Test
	void testGetTempF1of2() {
		String station1 = "KSEA";
		double temp1 = Activity1.getTempF(station1);
		assertNotEquals(temp1,Integer.MIN_VALUE);
	}
	@Test
	void testGetTempF2of2() {
		String station2 = "KRNT";
		DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/" + station2 + ".xml"); 
		ds.setCacheTimeout(15 * 60);  
		ds.load();
		double expected = ds.fetchFloat("temp_f");
		double temp2 = Activity1.getTempF(station2);
		assertEquals(expected, temp2);
	}
	@Test
	void testGetConciseForecast1of3() {
		String station1 = "KSEA";
		String temp1 = Activity1.getConciseForecast(station1);
		assertNotEquals(null, temp1);
	}

	@Test
	void testGetConciseForecast2of3() {
		String station1 = "KSEA";
		String temp1 = Activity1.getConciseForecast(station1);
		DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/" + station1 + ".xml"); 
		ds.setCacheTimeout(15 * 60);  
		ds.load();
		double temp = ds.fetchDouble("temp_f");
		String loc = ds.fetchString("location");
		String expected =  "The temperature at " + loc + " is " + temp + "F";
		assertEquals(expected,temp1);
	}
	@Test
	void testGetConciseForecast3of3() {
		String station1 = "KRNT";
		String temp1 = Activity1.getConciseForecast(station1);
		DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/" + station1 + ".xml"); 
		ds.setCacheTimeout(15 * 60);  
		ds.load();
		double temp = ds.fetchDouble("temp_f");
		String loc = ds.fetchString("location");
		String expected =  "The temperature at " + loc + " is " + temp + "F";
		assertEquals(expected,temp1);
	}

}
