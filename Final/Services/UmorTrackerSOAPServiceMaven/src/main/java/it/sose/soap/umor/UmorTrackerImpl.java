package it.sose.soap.umor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * Implementation of the Humor Tracker Service.
 * Tracks a daily humor value for every day of the year.
 * Data are persistent and are saved as serializable objects.
 */
public class UmorTrackerImpl implements UmorTracker, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/** 
	 * Matrix that represent all the humor values:
	 * - rows: months (0=january, ..., 11=dicember)
	 * - columns: days (0=1° day, ..., 30=31° day)
	 */
	private int[][] umorMatrix = new int[12][31];
	
	/**
	 * Path of file where save/load serialized data
	 */
	private static final String FILE_PATH = "/usr/local/tomcat/webapps/data/umor_tracker.ser";
	
	/**
	 * Constructor: if exist a file, load the data into the matrix
	 */
	public UmorTrackerImpl() {
        // Try to load saved data if any
        UmorTrackerImpl saved = deserialize();
        if (saved != null) {
            this.umorMatrix = saved.umorMatrix;
        }
    }

	
	/**
	 * First method of the interface, "print" that returns a text representation of the 
	 * humor data matrix.
	 * @return		Text representation of the data matrix
	 */
	@Override
	public String printUmorTracker() {
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
				int mood = umorMatrix[month][day];
				sb.append("  "+mood+"."); 
			}
		sb.append("\n");
		}
		return sb.toString();
	}
		
	/**
	 * Second method of the interface, "add" that adds the value for the corresponding humor for 
	 * current date. If the value is out of bounds, return an error message.
	 * Here's the list of possible values:
	 * - 1: angry
	 * - 2: sad
	 * - 3: tired
	 * - 4: ok
	 * - 5: happy
	 * - 6: energic
	 * @param mood The number corresponding to the today's humor.
	 * @return     A string with the current date if the operation was successful.
	 */
	@Override
	public String addUmorTracker(int mood) {
		// Controls if the number is in the range
		if (mood<1 || mood>6) {
			return "Insert number in range 1-6";
		}
		// Get the current date
		LocalDate today = LocalDate.now();
		int month = today.getMonthValue() - 1;
		int day = today.getDayOfMonth() - 1;
			
		// Save the value in the matrix
		umorMatrix[month][day] = mood;
		
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
			int val = umorMatrix[new_month][new_day];
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
	 * @return 		The deserialized instance of UmorTrackerImpl if exists, or null.	
	 */
    private UmorTrackerImpl deserialize() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (UmorTrackerImpl) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null; // primo avvio
        }
    }
	

}


