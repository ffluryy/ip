package chatbot.app;
import chatbot.config.Commands;
import chatbot.config.Config;
import chatbot.task.*;
import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Parser {
    private final String botName;

    public Parser(String botName) {
        this.botName = botName;
    }

    private static String greet(String botName) {
        return "Hello! I'm " + botName + "." + "\n" +
                "What can I do for you?";
    }

    private String bye() {
        return "Bye. Hope to see you again soon!";
    }

    private String echo(String inputString) {
        return inputString;
    }

    private String showTask(Task task) {
        return task.toString();
    }

    private String showTaskList(TaskList taskList) {
        if (taskList.isEmpty()) return "Your task list is empty";
        StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < taskList.size(); i++) {
            joiner.add((i + 1) + ". " + showTask(taskList.get(i)));
        }
        return joiner.toString();
    }

    private String ackTask(Task task, int taskCount) {
        return task.getTypeLabel() + " added: " + task + "\n"
                + "Now you have " + taskCount + " tasks";
    }

    private String markTask(TaskList taskList, String userArguments, boolean complete)
            throws AppExceptions.UserInputException, AppExceptions.OutOfRange {
        if (userArguments.isBlank()) {
            throw new AppExceptions.UserInputException("Proper format is: \"mark <int>\"");
        }

        final int itemNumber;
        try {
            itemNumber = Integer.parseInt(userArguments.trim());
        } catch (NumberFormatException e) {
            throw new AppExceptions.UserInputException("Proper format is: \"mark <int>\"");
        }

        int itemIndex = itemNumber - 1;
        if (itemIndex < 0 || itemIndex >= taskList.size()) {
            throw new AppExceptions.OutOfRange("You only have " + taskList.size() + " items");
        }

        taskList.get(itemIndex).setComplete(complete);
        String doneString = complete ? "done" : "not done";
        return "Marked \"" + taskList.get(itemIndex).getName() + "\" as " + doneString;
    }

    private String addTask(TaskList taskList, String userCommand, String userArguments) {
        return switch (userCommand) {
            case "todo" -> {
                if (userArguments.isBlank()) yield Config.TODO_FORMAT;
                ToDo t = new ToDo(userArguments.trim(), false);
                taskList.add(t);
                yield ackTask(t, taskList.size());
            }
            case "deadline" -> {
                String[] parts = userArguments.split("/", 2);
                if (parts.length != 2) yield Config.DEADLINE_FORMAT;
                String name = parts[0].trim();
                String by = parts[1].trim();
                if (name.isEmpty() || by.isEmpty()) yield Config.DEADLINE_FORMAT;
                Deadline t = new Deadline(name, false, by);
                taskList.add(t);
                yield ackTask(t, taskList.size());
            }
            case "event" -> {
                String[] parts = userArguments.split("/", 3);
                if (parts.length != 3) yield Config.EVENT_FORMAT;
                String name = parts[0].trim();
                String start = parts[1].trim();
                String end = parts[2].trim();
                if (name.isEmpty() || start.isEmpty() || end.isEmpty()) yield Config.EVENT_FORMAT;
                Event t = new Event(name, false, start, end);
                taskList.add(t);
                yield ackTask(t, taskList.size());
            }
            default -> "Unknown add command\n";
        };
    }

    private String deleteTask(TaskList taskList, String userArguments)
            throws AppExceptions.UserInputException, AppExceptions.OutOfRange {

        if (userArguments.isBlank()) {
            throw new AppExceptions.UserInputException("Proper format is: \"delete <int>\"");
        }

        final int itemNumber;
        try {
            itemNumber = Integer.parseInt(userArguments.trim());
        } catch (NumberFormatException e) {
            throw new AppExceptions.UserInputException("\"" + userArguments + "\" is not an int");
        }

        int itemIndex = itemNumber - 1;
        if (itemIndex < 0 || itemIndex >= taskList.size()) {
            throw new AppExceptions.OutOfRange("Task number " + itemNumber + " does not exist");
        }

        taskList.remove(itemIndex);
        return "Task " + itemNumber + " removed";
    }

    private String manual() {
        List<String> commands = new ArrayList<>();
        for (Field field : Commands.class.getDeclaredFields()) {
            if (field.getType() == String.class) {
                try {
                    commands.add((String) field.get(null)); // static field → null instance
                } catch (IllegalAccessException e) {
                    // ignore or rethrow
                }
            }
        }
        return "List of commands: " + String.join(", ", commands);
    }

    public Response parseInput(String userInput, TaskList taskList) {
        if (userInput.isBlank()) {
            return new Response(true, "Blank input received");
        }

        String[] splitInput = userInput.strip().split(" ", 2);
        String cmd = splitInput[0].strip();
        String args = (splitInput.length == 1) ? "" : splitInput[1].strip();

        boolean running = true;
        String message;

        try {
            switch (cmd.toLowerCase()) {
            case Commands.GREET_CMD -> message = greet(botName);
            case Commands.BYE_CMD -> {
                message = bye();
                running = false;
            }
            case Commands.ECHO_CMD -> message = echo(args);
            case Commands.LIST_CMD -> message = showTaskList(taskList);
            case Commands.MARK_CMD -> message = markTask(taskList, args, true);
            case Commands.UNMARK_CMD -> message = markTask(taskList, args, false);
            case Commands.EVENT_CMD,
                 Commands.DEADLINE_CMD,
                 Commands.TODO_CMD -> message = addTask(taskList, cmd, args);
            case Commands.DELETE_CMD -> message = deleteTask(taskList, args);
            case Commands.HELP_CMD -> message = manual();
            default -> message = Config.HELP_MSG;
            }
        } catch (AppExceptions.UserInputException | AppExceptions.OutOfRange e) {
            message = e.getMessage();
        }

        return new Response(running, message);
    }
}