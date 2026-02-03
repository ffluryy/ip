import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Megatron2 {
    public static void printLine() {
        for (int i = 0; i < 60; i ++) {
            System.out.print("_");
        }
        System.out.println();
    }
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

    public static void printTask(Task task) {
        System.out.println(task.toString());
    }

    public static void printTaskList(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i ++) {
            System.out.print((i + 1) + ".");
            printTask(tasks.get(i));
        }
    }

    public static void printTaskCount(List<Task> tasks) {
        System.out.println("You now have " + tasks.size() + " tasks");
    }

    public static void ackTask(ToDo task) {
        System.out.print("To do added: ");
        printTask(task);
    }

    public static void ackTask(Deadline task) {
        System.out.print("Deadline added: ");
        printTask(task);
    }

    public static void ackTask(Event task) {
        System.out.print("Event added: ");
        printTask(task);
    }

    public static void markTask(List<Task> tasks, String userArguments, boolean complete) {
        try {
            int itemNumber = Integer.parseInt(userArguments);
            int itemIndex = itemNumber - 1;
            if (itemIndex < tasks.size() && itemIndex >= 0) {
                tasks.get(itemIndex).setComplete(complete);
                String doneString = complete? "done" : "not done";
                System.out.println("Marked \"" + tasks.get(itemIndex).getName() + "\" as " + doneString);
            } else {
                System.out.println("You only have " + tasks.size() + " items");
            }
        } catch (NumberFormatException e) {
            System.out.println("Proper format is \"mark <int>\"");
        }
    }



    public static boolean parseInput(String userInput, List<Task> tasks) {
        String[] splitInput = userInput.split(" ", 2);
        String userCommand = splitInput[0];
        String userArguments;
        boolean running = true;
        if (splitInput.length == 1) {
            userArguments = "";
        } else {
            userArguments = splitInput[1];
        }
        printLine();
        switch (userCommand) {
            case ""         -> {
                System.out.println("Say something fool");
            }
            case "bye"      -> {
                bye();
                running = false;
            }
            case "echo"     -> {
                echo(userArguments);
            }
            case "list"     -> {
                printTaskList(tasks);
            }
            case "mark"     -> {
                markTask(tasks, userArguments, true);
            }
            case "unmark"   -> {
                markTask(tasks, userArguments, false);
            }
            case "todo"     -> {
                ToDo temp = new ToDo(userArguments,false);
                tasks.add(temp);
                ackTask(temp);
                System.out.println("Now you have " + tasks.size() + " tasks");
            }
            case "deadline" -> {
                String[] deadlineArguments = userArguments.split("/", 2);
                if (deadlineArguments.length == 2) {
                    Deadline temp = new Deadline(deadlineArguments[0],false, deadlineArguments[1]);
                    tasks.add(temp);
                    ackTask(temp);
                    System.out.println("Now you have " + tasks.size() + " tasks");
                } else {
                    System.out.println("Format is \"deadline <task name> /<deadline>\"");
                }
            }
            case "event"    -> {
                String[] eventArguments = userArguments.split("/", 3);
                if (eventArguments.length == 3) {
                    Event temp = new Event(eventArguments[0],false, eventArguments[1], eventArguments[2]);
                    tasks.add(temp);
                    ackTask(temp);
                    System.out.println("Now you have " + tasks.size() + " tasks");
                } else {
                    System.out.println("Format is \"event <task name> /<start> /<end>\"");
                }
            }
            default         -> {
                System.out.println("i guess bro");
            }
        }
        printLine();
        return running;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        greet("Megatron 2");
        List<Task> tasks = new ArrayList<>();
        boolean running = true;
        while (running) {
            String userInput = scan.nextLine();
            running = parseInput(userInput, tasks);
        }
    }
}
