/* 
 Exception Handler
 Author: Nicholas S. Damuth
 School of Technology and Engineering, National University
 CSC262: Programming in JAVA
 Artee Dubey
 February 24, 2026
*/
package CharacterCounter;

import java.io.File;
import java.util.Scanner;
// import java.nio.file.*;

import Banner.Banner;

public class CharacterCounter {
    private static int countCharacters(String line, int count) {
        for (char c: line.toLowerCase().toCharArray()) {
            // preserving char c in case of needing to add for accounting for
            // potential "non characters", e.g. spaces
            count = count +1;
        }
        return count;
    }
    private static int countWords(String line, int count) {
        if (line == null || line == "") {
            return count;
        }
        return count + line.split("\s+").length;
    }
    private static int countLines(String line, int count) {
        return count+1;
    }
    public static void main(String[] args) {
        Banner banner = new Banner();
        banner.print("Character Counter");
        // Ask for file name from user

        // User Interaction Scanner
        Scanner scanner = new Scanner(System.in);

        // Keeping things simple instead of using java.nio.file to check if file exists
        System.out.println("Enter the name of the files to use: (e.g. sample.txt)");
        String input = scanner.nextLine().trim();
        String dir = "./CharacterCounter/";
        File file = new File(dir+input);

        // File manager scanner
        Scanner file_scanner = null;
        // try / catch / finally for opening and reading file
        try {
            file_scanner = new Scanner(file);
        } catch(Exception exception) {
            System.out.println(String.format("File %s not found as %s", input, dir+input));
        } finally {
            System.out.println(String.format("Completed %s", ((file_scanner != null) ? input+"\n" : "")));
        } 
        if (file_scanner != null) {
            int character_count = 0;
            int word_count = 0;
            int line_count = 0;

            while (file_scanner.hasNextLine()) {
                String line = file_scanner.nextLine();
                character_count = countCharacters(line, character_count);
                word_count = countWords(line, word_count);
                line_count = countLines(line, line_count);
            }

            // Final output of results
            System.out.println(String.format("%s has %d number of characters", input, character_count));
            System.out.println(String.format("%s has %d number of words", input, word_count));
            System.out.println(String.format("%s has %d number of lines", input, line_count));

            // Close only if not null, and actually in use
            file_scanner.close();
        }
        scanner.close();
    }
}
