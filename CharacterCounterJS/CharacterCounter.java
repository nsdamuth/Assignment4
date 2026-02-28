package myClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CharacterCounter {
	
	public static void main(String[] args) {
		Scanner inputScanner = new Scanner(System.in);
		System.out.print("Enter the file name: ");
		String fileName = inputScanner .nextLine();
		
		
		try {
			Scanner fileScanner = newScanner(new File(fileName));
			
			
			int totalCharacters = 0;
			int totalWords = 0;
			int totalLines = 0;
			
			
			while (fileScanner .hasNextLine()) {
				String line = fileScanner .nextLine();
				totalLines++;
				totalCharacters += countCharacters(line);
				totalWords += countWords(line);
				
			}
			
			fileScanner .close();
			
			
			System.out.println("Number of characters: " + totalCharacters);
			System.out.println("Number of words: " + totalWords);
			System.out.println("Number of lines: " + totalLines);
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
		}
			
			
		inputScanner .close();
	}
	
	
	private static Scanner newScanner(File file) {
		// TODO Auto-generated method stub
		return null;
	}


	private static int countWords(String line) {
		// TODO Auto-generated method stub
		return 0;
	}


	// Count characters in a line (including spaces)
	public static int countCharacters(String line) {
		return line.length();
	}
	

	//Counts words in a line (words separated by whitespace)
	public static int countWords(String line) {
		if (line == null || line.isEmpty()) {
			return 0;
		}
		//Split by whitespace (space, tab, etc.)
		String[] words = line.trim().split("\\s+");
		return words.length;
	}