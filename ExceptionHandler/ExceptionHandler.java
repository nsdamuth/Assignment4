/* 
 Exception Handler
 Author: Nicholas S. Damuth
 School of Technology and Engineering, National University
 CSC262: Programming in JAVA
 Artee Dubey
 February 24, 2026
*/
// Github repo: https://github.com/nsdamuth/Assignment4.git
// package ExceptionHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.annotation.processing.FilerException;

// import Banner.Banner;
// Moved Banner back to this file instead of its own package to 
// keep things easier for review and sharing
class Result {
    int ave;
    Integer smallest;
    int largest;
    int range;
    int errors;
}
class Banner {
    final static int WINDOW_LENGTH = 100;
    // Retained here rather than abstracted into its own package for the purpose 
    // of keeping all the code in a single file
    private static String _framed_center(String text, int width, String border) {
        border = (border != null) ? border : "*";
        int inner = width - 2;
        int padding = inner - text.length();
        int left = padding / 2;
        int right = padding - left;

        return border + " ".repeat(left) + text + " ".repeat(right) + border;
    }
    public static String _auto_frame(String banner) {
        return _framed_center(banner, WINDOW_LENGTH, null);
    }
    public void print(String name) {
        // Generic banner for class
        String[] banners = {name, "Nicholas S. Damuth", "School of Technology and Engineering, National University", "CSC262: Programming in JAVA", "Artee Dubey", "February 24, 2026"};
        System.out.printf("%s%n", "*".repeat(WINDOW_LENGTH));
        for (String banner : banners) {
            System.out.println(_auto_frame(banner));
        }
        System.out.printf("%s%n", "*".repeat(WINDOW_LENGTH));
    }
}

public class ExceptionHandler {
    private static void display_output(Result result) {
        if (result.errors != 0) {
            System.out.println(String.format("  There was an %s error%s processing the file\n   Using only processed values.", result.errors, (result.errors > 1) ? "s" : ""));
        }
        System.out.println(String.format("  Average of the input value : %s", result.ave));
        System.out.println(String.format("  Smallest value : %s", result.smallest));
        System.out.println(String.format("  Largest value : %s", result.largest));
        System.out.println(String.format("  Range : %s", result.range));
    }
    // Keeping this here and simple rather than an err-logging class 
    // in its own package for simplicity sake similar to banner
    private static void _handle_err(String log_msg, String method) {
        long threadId = Thread.currentThread().threadId();
        System.out.println(String.format("%s - method : %s [%s]", log_msg, method, threadId));
    }
    // Not using a Binary Tree for this version to keep things simple for team
        public static void main(String[] args) {
        Banner banner = new Banner();
        banner.print("Exception Handler"); 
        
        Scanner scanner = new Scanner(System.in);
        
        String[] files = new String[]{"input1", "input2", "input3"};
        for (String file: files) {
            Result result = new Result();
            File filename = new File(file+".txt");
            Scanner file_scanner = null;
            try {
                file_scanner = new Scanner(filename);
            } catch (Exception exception) {
                _handle_err(String.format(" Failed to open file %s", file), "main:file_scanner");
            }

            boolean read_file = true;
            // This is all very unpleasant way of handling things, scanning for an ElementException to identify EOF
            if (file_scanner != null) {
                System.out.println(String.format("Reading file %s", filename));
                int i = 1;
                ArrayList<Integer> values = new ArrayList<>();
                while (read_file) {
                    Integer value = null;
                    try {
                        value = file_scanner.nextInt();
                    } catch (java.util.InputMismatchException e) {
                        _handle_err(String.format(" Failed to convert value (%s) on line %s for file %s", value, i, file), "main:file_scanner.nextInt()");
                        result.errors = result.errors +1;
                        read_file = false;
                    } catch (NoSuchElementException exception) {
                        read_file = false;
                    }
                    if (value != null) {
                        result.ave = result.ave + value;
                        if (value > result.largest) {
                            result.largest = value;
                        }
                        if (result.smallest  == null || result.smallest  > value) {
                            result.smallest  = value;
                        }
                        result.range = result.largest - result.smallest;
                    }
                    i++;
                }
                result.ave = result.ave / i;
                display_output(result);
                file_scanner.close();
            }
        }
        scanner.close();
    }
}
