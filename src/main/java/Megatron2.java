import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Megatron2 {
    public static String greet(String botName) {
        return "Hello! I'm " + botName + "." + "\n" +
                "What can I do for you?";
    }

    public static String bye() {
        return "Bye. Hope to see you again soon!";
    }

    public static String echo(String inputString) {
        return inputString;
    }

    public static String showTask(Task task) {
        return task.toString();
    }

    public static String showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) return "Your task list is empty";
        StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            joiner.add((i + 1) + ". " + showTask(tasks.get(i)));
        }
        return joiner.toString();
    }

    private static String ackTask(Task task, int totalTasks) {
        return task.getTypeLabel() + " added: " + task + "\n"
                + "Now you have " + totalTasks + " tasks";
    }

    public static String markTask(List<Task> tasks, String userArguments, boolean complete) {
        String output;
        try {
            int itemNumber = Integer.parseInt(userArguments);
            int itemIndex = itemNumber - 1;
            if (itemIndex < tasks.size() && itemIndex >= 0) {
                tasks.get(itemIndex).setComplete(complete);
                String doneString = complete? "done" : "not done";
                output = "Marked \"" + tasks.get(itemIndex).getName() + "\" as " + doneString;
            } else {
                output = "You only have " + tasks.size() + " items";
            }
        } catch (NumberFormatException e) {
            output = "Proper format is \"mark <int>\"";
        }
        return output;
    }

    private static String addTask(List<Task> tasks, String userCommand, String userArguments) {
        return switch (userCommand) {
            case "todo" -> {
                if (userArguments.isBlank()) yield "Format is \"todo <task name>\"\n";
                ToDo t = new ToDo(userArguments.trim(), false);
                tasks.add(t);
                yield ackTask(t, tasks.size());
            }
            case "deadline" -> {
                String[] parts = userArguments.split("/", 2);
                if (parts.length != 2) yield "Format is \"deadline <task name> /<deadline>\"\n";
                String name = parts[0].trim();
                String by = parts[1].trim();
                if (name.isEmpty() || by.isEmpty()) yield "Format is \"deadline <task name> /<deadline>\"\n";
                Deadline t = new Deadline(name, false, by);
                tasks.add(t);
                yield ackTask(t, tasks.size());
            }
            case "event" -> {
                String[] parts = userArguments.split("/", 3);
                if (parts.length != 3) yield "Format is \"event <task name> /<start> /<end>\"\n";
                String name = parts[0].trim();
                String start = parts[1].trim();
                String end = parts[2].trim();
                if (name.isEmpty() || start.isEmpty() || end.isEmpty()) yield "Format is \"event <task name> /<start> /<end>\"\n";
                Event t = new Event(name, false, start, end);
                tasks.add(t);
                yield ackTask(t, tasks.size());
            }
            default -> "Unknown add command\n";
        };
    }


    public static Response parseInput(String userInput, List<Task> tasks) {
        String[] splitInput = userInput.strip().split(" ", 2);
        String cmd = splitInput[0].strip();
        String args;
        boolean running = true;
        String message;

        if (userInput.isBlank()) {
            return new Response(true, "Say something fool");
        }
        if (splitInput.length == 1) {
            args = "";
        } else {
            args = splitInput[1].strip();
        }
        switch (cmd) {
            case "bye"      -> {
                message = bye();
                running = false;
            }
            case "echo"     -> message = echo(args);
            case "list"     -> message = showTaskList(tasks);
            case "mark"     -> message = markTask(tasks, args, true);
            case "unmark"   -> message = markTask(tasks, args, false);
            case "todo", "deadline", "event" -> message = addTask(tasks, cmd, args);
            default         -> message = "i guess bro";
        }
        return new Response(running, message);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Ui.show(greet("Megatron 2"));
        List<Task> tasks = new ArrayList<>();
        boolean running = true;
        Response r;
        while (running) {
            String userInput = scan.nextLine();
            r = parseInput(userInput, tasks);
            Ui.show(r.message());
            running = r.running();
        }
    }
}
