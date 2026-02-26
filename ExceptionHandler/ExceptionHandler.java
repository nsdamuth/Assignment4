/* 
 Exception Handler
 Author: Nicholas S. Damuth
 School of Technology and Engineering, National University
 CSC262: Programming in JAVA
 Artee Dubey
 February 24, 2026
*/
package ExceptionHandler;

import java.util.Scanner;

// import Banner.Banner;
// Moved Banner back to this file instead of its own package to 
// keep things easier for review and sharing
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
    // Keeping this here and simple rather than an err-logging class 
    // in its own package for simplicity sake similar to banner
    private static void _handle_err(String log_msg, String method) {
        long threadId = Thread.currentThread().threadId();
        System.out.println(String.format("%s - method : %s [%s]", log_msg, method, threadId));
    }
    public static void main(String[] args) {
        Banner banner = new Banner();
        banner.print("Exception Handler"); 
        
        Scanner scanner = new Scanner(System.in);
    }
}
