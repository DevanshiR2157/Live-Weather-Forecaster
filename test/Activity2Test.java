import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

class Activity2Test {
	static WeatherBot botSEA;
	static Observation obSEAShort;
	static WeatherBot botBOS;
	static Observation obBOSLong;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		botSEA = new WeatherBot("KSEA");
		obSEAShort = botSEA.getObservation();
		botBOS = new WeatherBot("KBOS");
		obBOSLong = botBOS.getObservation();
	}

	@Test
	void testConstructor1of4() {
		Observation ob = new Observation("KSEA","Light Rain",38.0,170,11.0,997.6,89);
		assertEquals(38.0,ob.getTemp());
	}
	@Test
	void testConstructor2of4() {
		Observation ob = new Observation("KSEA","Light Rain",28.0,170,11.0,997.6,89);
		assertEquals(89,ob.getHumidity());
	}
	@Test
	void testConstructor3of4() {
		Observation ob = new Observation("KSEA","Partly Cloudy",28.0,170,11.0,997.6,89, "http://www.iconstore.com/", "cloudy.ico");
		assertEquals("http://www.iconstore.com/cloudy.ico",ob.getIconURL());
	}

	@Test
	void testConstructor4of4() {
		Observation ob = new Observation("KSEA","Light Rain",38.0,170,11.0,997.6,89, "http://www.iconstore.com/", "rain.ico");
		assertEquals("http://www.iconstore.com/rain.ico",ob.getIconURL());
	}


	@Test
	void testToString1of2() {
		Observation ob = new Observation("KSEA","Light Rain",38.0,170,10.0,997.6,89);
		assertEquals("KSEA: 38.0 degrees; Light Rain (wind: 10.0 knots @ 170 degrees); barometric pressure: 997.6; relativity humidity: 89",ob.toString());
	}
	@Test
	void testToString2of2() {
		Observation obUUU = new Observation("KUUU","Sailing Time",74.0,170,23.9,998.4,49);
		assertEquals("KUUU: 74.0 degrees; Sailing Time (wind: 23.9 knots @ 170 degrees); barometric pressure: 998.4; relativity humidity: 49",obUUU.toString());
	}
	// accessor methods
	@Test
	void testColderThan1of2() {
		Observation obSEA = new Observation("KSEA","Light Rain Fog/Mist",39.0,190,11.0,997.6,89);
		Observation obRNT = new Observation("KRNT","Light Rain",37.0,0,11.0,997.6,89);
		assertFalse(obSEA.colderThan(obRNT));
	}
	@Test
	void testColderThan2of2() {
		Observation obSEA = new Observation("KSEA","Light Rain Fog/Mist",37.0,190,11.0,998.4,86);
		Observation obSMP = new Observation("KSMP","Light snow",29.0,0,0,998.4,89);
		assertTrue(obSMP.colderThan(obSEA));
	}
	@Test
	void testGetId1of2() {
		Observation ob = new Observation("KS52","Light Rain",28.0,170,11.0,997.6,89);
		assertEquals("KS52",ob.getId());
	}

	@Test
	void testGetId2of2() {
		Observation ob = new Observation("KSMP","Light Rain",28.0,170,11.0,997.6,89);
		assertEquals("KSMP",ob.getId());
	}
	@Test
	void testGetTemp1of2() {
		Observation ob = new Observation("KSEA","Light Rain",28.0,170,11.0,997.6,89);
		assertEquals(28.0,ob.getTemp());

	}

	@Test
	void testGetTemp2of2() {
		Observation ob = new Observation("KSEA","Light Rain",14.0,170,11.0,997.6,89);
		assertEquals(14.0,ob.getTemp());

	}
	
	@Test
	void testGetDescription1of2() {
		Observation ob = new Observation("KSEA","Light Rain",14.0,170,11.0,997.6,89);
		assertEquals("Light Rain",ob.getDescription());
		
	}
	@Test
	void testGetDescription2of2() {
		Observation obSMP = new Observation("KSMP","Light snow",29.0,170,48,998.4,89);
		assertEquals("Light snow",obSMP.getDescription());
	}

	@Test
	void testGetHumidity1of2() {
		Observation ob = new Observation("KSEA","Light Rain",14.0,170,11.0,997.6,89);
		assertEquals(89,ob.getHumidity());
		
	}
	@Test
	void testGetHumidity2of2() {
		Observation obSMP = new Observation("KSMP","Light snow",29.0,170,48,998.4,49);
		assertEquals(49,obSMP.getHumidity());
	}
	@Test
	void testGetIconURL1of2() {
		Observation ob = new Observation("KSEA","Heavy Snow",28.0,170,11.0,997.6,89, "http://www.iconstore.com/", "snow.ico");
		assertEquals("http://www.iconstore.com/snow.ico",ob.getIconURL());
		
	}
	@Test
	void testGetIconURL2of2() {
		Observation ob = new Observation("KSEA","Partly Cloudy",28.0,170,11.0,997.6,89, "http://www.iconstore.com/", "cloudy.ico");
		assertEquals("http://www.iconstore.com/cloudy.ico",ob.getIconURL());
	}

	@Test
	void testGetPressure1of2() {
		Observation ob = new Observation("KSEA","Light Rain",14.0,170,11.0,997.6,89);
		assertEquals(997.6,ob.getPressure());
		
	}
	@Test
	void testGetPressure2of2() {
		Observation obSMP = new Observation("KSMP","Light snow",29.0,170,48,998.4,49);
		assertEquals(998.4,obSMP.getPressure());
	}
	@Test
	void testGetWindDir1of2() {
		Observation ob = new Observation("KSEA","Light Rain",14.0,170,11.0,997.6,89);
		assertEquals(170,ob.getWindDir());
		
	}
	@Test
	void testGetWindDir2of2() {
		Observation obSMP = new Observation("KSMP","Light snow",29.0,270,48,998.4,49);
		assertEquals(270,obSMP.getWindDir());
	}

	@Test
	void testGetWindSpeed1of2() {
		Observation ob = new Observation("KSEA","Light Rain",14.0,170,11.0,997.6,89);
		assertEquals(11.0,ob.getWindSpeed());
		
	}
	@Test
	void testGetWindSpeed2of2() {
		Observation obSMP = new Observation("KSMP","Light snow",29.0,270,48,998.4,49);
		assertEquals(48,obSMP.getWindSpeed());
	}

	@Test
	void testGetBeaufortNumber1of3() {
		Observation obSMP = new Observation("KSMP","Light snow",29.0,170,48,998.4,89);
		assertEquals(10,obSMP.getBeaufortNumber());
	}

	@Test
	void testGetBeaufortNumber2of3() {
		Observation obSMP = new Observation("KSMP","Light snow",29.0,170,10,998.4,89);
		assertEquals(3,obSMP.getBeaufortNumber());
	}
	@Test
	void testGetBeaufortNumber3of3() {
		Observation obSMP = new Observation("KSMP","Light snow",29.0,170,11.0,997.6,89);
		assertEquals(4,obSMP.getBeaufortNumber());
	}

	@Test
	void testGetWindConditions1of4() {
		//Hurricane IDA. 2021 strongest hurricane
		Observation obHUM = new Observation("KHUM","Hurricane approaching",79.0,170,129,929,89);
		assertEquals("Storm is coming",obHUM.getWindConditions());

	}

	@Test
	void testGetWindConditions2of4() {
		//Newport, Rhode Island
		Observation obUUU = new Observation("KUUU","Sailing Time",330,170,23.9,998.4,49);
		assertEquals("Wind flags are out",obUUU.getWindConditions());

	}
	@Test
	void testGetWindConditions3of4() {
		Observation obRNT = new Observation("KRNT","Light Rain",37.0,0,11.0,997.6,89);
		assertEquals("Nice breeze today",obRNT.getWindConditions());
	}
	@Test
	void testGetWindConditions4of4() {
		Observation obSEA = new Observation("KSEA","Light Rain Fog/Mist",37.0,190,11.0,998.4,86);
		assertEquals("Nice breeze today",obSEA.getWindConditions());
	}

	@Test
	void testgetObservation1of3() {
		// Boston is the windiest big city in the US.
		WeatherBot botBos2 = new WeatherBot("KBOS");
		Observation ob = botBos2.getObservation();
		assertNotEquals(0,ob.getBeaufortNumber());
	}
	@Test
	void testgetObservation2of3() {
		// Boston is the windiest big city in the US.
		WeatherBot botBos2 = new WeatherBot("KBOS");
		Observation ob = botBos2.getObservation();
		assertEquals("KBOS",ob.getId());
	}
	@Test
	void testgetObservation3of3() {
		// Boston is the windiest big city in the US.
		// International Falls, MN is typically the coldest in the US in winter
		Observation obIntFalls = new Observation("KINL","Overcast with Haze",-24.0, 330,11.0,997.6,89);
		assertTrue(obIntFalls.colderThan(obBOSLong));

	}}
