package chatbot.app;

import chatbot.task.TaskList;

public class App {
    private final Ui ui;
    private final TaskList taskList;
    private final Parser parser;

    public App(Ui ui, TaskList taskList, Parser parser) {
        this.ui = ui;
        this.taskList = taskList;
        this.parser = parser;
    }

    public void run() {
        boolean running = true;
        Response r;
        Ui.show(parser.parseInput("hi", taskList));
        while (running) {
            String userInput = ui.readCommand();
            r = parser.parseInput(userInput, taskList);
            Ui.show(r);
            running = r.running();
        }
    }

}