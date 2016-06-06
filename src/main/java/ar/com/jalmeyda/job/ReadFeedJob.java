package ar.com.jalmeyda.job;

import ar.com.jalmeyda.domain.FeedItem;
import ar.com.jalmeyda.domain.parser.RSSFeedParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan Almeyda on 6/3/2016.
 */
@Component
public class ReadFeedJob {

    @Value("${feedUrl}")
    private String feedUrl;

    @Scheduled(fixedRate = 60000)
    public void readFeed() {
        RSSFeedParser rssFeedParser = new RSSFeedParser(feedUrl);
        List<FeedItem> newFeedItems = rssFeedParser.readFeed();
        List<FeedItem> feedItemsToPersist = new ArrayList<>(newFeedItems);
        List<FeedItem> oldFeedItems = new ArrayList<>();
        newFeedItems.removeAll(oldFeedItems);
    }
}
