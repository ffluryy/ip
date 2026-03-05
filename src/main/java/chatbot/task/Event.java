package chatbot.task;

/**
 * Represents a task that occurs within a specific time frame.
 * <p>
 * An {@code Event} extends {@link Task} by adding a start time and an end time.
 * It is identified by the "E" symbol.
 */
public class Event extends Task {

    /**
     * The start time or date of the event.
     */
    private String startTime;

    /**
     * The end time or date of the event.
     */
    private String endTime;

    /**
     * The symbol representing the Event task type.
     */
    private static final String SYMBOL = "E";

    /**
     * {@inheritDoc}
     *
     * @param name      the name or description of the task
     * @param complete  whether the task is completed
     * @param startTime the starting time or date
     * @param endTime   the ending time or date
     */
    public Event(String name, boolean complete, String startTime, String endTime) {
        super(name, complete);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs a default {@code Event} with placeholder values.
     */
    public Event() {
        this("Empty", false, "No start time", "No end time");
    }

    /**
     * Sets the start time of the event.
     *
     * @param startTime the new start time
     */
    public void setStart(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns the start time of the event.
     *
     * @return the start time string
     */
    public String getStart() {
        return startTime;
    }

    /**
     * Sets the end time of the event.
     *
     * @param endTime the new end time
     */
    public void setEnd(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the end time string
     */
    public String getEnd() {
        return endTime;
    }

    /**
     * Returns a string representation of the Event task, including its type symbol,
     * completion status, name, and the time duration.
     *
     * @return a formatted string representing the Event
     */
    @Override
    public String toString() {
        return "[" + SYMBOL + "][" +
                (isComplete() ? "X" : " ") +
                "] " +
                getName().strip() +
                " (from: " +
                startTime.strip() +
                " to: " +
                endTime.strip() +
                ")";
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code "Event"}
     */
    @Override
    public String getTypeLabel() {
        return "Event";
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation concatenates the task name, start time, and end time.
     *
     * @return {@inheritDoc} plus start and end times
     */
    @Override
    public String getInfo() {
        return (getName() + getStart() + getEnd()).toLowerCase();
    }
}