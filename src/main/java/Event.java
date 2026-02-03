public class Event extends Task {
    private String startTime;
    private String endTime;
    private static final String symbol = "E";

    public Event(String name, boolean complete, String startTime, String endTime) {
        super(name, complete);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event() {
        this("Empty", false, "No start time", "No end time");
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "[" + symbol + "][" +
                (isComplete() ? "X" : " ") +
                "] " +
                getName().strip() +
                " (from: " +
                startTime.strip() +
                " to: " +
                endTime.strip() +
                ")";

    }
}