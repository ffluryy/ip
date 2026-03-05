package chatbot.app.search;

public class SearchEntry {
    private int index;
    private int count = 0;

    public SearchEntry(int index, int count) {
        this.index = index;
        this.count = count;
    }

    public SearchEntry() {
        new SearchEntry(0,0);
    }

    public int getIndex() {
        return index;
    }

    public int getCount() {
        return count;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrementCount() {
        this.count ++;
    }
}
