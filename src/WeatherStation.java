import core.data.DataSource;

/*
 Represents information about a NWS weather station
*/

public class WeatherStation implements Comparable{
   
	private String name;
	private String id;
	private String state;
	private double lat;
	private double lng;
	
   /**
    * Constructor 
    * @param name The name of the station
    * @param id the id for the station
    * @param state the state in which this station is located
    * @param lat latitude of this station
    * @param lng longitude of this station
    */
   public WeatherStation(String name, String id, String state, double lat, double lng) {
	   Activity1.avoidSSLError();
	   this.name = name;
	   this.id = id; 
	   this.state = state;
	   this.lat = lat;
	   this.lng = lng;
   }
   
  /**
   * Gets the ID of the weather station
   * @return the ID of the weather station
   */
   public String getId() { 
	  Activity1.avoidSSLError();
      return id;
   }
   
   /**
    * Gets the name of this station
    * @return the name of this station
    */
   public String getName() {
	  Activity1.avoidSSLError();
      return name;
   }
   
   /**
    * Gets state in which this station is located
    * @returns the state in which this station is located
    */
   public String getState() {
	   Activity1.avoidSSLError();
	   return state;
   }
   /**
    * Gets latitude for this station
    * @returns the latitude for this station
    */
   public double getLatitude() {
	   Activity1.avoidSSLError();
	   return lat;
   }
   /**
    * Gets longitude for this station
    * @returns the longitude for this station
    */
   public double getLongitude() {
	   Activity1.avoidSSLError();
	   return lng;
   }

   /**
    * Returns the weather data for the current weather station
    * @return An Observation object representing the ata for the current weather station
    */
   public Observation getCurrentWeather() {
	   Activity1.avoidSSLError();
	   DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/" + id + ".xml"); 
	   ds.setCacheTimeout(15);  
	   ds.load();
	   
	   Observation ob = ds.fetch("Observation", "station_id", "weather", "temp_f", "wind_degrees", "wind_kt", "pressure_mb", "relative_humidity");
	   return ob;
   }
   
	/**
	 * Compares this object with the specified object for order.
	 * @return a value > 0 if this observation has a name later in the alphabet than other;
	 *           value < 0 if this observation has a name earlier in the alphabet than other;
	 *           value = 0 if this observation's name is equal to other.
	 * NOTE: Polymorphism is needed for this, so I didn't include this in the project spec.
	 */
	@Override
	public int compareTo(Object other) {
		if (other instanceof WeatherStation) {
			WeatherStation otherOb = (WeatherStation) other;
			return this.getName().compareTo(otherOb.getName());
			}
		return -1;
	}

   
}