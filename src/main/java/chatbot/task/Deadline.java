package chatbot.task;

public class Deadline extends Task {
    private String dueDate;
    private static final String SYMBOL = "D";

    public Deadline(String name, boolean complete, String dueDate) {
        super(name, complete);
        this.dueDate = dueDate;
    }

    public Deadline() {
        this("Empty", false, "No due date");
    }

    public void setBy(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getBy() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "][" +
                (isComplete() ? "X" : " ") +
                "] " +
                getName().strip() +
                " (by: " +
                dueDate.strip() +
                ")";

    }
    @Override
    public String getTypeLabel() {
        return "chatbot.task.Deadline";
    }
}