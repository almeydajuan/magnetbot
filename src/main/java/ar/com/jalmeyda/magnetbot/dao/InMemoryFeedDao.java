package ar.com.jalmeyda.magnetbot.dao;

import ar.com.jalmeyda.magnetbot.domain.FeedItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Juan Almeyda on 6/6/2016.
 */
@Component
public class InMemoryFeedDao implements FeedDao {

    private Map<Integer, List<FeedItem>> items = new HashMap<>();

    @Override
    public List<FeedItem> getItemsFromFeed(Integer feedId) {
        List<FeedItem> listItems = items.get(feedId);
        if (listItems == null)
            listItems = new ArrayList<>();
        return listItems;
    }

    @Override
    public void updateFeed(Integer feedId, List<FeedItem> feedItems) {
        items.put(feedId, feedItems);
    }
}
