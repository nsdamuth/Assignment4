package assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ExceptionHandlerAF {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean done = false;

        while (!done) {
            try {
                System.out.print("Enter file name: ");
                String filename = in.next();

                double[] data = readFile(filename);
                double sum = 0;
                for (double d : data) {
                    sum = sum + d;
                }
                System.out.println("The sum is " + sum);
                done = true;
            }
            catch (FileNotFoundException exception){
                System.out.println("File not found");
            }
            catch (NoSuchElementException exception){
                System.out.println("File contents invalid");
            }
            catch (IOException exception){
                System.out.println("Error reading file: " + exception.getMessage());
            }
        }
    }
    public static double[] readFile(String filename) throws IOException {
        File inFile = new File(filename);
        try (Scanner in = new Scanner(inFile)) {
            return readData(in);
        }
    }
    public static double[] readData(Scanner in) throws IOException {
        int numberOfValues = in.nextInt();
        double[] data = new double[numberOfValues];
        for (int i = 0; i < numberOfValues; i++) {
            data[i] = in.nextDouble();
        }
        return data;
    }
}
