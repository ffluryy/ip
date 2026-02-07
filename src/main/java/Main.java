import chatbot.app.Io;
import chatbot.app.App;
import chatbot.app.Parser;
import chatbot.config.Config;
import chatbot.task.TaskList;

public class Main {
    public static void main(String[] args) {
        Io io = new Io();
        TaskList taskList = new TaskList();
        Parser parser = new Parser(Config.BOT_NAME);
        App app = new App(io, taskList, parser);
        app.run();
    }
}
