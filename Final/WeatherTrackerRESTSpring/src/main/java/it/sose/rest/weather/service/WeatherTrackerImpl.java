package it.sose.rest.weather.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class WeatherTrackerImpl implements WeatherTracker {

	private final int[][] weatherMatrix = new int[12][32];

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

		return "Value updated for day:"+today.toString();
	}
	
	@Override
	public int[] last7DaysValues() {
		
		int[] lastValues = new int[8];
		
		LocalDate today = LocalDate.now();
		
		for(int i=1; i<8; i++) {
			LocalDate date = today.minusDays(i);
			int new_month = date.getMonthValue();
			int new_day = date.getDayOfMonth();
			
			int val = weatherMatrix[new_month][new_day];
			lastValues[i] = val;
		}
		
		return lastValues;
	}
	
	
}
