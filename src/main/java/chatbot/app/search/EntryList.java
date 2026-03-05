package chatbot.app.search;

import java.util.ArrayList;

public class EntryList extends ArrayList<SearchEntry> {

    public void addCount(int index) {
        boolean exists = false;
        for (SearchEntry entry : this) {
            if (entry.getIndex() == index) {
                entry.incrementCount();
                exists = true;
            }
        }
        if (!exists) {
            this.add(new SearchEntry(index,1));
        }
    }

    public void sort() {
        super.sort(new SortSearch().reversed());
    }
}

