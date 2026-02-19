package chatbot.task;

public class ToDo extends Task {
    private static final String SYMBOL = "T";

    public ToDo(String name, boolean complete) {
        super(name, complete);
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "][" +
                (isComplete() ? "X" : " ") +
                "] " +
                getName().strip();
    }
    @Override
    public String getTypeLabel() {
        return "Todo";
    }
}