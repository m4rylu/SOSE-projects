package it.sose.soap.umor;

import java.time.LocalDate;

public class UmorTrackerImpl implements UmorTracker {
	
	private final int[][] umorMatrix = new int[12][32];

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
			
			int val = umorMatrix[new_month][new_day];
			lastValues[i] = val;
		}
		
		return lastValues;
	}
	

}
