package com.ari.desgnpattern.soliddesignpattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;

//NOTICE:
	/**
	 * Journal class only have methods to add/remove/manipulate journal entries
	 *  It doesn't have method for Saving/Persisting journals, that is handled by Persistence class
	 *  This is separation of concerns. This is handled by Single Responsibility Principle Design Pattern
	 */

class Journal {
	private ArrayList<String> entries = new ArrayList<>();
	private int count = 0;
	
	public void addEntry(String entry) {
		entries.add(count++ +" : "+entry);
	}
	public String removeEntry(int index) {
		count--;
		return entries.remove(index);
	}
	@Override
	public String toString() {
		return String.join(System.lineSeparator(), entries);
	}
	
}

class Persistence {
	public void saveToFile(Journal journal, String fileName, boolean overWrite) throws FileNotFoundException {
		if (overWrite || new File(fileName).exists()) {
			try (PrintStream out = new PrintStream(fileName)) {
				out.println(journal.toString());
			}
		}
	}

	public Journal load(String fileName) {
		return new Journal();
	}

	public Journal load(URL url) {
		return new Journal();
	}
}

public class SingleResposibilityPrinciple {
	public static void main(String[] args) throws IOException {
		Journal j = new Journal();
		j.addEntry("I exercise today");
		j.addEntry("I ate pie today");

		System.out.println(j);
		String fileName = "/Users/vodafone/Desktop/Arindam/Documents/FileOutputs/journal.txt";
		Persistence p = new Persistence();
		p.saveToFile(j, fileName, true);
		
		// windows!
	    Runtime.getRuntime().exec("open -e " + fileName);

	}
}
