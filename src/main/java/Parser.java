import java.util.StringJoiner;

public class Parser {
    private static String botName = "Unnamed";

    public static void setName(String botName) {
        Parser.botName = botName;
    }

    public static String getName() {
        return botName;
    }
    
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

    public static String showTaskList(TaskList taskList) {
        if (taskList.isEmpty()) return "Your task list is empty";
        StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < taskList.size(); i++) {
            joiner.add((i + 1) + ". " + showTask(taskList.get(i)));
        }
        return joiner.toString();
    }

    private static String ackTask(Task task, int totaltaskList) {
        return task.getTypeLabel() + " added: " + task + "\n"
                + "Now you have " + totaltaskList + " taskList";
    }

    public static String markTask(TaskList taskList, String userArguments, boolean complete) {
        String output;
        try {
            int itemNumber = Integer.parseInt(userArguments);
            int itemIndex = itemNumber - 1;
            if (itemIndex < taskList.size() && itemIndex >= 0) {
                taskList.get(itemIndex).setComplete(complete);
                String doneString = complete? "done" : "not done";
                output = "Marked \"" + taskList.get(itemIndex).getName() + "\" as " + doneString;
            } else {
                output = "You only have " + taskList.size() + " items";
            }
        } catch (NumberFormatException e) {
            output = "Proper format is \"mark <int>\"";
        }
        return output;
    }

    private static String addTask(TaskList taskList, String userCommand, String userArguments) {
        return switch (userCommand) {
            case "todo" -> {
                if (userArguments.isBlank()) yield "Format is \"todo <task name>\"\n";
                ToDo t = new ToDo(userArguments.trim(), false);
                taskList.add(t);
                yield ackTask(t, taskList.size());
            }
            case "deadline" -> {
                String[] parts = userArguments.split("/", 2);
                if (parts.length != 2) yield "Format is \"deadline <task name> /<deadline>\"\n";
                String name = parts[0].trim();
                String by = parts[1].trim();
                if (name.isEmpty() || by.isEmpty()) yield "Format is \"deadline <task name> /<deadline>\"\n";
                Deadline t = new Deadline(name, false, by);
                taskList.add(t);
                yield ackTask(t, taskList.size());
            }
            case "event" -> {
                String[] parts = userArguments.split("/", 3);
                if (parts.length != 3) yield "Format is \"event <task name> /<start> /<end>\"\n";
                String name = parts[0].trim();
                String start = parts[1].trim();
                String end = parts[2].trim();
                if (name.isEmpty() || start.isEmpty() || end.isEmpty()) yield "Format is \"event <task name> /<start> /<end>\"\n";
                Event t = new Event(name, false, start, end);
                taskList.add(t);
                yield ackTask(t, taskList.size());
            }
            default -> "Unknown add command\n";
        };
    }


    public static Response parseInput(String userInput, TaskList taskList) {
        String[] splitInput = userInput.strip().split(" ", 2);
        String cmd = splitInput[0].strip();
        String args;
        boolean running = true;
        String message = "Undefined";

        if (userInput.isBlank()) {
            return new Response(true, "Say something fool");
        }
        if (splitInput.length == 1) {
            args = "";
        } else {
            args = splitInput[1].strip();
        }

        switch (cmd.toLowerCase()) {
            case Config.GREET_CMD           -> message = greet(botName);
            case Config.BYE_CMD             -> {
                message = bye();
                running = false;
            }
            case Config.ECHO_CMD            -> message = echo(args);
            case Config.LIST_CMD            -> message = showTaskList(taskList);
            case Config.MARK_CMD            -> message = markTask(taskList, args, true);
            case Config.UNMARK_CMD          -> message = markTask(taskList, args, false);
            case Config.EVENT_CMD, 
                Config.DEADLINE_CMD, 
                Config.TODO_CMD             -> message = addTask(taskList, cmd, args);
            default                         -> message = "i guess bro";
        }
        return new Response(running, message);
    }
}