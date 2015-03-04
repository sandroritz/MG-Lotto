package ch.mgeggishorn.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.AllPermission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.opencsv.CSVWriter;

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
		
		// Start
		
		String csv = "C:\\Users\\Sandro\\Desktop\\output_saal.csv";
		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter(csv));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		List<String[]> csvdata = new ArrayList<String[]>();
		for( int i = 0; i < maxSaalZahl; i++){
			csvdata.add(new String[] {String.valueOf(saalNumberList.get(i))});
		}
		writer.writeAll(csvdata);
		 
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 
		

		
		//Ende
		
		return saalNumberList;
	}
	
	public List<Integer> createPCNumberList(){
		List<Integer> saalNumberList = createSaalNumberList();
		List<Integer> pcNumberList = createAllNumberList();
		pcNumberList.removeAll(saalNumberList);
		
//		printPCNumber(pcNumberList);
		
	// Start
		
		String csv = "C:\\Users\\Sandro\\Desktop\\output_pc.csv";
		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter(csv));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		List<String[]> csvdata = new ArrayList<String[]>();
		for( int i = 0; i < maxPcZahl; i++){
			csvdata.add(new String[] {String.valueOf(pcNumberList.get(i))});
		}
		writer.writeAll(csvdata);
		 
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 
		

		
		//Ende
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
