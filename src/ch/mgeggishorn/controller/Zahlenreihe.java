package ch.mgeggishorn.controller;

import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Zahlenreihe {

	private static final int minZahl = 1;
	private static final int maxZahl = 4999;
	private static final int maxSaalZahl = 1200;
	private static final int maxPcZahl = 3799;
	private static final int startwert = 1; //Muss immer gleich bleiben! Sons gibts andere Zufallszahlen

	private List<Integer> createAllNumberList() {
		List<Integer> allNumberList = new ArrayList<Integer>();
		
		for (int i = 1; i <= maxZahl; i++) {
			allNumberList.add(i);
		}
		Collections.shuffle(allNumberList);
		return allNumberList;

	}
	
	public List<Integer> createSaalNumberList(){
		List<Integer> saalNumberList = new ArrayList<Integer>();
		
		Random rand = new Random(startwert);
		int zufallszahl;
		for (int i = 1; i <= maxSaalZahl; i++) {
			zufallszahl = rand.nextInt(maxZahl - minZahl) + minZahl;
			//Gebugfixed by Deke
			while(saalNumberList.contains(zufallszahl))
			{
				zufallszahl = rand.nextInt(maxZahl - minZahl) + minZahl;
			}
			saalNumberList.add(zufallszahl);
		}
//		printSaalNumber(saalNumberList);
		return saalNumberList;
	}
	
	public List<Integer> createPCNumberList(){
		List<Integer> saalNumberList = createSaalNumberList();
		List<Integer> pcNumberList = createAllNumberList();
		pcNumberList.removeAll(saalNumberList);
		
//		printPCNumber(pcNumberList);
		return pcNumberList;
	}
	
	public void printAllNumber(){
		List<Integer> allNumberList = createAllNumberList();
		for (Integer zahl : allNumberList) {
			System.out.println("All Number List: " + zahl);
		}
	}
	
	public void printSaalNumber(List<Integer> saalNumberList){
		for (int i = 1; i <= maxSaalZahl; i++) {
			System.out.println("All Saal List: Zahl:" + i + " Zufallszahl: " + saalNumberList.get(i-1));
		}
	}
	public void printPCNumber(List<Integer> pcNumberList){
		for (int i = 1; i <= maxPcZahl; i++) {
			System.out.println("All PC List: Zahl:" + i + " Zufallszahl: " + pcNumberList.get(i-1));
		}
	}

}
