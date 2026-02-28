// package assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ExceptionHandler {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter file name: ");
        String filename = in.next();
        in.close();

        Scanner file_data = null;
        Integer numberOfValues = 0;
        while (file_data == null) {
            try {
                File inFile = new File(filename);
                file_data = new Scanner(inFile);
                numberOfValues = file_data.nextInt();
            }
            catch (FileNotFoundException exception){
                System.out.println("File not found");
            }
            catch (NoSuchElementException exception){
                System.out.println("File contents invalid");
            }


            if (numberOfValues != null) {
                Double sum = null;
                for (int i = 0; i < numberOfValues; i++) {
                    try {
                        sum = ((sum == null) ? 0 : sum) + file_data.nextDouble();
                    } catch (Exception e) {
                        System.out.println("Failed to convert value");
                        sum = null;
                        break;
                    }
                }
                
                if (sum != null) {
                    System.out.println(String.format("The sum is %s", sum));
                }
            }
        }
    }

}
