package chatbot.app;

import java.util.Scanner;
public class Io {
    private final Scanner scanner = new Scanner(System.in);
    private static final String line = "-".repeat(100);

    public static void line() {
        System.out.println(line);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public static void show(String s) {
        line();
        System.out.println(s);
        line();
    }

    public static void show(Response r) {
        line();
        System.out.println(r.message());
        line();
    }
}