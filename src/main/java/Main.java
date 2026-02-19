import java.io.IOException;
import chatbot.app.Io;
import chatbot.app.App;
import chatbot.app.Parser;
import chatbot.config.Config;
import chatbot.storage.Storage;
import chatbot.task.TaskList;

public class Main {
    public static void main(String[] args) {
        Io io = new Io();
        try {
            Storage storage = new Storage("data/tasks.txt");
            TaskList taskList = storage.load();
            Parser parser = new Parser(Config.BOT_NAME);
            App app = new App(io, taskList, parser);
            app.run();
            storage.save(taskList);
        } catch (IOException e){
            System.out.println("Failed to access storage file.");
            System.out.println("Reason: " + e.getMessage());
            System.exit(1);
        }
    }
}
