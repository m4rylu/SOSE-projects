package it.sose.soap.umor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

public class UmorTrackerImpl implements UmorTracker, Serializable {
	
	private static final long serialVersionUID = 1L;
	private int[][] umorMatrix = new int[12][31];
	private static final String FILE_PATH = "/usr/local/tomcat/webapps/data/umor_tracker.ser";
	
	public UmorTrackerImpl() {
        // Prova a caricare i dati salvati
        UmorTrackerImpl saved = deserialize();
        if (saved != null) {
            this.umorMatrix = saved.umorMatrix;
        }
    }

	@Override
	public String printUmorTracker() {
		StringBuilder sb = new StringBuilder();
		sb.append("Giorno:");
		for (int day=1; day<32; day++) {
			if (day<=9) {
				sb.append("  0"+day);
			} else {
				sb.append("  "+day);
			}
		}
		sb.append("\n");
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
		
	@Override
	public String addUmorTracker(int mood) {
		if (mood<1 || mood>5) {
			return "Insert number in range 1-5";
		}
		LocalDate today = LocalDate.now();
		int month = today.getMonthValue() - 1;
		int day = today.getDayOfMonth() - 1;
			
		umorMatrix[month][day] = mood;
		serialize();

		return "Value updated for day:"+today.toString();
	}
	
	@Override
	public String last7DaysValues() {
		
		int[] lastValues = new int[8];
		
		LocalDate today = LocalDate.now();
		
		for(int i=1; i<8; i++) {
			LocalDate date = today.minusDays(i);
			int new_month = date.getMonthValue() - 1;
			int new_day = date.getDayOfMonth() - 1;
			
			int val = umorMatrix[new_month][new_day];
			lastValues[i] = val;
		}
		
		return Arrays.toString(lastValues);
	}
	
	private void serialize() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private UmorTrackerImpl deserialize() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (UmorTrackerImpl) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null; // primo avvio
        }
    }
	

}


