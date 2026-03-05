package chatbot.app;

import java.util.Scanner;

/**
 * Handles user interaction for the chatbot application.
 * <p>
 * This class is responsible for reading user input and displaying
 * formatted output to the console. Output is wrapped with a separator
 * line to improve readability.
 */
public class Ui {

    /** Scanner used to read user input from standard input. */
    private final Scanner scanner = new Scanner(System.in);

    /** Separator line used to visually divide console output. */
    private static final String line = "-".repeat(100);

    /**
     * Prints a separator line to the console.
     */
    public static void line() {
        System.out.println(line);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return the line of text entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user surrounded by separator lines.
     *
     * @param s the message to display
     */
    public static void show(String s) {
        line();
        System.out.println(s);
        line();
    }

    /**
     * Displays the message contained in a {@code Response} object,
     * surrounded by separator lines.
     *
     * @param r the response whose message will be displayed
     */
    public static void show(Response r) {
        line();
        System.out.println(r.message());
        line();
    }
}