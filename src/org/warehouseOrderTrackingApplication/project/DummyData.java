package org.warehouseOrderTrackingApplication.project;
import java.util.ArrayList;
import java.util.Random;

class DummyData {
	static Random random = new Random();
	static int age = randInt(16, 65);
	static int id = random.nextInt(100);
	static int id2 = random.nextInt(100);
	static String appendID = Integer.toString(id);
	static String appendID2 = Integer.toString(id2);
	static int firstThreeNumbersOFAddress = random.nextInt(1000);
	static String randomAddress = randString();
	static String[] list = {"Circle", "Lane", "Street", "Road"};
	static String streetName = list[random.nextInt(list.length)];
	int streetNameIndex = random.nextInt(4);
	
	static int dummyID = random.nextInt(1000000);
	static String firstName = "FistName" + appendID;
	static String lastName = "LastName" + appendID2;
	static String finalAddress = firstThreeNumbersOFAddress + " " + randomAddress + " " + streetName;
	static int dummyAge = age;
	
	public static void printDummy() {
		System.out.println("Dummy ID: " + dummyID + "\n" + "First Name: " + firstName + "\n" + "Last Name: " + lastName + "\n" + "Address: " 
				+ finalAddress + "\n" + "Age: " + age);
	}
	
	static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		
		return randomNum;
	}
	
	static String randString() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		return output;
	}
}