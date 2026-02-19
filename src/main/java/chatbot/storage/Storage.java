package chatbot.storage;

import chatbot.task.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) throws IOException {
        this.filePath = Paths.get(filePath);
        init();
    }

    private void init() throws IOException {
        Path parentDir = filePath.getParent();

        // Create directory if missing
        if (parentDir != null && Files.notExists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        // Create file if missing
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }

    public TaskList load() throws IOException {
        TaskList taskList = new TaskList();

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                taskList.add(deserialize(line));
            }
        }

        return taskList;
    }

    public void save(TaskList taskList) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : taskList) {
                writer.write(serialize(task));
                writer.newLine();
            }
        }
    }

    private String serialize(Task task) {
        if (task instanceof ToDo t) {
            return "T | " + (t.isComplete() ? 1 : 0) + " | " + t.getName();
        }

        if (task instanceof Deadline d) {
            return "D | " + (d.isComplete() ? 1 : 0)
                    + " | " + d.getName()
                    + " | " + d.getBy();
        }

        if (task instanceof Event e) {
            return "E | " + (e.isComplete() ? 1 : 0)
                    + " | " + e.getName()
                    + " | " + e.getStart()
                    + " | " + e.getEnd();
        }

        throw new IllegalStateException("Unknown task type");
    }

    private Task deserialize(String line) {
        String[] parts = line.split("\\s*\\|\\s*");

        String type = parts[0];
        boolean complete = parts[1].equals("1");

        return switch (type) {
            case "T" -> new ToDo(parts[2], complete);

            case "D" -> new Deadline(parts[2], complete, parts[3]);

            case "E" -> new Event(parts[2], complete, parts[3], parts[4]);

            default -> throw new IllegalArgumentException("Corrupt data: " + line);
        };
    }
}