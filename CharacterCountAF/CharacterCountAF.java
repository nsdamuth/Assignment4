package assignment4;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CharacterCountAF {
    public static int countCharacters(String line) {

        return line.length();

    }
    public static int countWords(String line) {
        String[] words = line.split("\\s+");
        return words.length;
    }
    public static int countLines() {
        return 1;
    }
    public static void main(String[] args){
        Scanner userInput = new Scanner(System.in);

        System.out.print("Enter file name: ");
        String fileName = userInput.nextLine();
        userInput.close();

        int totalCharacters = 0;
        int totalWords = 0;
        int totalLines = 0;

        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("File \"" + fileName + "\" not found.");
        }
        while (fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();

            totalCharacters += countCharacters(line);
            totalWords += countWords(line);
            totalLines += countLines();
        }

        fileScanner.close();

        System.out.println("Total characters: " + totalCharacters);
        System.out.println("Total words: " + totalWords);
        System.out.println("Total lines: " + totalLines);
    }
}