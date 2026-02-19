package chatbot.task;

public class Event extends Task {
    private String startTime;
    private String endTime;
    private static final String SYMBOL = "E";

    public Event(String name, boolean complete, String startTime, String endTime) {
        super(name, complete);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event() {
        this("Empty", false, "No start time", "No end time");
    }

    public void setStart(String startTime) {
        this.startTime = startTime;
    }

    public String getStart() {
        return startTime;
    }

    public void setEnd(String endTime) {
        this.endTime = endTime;
    }

    public String getEnd() {
        return endTime;
    }

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
    @Override
    public String getTypeLabel() {
        return "chatbot.task.Event";
    }
}