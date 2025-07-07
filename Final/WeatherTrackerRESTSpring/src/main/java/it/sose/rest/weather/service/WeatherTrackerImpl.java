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

@Service
public class WeatherTrackerImpl implements WeatherTracker, Serializable{
	
	private static final long serialVersionUID = 1L;
	private int[][] weatherMatrix = new int[12][31];
	private static final String FILE_PATH = "data/weather_tracker.ser";
	
	public WeatherTrackerImpl() {
        // Prova a caricare i dati salvati
        WeatherTrackerImpl saved = deserialize();
        if (saved != null) {
            this.weatherMatrix = saved.weatherMatrix;
        }
    }
	
	@Override
	public String printWeatherTracker() {
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
				int mood = weatherMatrix[month][day];
				sb.append("  "+mood+"."); 
			}
		sb.append("\n");
		}
		return sb.toString();
	}
		
	@Override
	public String addWeatherTracker(int mood) {
		if (mood<1 || mood>6) {
			return "Insert number in range 1-6";
		}
		LocalDate today = LocalDate.now();
		int month = today.getMonthValue() - 1;
		int day = today.getDayOfMonth() - 1;
			
		weatherMatrix[month][day] = mood;
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
			
			int val = weatherMatrix[new_month][new_day];
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

    private WeatherTrackerImpl deserialize() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (WeatherTrackerImpl) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null; // primo avvio
        }
    }
	
}
