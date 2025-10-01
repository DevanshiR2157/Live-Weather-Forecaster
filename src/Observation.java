import core.data.*;

public class Observation implements Comparable{
	//instance data
	private String id; 
	private String description;
	private double temp;
	private int windDir;
	private double windSpeed;
	private double pressure;
	private int humidity;
	private String iconURLBase;
	private String iconURLName;
	
	/**
	 * Constructs an Observation object with the specified parameters.
	 * toString will output a full observation, if this constructor is used.
	 * @param the weather station id
	 * @param description Short description of the current weather
	 * @param temp temperature
	 * @param windDir wind direction in degrees
	 * @param windSpeed wind speed in knots
	 * @param pressure barometric pressure in mb
	 * @param humidity relative humidity
	 */
	public Observation(String id, String description, double temp, int windDir, double windSpeed, double pressure, int humidity) {
		this.id = id;
		this.description = description;
		this.temp = temp;
		this.windDir = windDir;
		this.windSpeed = windSpeed;
		this.pressure = pressure;
		this.humidity = humidity;
	}

	/**
	 * Constructs an Observation object with the specified parameters.
	 * toString will output a full observation, if this constructor is used.
	 * @param the weather station id
	 * @param description Short description of the current weather
	 * @param temp temperature
	 * @param windDir wind direction in degrees
	 * @param windSpeed wind speed in knots
	 * @param pressure barometric pressure in mb
	 * @param humidity relative humidity
	 * @param iconURLBase the base URL, including the domain name for an icon depicting the weather
	 * @param iconURLName the icon name depicting the weather
	 */
	public Observation(String id, String description, double temp, int windDir, double windSpeed, double pressure, int humidity, String iconURLBase, String  iconURLName) {
		this(id, description, temp, windDir, windSpeed, pressure, humidity);
		this.iconURLBase = iconURLBase;
		this.iconURLName = iconURLName;
	}
	
	/**
	 * @return a String representing this observation
	 */
	public String toString() {
		String str = id + ": " + temp + " degrees; " + description + " (wind: " + windSpeed + " knots @ " + windDir + " degrees); barometric pressure: " + pressure + "; relativity humidity: " + humidity;
		return str;
		
	}
	
	/**
	 * Determines if the temperature of this observation is colder than other.
	 * @param other the other other observation
	 * @return true if this observation's temperature is colder than other; otherwise false.
	 */
	public boolean colderThan(Observation other) {
		if (this.temp < other.temp) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the Beaufort number on the Beaufort wind force scale.
	 * @return the Beaufot number for the current observation
	 * For more details, see https://en.wikipedia.org/wiki/Beaufort_scale
	 */
	public int getBeaufortNumber() {
		if (windSpeed < 1.0) {
			return 0;
		} else if (windSpeed >= 1 && windSpeed <= 3) {
			return 1;
		} else if (windSpeed >= 4 && windSpeed <= 6) {
			return 2;
		} else if (windSpeed >= 7 && windSpeed <= 10) {
			return 3;
		} else if (windSpeed >= 11 && windSpeed <= 16) {
			return 4;
		} else if (windSpeed >= 17 && windSpeed<= 21) {
			return 5; 
		} else if (windSpeed >= 22 && windSpeed <= 27) {
			return 6;
		} else if (windSpeed >= 28 && windSpeed <= 33) {
			return 7;
		} else if (windSpeed >= 34 && windSpeed <= 40) {
			return 8;
		} else if (windSpeed >= 41 && windSpeed <= 47) {
			return 9;
		} else if (windSpeed >= 48 && windSpeed <= 55) {
			return 10;
		} else if (windSpeed >= 56 && windSpeed <= 63) {
			return 11;
		} else {
			return 12;
		}
	}
	
	   /**
	    * Returns a string representation of the wind conditions. Summarize the
	    * Beaufort number into 4 categories; calm; breezy; wind flags out; storm 
	    * @return a string representation of the wind conditions
	    */
	   public String getWindConditions() {
		   int BeaufortNum = getBeaufortNumber();
		   if (BeaufortNum <= 2) {
			   return "Wind is calm";
		   } else if (BeaufortNum >= 3 && BeaufortNum <= 5) {
			   return "Nice breeze today";
		   } else if (BeaufortNum >= 6 && BeaufortNum <= 9) {
			   return "Wind flags are out";
		   } else {
			   return "Storm is coming";
		   }
	   }
		/**
		 * @return the wind Direction
		 */
		public int getWindDir() {
			return windDir;
		}

		/**
		 * @return a short description of the weather
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * @return the barometric pressure in mb
		 */
		public double getPressure() {
			return pressure;
		}

		/**
		 * @return the relative humidity
		 */
		public int getHumidity() {
			return humidity;
		}

		/**
		 * @return the wind speed in knots
		 */
		public double getWindSpeed() {
			return windSpeed;
		}

	/**
	 * Gets the weather station ID
	 * @return the weather station id
	 */
	public String getId() {
		return id; 
	}
	/**
	 * Gets the URL to the icon depicting the weather. 
	 * This is a combination of the baseURL and the icon filename
	 * @return A full URL to the icon depicting the weather. 
	 */
	public String getIconURL() {
		return iconURLBase + iconURLName;
	}


	/**
	 * @return the temp
	 */
	public double getTemp() {
		return temp;
	}
	/**
	 * Compares this object with the specified object for order.
	 * @return a value > 0 if this observation has a higher temperature than other;
	 *           value < 0 if this observation has a higher temperature than other;
	 *           value = 0 if this observation's temperature is equal to other.
	 * NOTE: Polymorphism is needed for this, so I didn't include this in the project spec.
	 */
	@Override
	public int compareTo(Object other) {
		if (other instanceof Observation) {
			Observation otherOb = (Observation) other;
			if (this.getTemp() == otherOb.getTemp()) {
				return 0;
			}
			else if (this.getTemp() > otherOb.getTemp()) {
				return 1;
			}
			else {
				return -1;
			}
		}
		return -1;
	}

}
