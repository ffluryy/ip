import java.util.Scanner;

public class Megatron2 {
    public static void greet(String botName) {
        System.out.println("Hello! I'm " + botName + ".");
        System.out.println("What can I do for you?");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void echo(String inputString) {
        System.out.println(inputString.toUpperCase());
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String botName = "Megatron 2";
        String exitWord = "bye";
        greet(botName);
        boolean exit = false;
        while (!exit) {
            String userInput = scan.nextLine();
            if (userInput.equals(exitWord)) {
                exit = true;
            } else {
                echo(userInput);
            }
        }

        bye();
    }
}
