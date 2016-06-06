package ar.com.jalmeyda.magnetbot.dao;

import ar.com.jalmeyda.magnetbot.domain.FeedItem;

import java.util.List;

/**
 * Created by Juan Almeyda on 6/6/2016.
 */
public interface FeedDao {

    List<FeedItem> getItemsFromFeed(Integer feedId);

    void updateFeed(Integer feedId, List<FeedItem> feedItems);
}
