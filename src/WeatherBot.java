import core.data.*;

public class WeatherBot {
	private String stationID;
   
   /**
    * Constructs a WeatherBot object with the specified weather station id
    * @param stationID the weather station id
    */
   public WeatherBot(String stationID) {
	   Activity1.avoidSSLError();
	   this.stationID = stationID;
   }
   
   
   /**
    * Gets an Observation object with the station id, short weather description, temperature,
    * wind direction, wind speed in knots, barometric pressure in mb, and the relative humidity.
    * @return an Observation object
    */
   public Observation getObservation() {
	   Activity1.avoidSSLError();
	   DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/" + stationID + ".xml"); 
	   ds.setCacheTimeout(15);  
	   ds.load();
	   
	   Observation ob = ds.fetch("Observation", "station_id", "weather", "temp_f", "wind_degrees", "wind_kt", "pressure_mb", "relative_humidity");
	   return ob;

   }
   
   public static void main(String[] args) {
	   
	   WeatherBot bot = new WeatherBot("KSEA");
	   WeatherBot bot2 = new WeatherBot("KRNT");
	   Observation ob1 = bot.getObservation();
	   Observation ob2 = bot2.getObservation();
	   System.out.println(ob1.toString());
	   System.out.println(ob2.toString());
	   
	   WeatherBot bot3 = new WeatherBot("KSMP");
	   Observation ob3 = bot2.getObservation();
	   System.out.println(ob3.toString());
	   
	   if (ob2.colderThan(ob3)) {
		   System.out.println("It is colder at " +ob2.getId() + " than "  + ob3.getId());
	   }
	   else {
		   System.out.println("It is warmer at " +ob2.getId() + " than "  + ob3.getId());
	   }
	   
	   System.out.println(ob2.getId() +": " + ob2.getWindConditions());
	   System.out.println(ob3.getId() +": " + ob3.getWindConditions());
   }   


}



