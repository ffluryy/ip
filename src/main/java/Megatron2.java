import java.util.ListIterator;
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

    public static void readList(String[] listItems, int listCount, boolean[] listCompletion) {
        for (int i = 0; i < listCount; i ++) {
            System.out.print((i+1) + ".[");
            if (listCompletion[i]) {
                System.out.print("X");
            } else {
                System.out.print(" ");
            }
            System.out.println("] " + listItems[i]);
        }
    }

    public static void markAsDone(boolean[] listCompletion, int itemNumber) {
        listCompletion[itemNumber] = true;
        System.out.println("I've marked item " + (itemNumber + 1) + " as done");
    }

    public static void markAsNotDone(boolean[] listCompletion, int itemNumber) {
        listCompletion[itemNumber] = false;
        System.out.println("I've marked item " + (itemNumber + 1) + " as not done");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String botName = "Megatron 2";
        String exitWord = "bye";
        greet(botName);
        int listLength = 100;
        String[] listItems = new String[listLength];
        boolean[] listCompletion = new boolean[listLength];
        int listCount = 0;

        boolean exit = false;
        while (!exit) {
            String userInput = scan.nextLine();
            String[] splitInput = userInput.split(" ", 2);
            String userCommand = splitInput[0];
            String userArguments;
            if (splitInput.length == 1) {
                userArguments = "";
            } else {
                userArguments = splitInput[1];
            }
            switch (userCommand) {
                case ""         -> System.out.println("say something fool");
                case "bye"      -> exit = true;
                case "echo"     -> echo(userArguments);
                case "list"     -> readList(listItems, listCount, listCompletion);
                case "mark"     -> {
                    try {
                        int itemNumber = Integer.parseInt(userArguments);
                        if (itemNumber <= listCount && itemNumber > 0) {
                            markAsDone(listCompletion, itemNumber-1);
                        } else {
                            System.out.println("You only have " + listCount + " items");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Proper format is \"mark <int>\"");
                    }

                }
                case "unmark"   -> {
                    try {
                        int itemNumber = Integer.parseInt(userArguments);
                        if (itemNumber <= listCount && itemNumber > 0) {
                            markAsNotDone(listCompletion, itemNumber-1);
                        } else {
                            System.out.println("You only have " + listCount + " items");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Proper format is \"unmark <int>\"");
                    }
                }
                case "add"      -> {
                    if (userArguments.isEmpty()) {
                        System.out.println("add what dumbahh");
                    } else if (listCount < 100) {
                        listItems[listCount] = userArguments;
                        System.out.println("added: " + userArguments);
                        listCount ++;
                    } else {
                        System.out.println("List full");
                    }

                }
                default         -> System.out.println("i guess bro");
            }
        }

        bye();
    }
}
