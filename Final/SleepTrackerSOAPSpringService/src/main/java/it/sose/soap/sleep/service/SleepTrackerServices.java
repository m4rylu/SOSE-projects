package it.sose.soap.sleep.service;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class SleepTrackerServices {
		
	private final int[][] sleepMatrix = new int[12][32];

	public String printSleepTracker() {
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
				int mood = sleepMatrix[month][day];
				sb.append("  "+mood+"."); 
			}
		sb.append("\n");
		}
		return sb.toString();
	}
		
	public String addSleepTracker(int mood) {
		if (mood<1 || mood>5) {
			return "Insert number in range 1-5";
		}
		LocalDate today = LocalDate.now();
		int month = today.getMonthValue() - 1;
		int day = today.getDayOfMonth() - 1;
			
		sleepMatrix[month][day] = mood;

		return "Value updated for day:"+today.toString();
	}
	
	
	public String last7DaysValues() {
		
		int[] lastValues = new int[8];
		
		LocalDate today = LocalDate.now();
		
		for(int i=1; i<8; i++) {
			LocalDate date = today.minusDays(i);
			int new_month = date.getMonthValue();
			int new_day = date.getDayOfMonth();
			
			int val = sleepMatrix[new_month][new_day];
			lastValues[i] = val;
		}
		
		return Arrays.toString(lastValues);
	}
	

	
	
	
}

