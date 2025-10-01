import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Activity3Test {
	static WeatherBureau accuBradley;
	static WeatherStation[] stations;
	static ArrayList<WeatherStation> stationsAsList;
	static ArrayList<WeatherStation> waStations;
	static ArrayList<WeatherStation> riStations;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		accuBradley = new WeatherBureau();
		stations = accuBradley.getAllStationsArray();
		stationsAsList = accuBradley.getAllStationsList();
		waStations = accuBradley.getStationsInState("WA");
		riStations = accuBradley.getStationsInState("RI");

	}

	@Test
	void testWeatherStation1of4() {
		WeatherStation kSEA= new WeatherStation("Seattle, Seattle-Tacoma International Airport","KSEA","WA", 47.44472,122.31361); 
		assertEquals("KSEA",kSEA.getId());
	}
	@Test
	void testWeatherStation2of4() {
		WeatherStation kSMP= new WeatherStation("Stampede Pass","KSMP","WA", 47.427,121.418); 
		assertEquals("Stampede Pass",kSMP.getName());
	}
	@Test
	void testWeatherStation3of4() {
		WeatherStation kUUU= new WeatherStation("Newport, Newport State Airport","KUUU","RI", 41.53,71.28); 
		assertEquals(41.53,kUUU.getLatitude());
	}

	@Test
	void testWeatherStation4of4() {
		WeatherStation kUUU= new WeatherStation("Newport, Newport State Airport","KUUU","RI", 41.53,71.28); 
		assertEquals(71.28,kUUU.getLongitude());
	}
	@Test
	void testGetCurrentWeather() {
		WeatherStation kSEA= new WeatherStation("Seattle, Seattle-Tacoma International Airport","KSEA","WA", 47.44472,122.31361); 
		assertEquals("KSEA",kSEA.getCurrentWeather().getId());

	}

	// non-zero size
	@Test
	void testGetAllStationsArray1of4() {
		assertNotEquals(0,stations.length);
	}

	// More than 2600 stations
	@Test
	void testGetAllStationsArray2of4() {
		assertTrue(stations.length > 2600);
	}

	// no null elements
	@Test
	void testGetAllStationsArray3of4() {
		boolean isWorking = true;
		for (WeatherStation ws : stations) {
			if (ws == null) {
				isWorking = false;
			}
		}
		assertTrue(isWorking);
	}
	// contains KSEA
	@Test
	void testGetAllStationsArray4of4() {
		boolean isWorking = false;
		for (WeatherStation ws : stations) {
			if (ws.getId().equals("KSEA")) {
				isWorking = true;
			}
		}
		assertTrue(isWorking);
	}
	// non-zero size
	@Test
	void testGetAllStationsList1of4() {
		assertNotEquals(0,stationsAsList.size());
	}

	// More than 2600 stations
	@Test
	void testGetAllStationsList2of4() {
		assertTrue(stationsAsList.size() > 2600);
	}

	// no null elements
	@Test
	void testGetAllStationsList3of4() {
		boolean isWorking = true;
		for (WeatherStation ws : stationsAsList) {
			if (ws == null) {
				isWorking = false;
			}
		}
		assertTrue(isWorking);
	}
	// contains KSEA
	@Test
	void testGetAllStationsList4of4() {
		boolean isWorking = false;
		for (WeatherStation ws : stationsAsList) {
			if (ws.getId().equals("KSEA")) {
				isWorking = true;
			}
		}
		assertTrue(isWorking);
	}

	// not the same size as all stations
	@Test
	void testGetStationsInState1of3() {
		assertNotEquals(waStations.size(), stationsAsList.size());
	}

	// contains KSEA and KSMP
	@Test
	void testGetStationsInState2of3() {
		int count = 0;
		for (WeatherStation ws : waStations) {
			if (ws.getId().equals("KSEA") || ws.getId().equals("KSMP")) {
				count++;
			}
		}
		assertEquals(2, count);
	}
	
	// At least 40 station in WA
	@Test
	void testGetStationsInState3of3() {
		assertTrue(waStations.size() > 40);
	}

	@Test
	void testGetColdestInState() {
		//Use Rhode Island, so that it is faster
		String state = "RI";
		Observation ob = accuBradley.getColdestInState(state);
		boolean isWorking = false;
		for (WeatherStation station : riStations) {
			if (station.getId().equals(ob.getId())) {
				isWorking = true;
			}
		}
		assertTrue(isWorking);
	}

	@Test
	void testGetStationsInStateSortedByName1of2() {
		WeatherStation[] stationsCopy = accuBradley.getStationsInStateSortedByName("WA");
		boolean isWorking = true;
		for (int i = 1; i < stationsCopy.length;i++) {
			if (stationsCopy[i-1].getName().compareTo(stationsCopy[i].getName()) > 0) {
				isWorking = false;
			}
		}
		assertTrue(isWorking);
	}

	@Test
	void testGetStationsInStateSortedByName2of2() {
		WeatherStation[] stationsCopy = accuBradley.getStationsInStateSortedByName("WA");
		assertEquals(waStations.size(),stationsCopy.length);
	}
	@Test
	void testInsertionSort() {
		WeatherStation[] stationsCopy = makeCopy(stations);
		accuBradley.insertionSort(stationsCopy);
		boolean isWorking = true;
		for (int i = 1; i < stationsCopy.length;i++) {
			if (stationsCopy[i-1].getName().compareTo(stationsCopy[i].getName()) > 0) {
				isWorking = false;
			}
		}
		assertTrue(isWorking);
	}
	@Test
	void testGetStatesWithStations() {
		assertEquals(92, accuBradley.getStatesWithStations().size());
	}

	private WeatherStation[] makeCopy(WeatherStation[] stationsOrig) {
		WeatherStation[] stationsCopy = new WeatherStation[stationsOrig.length];
		for (int i = 0; i< stationsOrig.length;i++) {
			stationsCopy[i] = stationsOrig[i];
		}
		return stationsCopy;
	}
}
