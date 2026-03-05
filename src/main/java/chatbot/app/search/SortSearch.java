package chatbot.app.search;

import java.util.Comparator;

public class SortSearch implements Comparator<SearchEntry> {

    @Override
    public int compare(SearchEntry a, SearchEntry b) {
        return Integer.compare(a.getCount(), b.getCount());
    }
}
