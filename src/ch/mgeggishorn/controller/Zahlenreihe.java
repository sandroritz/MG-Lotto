package ch.mgeggishorn.controller;

import java.security.AllPermission;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Zahlenreihe {

	private static final int minZahl = 1;
	private static final int maxZahl = 4999;
	private static final int maxSaalZahl = 1200;
	private static final int startwert = 1; //Muss immer gleich bleiben! Sons gibts andere Zufallszahlen

	private List<Integer> createAllNumberList() {
		List<Integer> allNumberList = new ArrayList<Integer>();

		for (int i = 1; i <= maxZahl; i++) {
			allNumberList.add(i);
		}
		return allNumberList;

	}
	
	private List<Integer> createSaalNumberList(){
		List<Integer> saalNumberList = new ArrayList<Integer>();
		
		Random rand = new Random(startwert);
		
		for (int i = 1; i <= maxSaalZahl; i++) {
			saalNumberList.add(rand.nextInt(maxZahl - minZahl) + minZahl);
		}
		
		return saalNumberList;
	}
	
	public void printAllNumber(){
		List<Integer> allNumberList = createAllNumberList();
		for (Integer zahl : allNumberList) {
			System.out.println("All Number List: " + zahl);
		}
	}
	
	public void printSaalNumber(){
		List<Integer> saalNumberList = createSaalNumberList();
		for (int i = 1; i <= maxSaalZahl; i++) {
			System.out.println("All Saal List: Zahl:" + i + " Zufallszahl: " + saalNumberList.get(i-1));
		}
	}

}
