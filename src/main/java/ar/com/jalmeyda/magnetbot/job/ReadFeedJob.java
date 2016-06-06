package ar.com.jalmeyda.magnetbot.job;

import ar.com.jalmeyda.magnetbot.dao.FeedDao;
import ar.com.jalmeyda.magnetbot.domain.FeedItem;
import ar.com.jalmeyda.magnetbot.domain.parser.RSSFeedParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan Almeyda on 6/3/2016.
 */
@Component
public class ReadFeedJob {

    @Value("${feedUrl}")
    private String feedUrl;

    @Resource
    private FeedDao feedDao;

    @Scheduled(fixedRate = 60000)
    public void readFeed() {
        RSSFeedParser rssFeedParser = new RSSFeedParser(feedUrl);
        List<FeedItem> newFeedItems = rssFeedParser.readFeed();
        List<FeedItem> feedItemsToPersist = new ArrayList<>(newFeedItems);
        List<FeedItem> oldFeedItems = feedDao.getItemsFromFeed(262);
        newFeedItems.removeAll(oldFeedItems);
        feedDao.updateFeed(262, feedItemsToPersist);
    }
}
