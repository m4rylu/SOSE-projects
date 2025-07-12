package it.sose.rest.weather.service;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * Implementation of the Weather Tracker Service.
 * Tracks a daily weather value for every day of the year.
 * Data are persistent and are saved as serializable objects.
 */

@Service
public class WeatherTrackerImpl implements WeatherTracker, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 
	 * Matrix that represent all the weather values:
	 * - rows: months (0=january, ..., 11=dicember)
	 * - columns: days (0=1° day, ..., 30=31° day)
	 */
	private int[][] weatherMatrix = new int[12][31];
	
	/**
	 * Path of file where save/load serialized data
	 */
	private static final String FILE_PATH = "data/weather_tracker.ser";
	
	/**
	 * Constructor: if exist a file, load the data into the matrix
	 */
	public WeatherTrackerImpl() {
        // Prova a caricare i dati salvati
        WeatherTrackerImpl saved = deserialize();
        if (saved != null) {
            this.weatherMatrix = saved.weatherMatrix;
        }
    }
	
	/**
	 * First method of the interface, "print" that returns a text representation of the 
	 * weather data matrix.
	 * @return		Text representation of the data matrix
	 */
	@Override
	public String printWeatherTracker() {
		StringBuilder sb = new StringBuilder();
		// Header: print the number of days (1-31)
		sb.append("Giorno:");
		for (int day=1; day<32; day++) {
			if (day<=9) {
				sb.append("  0"+day);
			} else {
				sb.append("  "+day);
			}
		}
		sb.append("\n");
		
		// Print the values for every month in the corresponding row
		for (int month=0; month < 12; month++) {
			if (month+1<=9) {
				sb.append("Mese 0"+(month+1)+":");
			} else {
				sb.append("Mese " + (month+1) + ":");
			}
			for (int day=0; day<31; day++) {
				int mood = weatherMatrix[month][day];
				sb.append("  "+mood+"."); 
			}
		sb.append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * Second method of the interface, "add" that adds the value for the corresponding weather for 
	 * current date. If the value is out of bounds, return an error message.
	 * Here's the list of possible values:
	 * - 1: sunny
	 * - 2: fine
	 * - 3: cloudy
	 * - 4: rainy
	 * - 5: stormy
	 * - 6: windy
	 * - 7: icy
	 * @param weather The number corresponding to the today's weather.
	 * @return     A string with the current date if the operation was successful.
	 */
	@Override
	public String addWeatherTracker(int weather) {
		// Controls if the number is in the range
		if (weather<1 || weather>7) {
			return "Insert number in range 1-7";
		}
		
		// Get the current date
		LocalDate today = LocalDate.now();
		int month = today.getMonthValue() - 1;
		int day = today.getDayOfMonth() - 1;
		
		// Save the value in the matrix.
		weatherMatrix[month][day] = weather;
		
		// Serialize the object in the file
		serialize();

		return "Value updated for day:"+today.toString();
	}
	
	/**
	 * Third method of the interface, "lastValues" that prints the last 7 values from yesterday
	 * saved in the tracker.
	 * @return     A string with the last 7 days values from yesterday.
	 */
	@Override
	public String last7DaysValues() {
		// Create an int array
		int[] lastValues = new int[7];
		
		// Get the current day.
		LocalDate today = LocalDate.now();
		
		// Loop calculating past dates
		for(int i=1; i<8; i++) {
			LocalDate date = today.minusDays(i);
			int new_month = date.getMonthValue() - 1;
			int new_day = date.getDayOfMonth() - 1;
			
			// Recover the value of the date from matrix
			int val = weatherMatrix[new_month][new_day];
			lastValues[i] = val;
		}
		
		// Convert int array to string 
		return Arrays.toString(lastValues);
	}
	
	/**
	 * Save the current object on file using Java serialization
	 */
	private void serialize() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Try to load an object saved on file if exists.
	 * @return 		The deserialized instance of WheatherTrackerImpl if exists, or null if not available.	
	 */
    private WeatherTrackerImpl deserialize() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (WeatherTrackerImpl) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
	
}
