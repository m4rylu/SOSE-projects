package it.sose.rest.rate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;

/**
 * Implementation of the Rate My Day Tracker Service.
 * Tracks a daily rate of day value for every day of the year.
 * Data are persistent and are saved as serializable objects.
 */
@Path("/")
public class RateMyDayTrackerImpl implements RateMyDayTracker, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 
	 * Matrix that represent all the rate values:
	 * - rows: months (0=january, ..., 11=dicember)
	 * - columns: days (0=1° day, ..., 30=31° day)
	 */
	private int[][] rateMatrix = new int[12][31];
	
	/**
	 * Path of directory where save/load serialized data
	 */
	private static final String FILE_PATH = "/usr/local/tomcat/webapps/data/rate_tracker.ser";
	
	
    // Inject the current HTTP request to retrieve client IP
    @Context
    private HttpServletRequest request;
    
	/**
	 * Constructor: if exist a file, load the data into the matrix
	 */
	public RateMyDayTrackerImpl() {
        // Try to load saved data if any
        RateMyDayTrackerImpl saved = deserialize();
        if (saved != null) {
            this.rateMatrix = saved.rateMatrix;
        }
    }

	/**
	 * First method of the interface, "print" that returns a text representation of the 
	 * rate data matrix.
	 * @return		Text representation of the data matrix
	 */
	@Override
	public String printRateMyDayTracker() {
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
				int mood = rateMatrix[month][day];
				sb.append("  "+mood+"."); 
			}
		sb.append("\n");
		}
		return sb.toString();
	}
		
	/**
	 * Second method of the interface, "add" that adds the value for the corresponding rate for 
	 * current date. If the value is out of bounds, return an error message.
	 * Here's the list of possible values:
	 * - 1: 1 star
	 * - 2: 2 star
	 * - 3: 3 star
	 * - 4: 4 star
	 * - 5: 5 star
	 * @param mood The number corresponding to the today's rate.
	 * @return     A string with the current date if the operation was successful.
	 */
	@Override
	public String addRateMyDayTracker(int mood) {
		// Controls if the number is in the range
		if (mood<1 || mood>5) {
			return "Insert number in range 1-5";
		}
		
		// Get the current date
		LocalDate today = LocalDate.now();
		int month = today.getMonthValue() - 1;
		int day = today.getDayOfMonth() - 1;
			
		// Save the value in the matrix
		rateMatrix[month][day] = mood;
		
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
		int[] lastValues = new int[8];
		
		// Get the current day.
		LocalDate today = LocalDate.now();
		
		// Loop calculating past dates
		for(int i=1; i<8; i++) {
			LocalDate date = today.minusDays(i);
			int new_month = date.getMonthValue() - 1;
			int new_day = date.getDayOfMonth() - 1;
			
			// Recover the value of the date from matrix
			int val = rateMatrix[new_month][new_day];
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
	 * @return 		The deserialized instance of RateMyDayTrackerImpl if exists, or null.	
	 */
    private RateMyDayTrackerImpl deserialize() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (RateMyDayTrackerImpl) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

}

