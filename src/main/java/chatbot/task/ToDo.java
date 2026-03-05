package chatbot.task;

/**
 * Represents a basic task without any specific date or time constraints.
 * <p>
 * This class extends {@link Task} and is identified by the "T" symbol.
 */
public class ToDo extends Task {

    /**
     * The symbol representing the Todo task type.
     */
    private static final String SYMBOL = "T";

    /**
     * {@inheritDoc}
     * @param name     the name or description of the task
     * @param complete whether the task is completed
     */
    public ToDo(String name, boolean complete) {
        super(name, complete);
    }

    /**
     * Returns a string representation of the Todo task, including its type symbol,
     * completion status, and name.
     *
     * @return a formatted string representing the Todo task
     */
    @Override
    public String toString() {
        return "[" + SYMBOL + "][" +
                (isComplete() ? "X" : " ") +
                "] " +
                getName().strip();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code "Todo"}
     */
    @Override
    public String getTypeLabel() {
        return "Todo";
    }
}