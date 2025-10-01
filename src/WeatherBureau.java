/*
 * Arrays and ArrayLists of objects
 */

import core.data.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WeatherBureau {
	WeatherStation[] stations;
	
	/**
	 * Constructor that initializes stations by connecting, loading
	 * and fetching the weather stations serviced by the National 
	 * Weather Service
	 */
	public WeatherBureau() {
		Activity1.avoidSSLError();
		DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/index.xml");
		ds.load();
		stations = ds.fetchArray("WeatherStation", "station/station_name", "station/station_id", "station/state", "station/latitude", "station/longitude");

	}
	
	/**
	 * Gets all the weather stations as an array
	 * @return he weather stations as an array
	 */

	public WeatherStation[] getAllStationsArray() {
		Activity1.avoidSSLError();
		return stations;
	}
	
	/**
	 * Gets all the weather stations as an ArrayList
	 * @return he weather stations as an ArrayList
	 */
	public ArrayList<WeatherStation> getAllStationsList(){
		Activity1.avoidSSLError();
		DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/index.xml");
		ds.load();
		ArrayList<WeatherStation> allStations = ds.fetchList("WeatherStation", "station/station_name", "station/station_id", "station/state", "station/latitude", "station/longitude");
		return allStations;
	}
	
	/**
	 * Gets the list of weather stations in the specified state
	 * @param state the state to filter with
	 * @return the list of weather stations in the specified state
	 */

	public ArrayList<WeatherStation> getStationsInState(String state){
		ArrayList<WeatherStation> allStnsInState = new ArrayList<WeatherStation>();
		
		for (WeatherStation i : stations) {
			if ((i.getState()).equals(state)) {
				allStnsInState.add(i);				
			}
		}

		return allStnsInState;
	}

	/**
	 * Returns an ArrayList of all states and provinces that
	 * have a National Weather Service weather station
	 * @return the states and provinces with NWS weather stations
	 */
	public ArrayList<String> getStatesWithStations(){
		ArrayList <String> statesWStns = new ArrayList <String>();
		for (WeatherStation i : stations ) {
			if(statesWStns.contains(i.getState()) == false) {
				statesWStns.add(i.getState());
			}
		}
		return statesWStns;
	}

	/**
	 * Returns a Weather Station object given it's station ID
	 * @param stationLookingFor the station ID
	 * @return a Weather Station object given it's station ID; otherwise null
	 */
	public WeatherStation getStation(String stationLookingFor) {
		for (WeatherStation i : stations ) {
			if(i.getId().equals(stationLookingFor)) {
				return i;
			} 
		}
		return null;
	}

	/**
	 * Finds the Weather Station in the specified state with the coldest temperature.
	 * @param state the state
	 * @return An Observation for the weather station with the coldest temperature
	 */
	public Observation getColdestInState(String state) {
		ArrayList<WeatherStation> stationsInState = new ArrayList<WeatherStation>();
		stationsInState = getStationsInState(state);
		// Traverse all weather stations in a state
		double lowestTemp = Integer.MAX_VALUE;
		Observation lowestTempStation = stationsInState.get(0).getCurrentWeather();
		
			// Adding a try catch, because sometimes a weather station is offline.
			try {
				// add code to get the weather for a station
				Activity1.avoidSSLError();
				DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/" + lowestTempStation.getId() + ".xml"); 
				ds.setCacheTimeout(15);  
				ds.load();
				
				Observation ob = ds.fetch("Observation", "station_id", "weather", "temp_f", "wind_degrees", "wind_kt", "pressure_mb", "relative_humidity");
				return ob;
			}catch(Exception e) {
				System.out.println("Error retrieving observation data for station" );
				for (WeatherStation i : stationsInState) {
					Observation currWeather = i.getCurrentWeather();
					if (currWeather.getTemp() < lowestTemp) {
						lowestTemp = currWeather.getTemp();
						lowestTempStation = currWeather;
					}
				}
			}

		
		return lowestTempStation;   
	}
	  
	/**
	 * Gets a list of all weather stations in a state sorted by their name.
	 * @param state the state
	 * @return list of all weather stations in a state sorted by their name
	 */

	public WeatherStation[] getStationsInStateSortedByName(String state) {
		ArrayList<WeatherStation> listOfStations = getStationsInState(state);
		WeatherStation[] arrOfStations = new WeatherStation[listOfStations.size()];
		for(int i = 0; i < listOfStations.size(); i++) {
			arrOfStations[i] = listOfStations.get(i);
		}
		insertionSort(arrOfStations);
		return arrOfStations;
	}
	
	/**
	 * Sorts the array of WeatherStation using the Insertion Sort algorithm
	 * @param arr the array of WeatherStation
	 */
	public void insertionSort(WeatherStation[] arr) {
		WeatherStation newValue = arr[0];
		for(int i = 1; i < arr.length; i++) {
			newValue = arr[i];
			int sortedVal = i;
			while ((sortedVal > 0) && (arr[sortedVal-1].getName().compareTo(arr[sortedVal].getName())>0)) {
				arr[sortedVal - 1] =  arr[sortedVal];
				sortedVal--;
			}
			arr[sortedVal] = newValue;
		}
	}
	
	public static void main(String[] args) {
	   WeatherBureau bureau = new WeatherBureau();
//	   WeatherStation[] stations = bureau.getAllStationsArray();
//	   for (WeatherStation ws : stations) {
//		   System.out.println("  " + ws.getId() + ": " + ws.getName());
//	   }
//	   System.out.println("Total number of stations: " + stations.length);
//	   
//	   System.out.println();
	   
	   System.out.println("Getting weather stations in Washington");
	   ArrayList<WeatherStation> waStations = bureau.getStationsInState("WA");
	   for (WeatherStation ws : waStations) {
		   System.out.println("  " + ws.getId() + ": " + ws.getName());
	   }
	   System.out.println("Total number of stations: " + waStations.size());
	   
	   System.out.println();
	   System.out.println("Getting coldest station in Washington");
	   Observation ob = bureau.getColdestInState("WA");
	   System.out.println("Coldest station is - " + ob);
	   System.out.println(ob);
	   
	   System.out.println();
	   System.out.println("Sorting stations in Washington");
	   WeatherStation[] filteredStations = bureau.getStationsInStateSortedByName("WA");
	   for (WeatherStation ws : filteredStations) {
		   System.out.println("  " + ws.getId() + ": " + ws.getName());
	   }


   }
}