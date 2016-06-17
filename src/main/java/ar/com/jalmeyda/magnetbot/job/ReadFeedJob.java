package ar.com.jalmeyda.magnetbot.job;

import ar.com.jalmeyda.magnetbot.dao.FeedDao;
import ar.com.jalmeyda.magnetbot.dao.UserFeedsDao;
import ar.com.jalmeyda.magnetbot.domain.FeedItem;
import ar.com.jalmeyda.magnetbot.service.RSSFeedParser;
import ar.com.jalmeyda.magnetbot.service.SerieIdResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Juan Almeyda on 6/3/2016.
 */
@Component
public class ReadFeedJob {

    @Value("${feedUrl}")
    private String feedUrl;

    @Resource
    private FeedDao feedDao;

    @Resource
    private SerieIdResolver serieIdResolver;

    @Resource
    private UserFeedsDao userFeedsDao;

    @Scheduled(fixedRate = 60000)
    public void readFeed() {
        for (Integer serieId : serieIdResolver.getAllSerieIds()) {
            String serieFeedUrl = feedUrl;
            RSSFeedParser rssFeedParser = new RSSFeedParser(serieFeedUrl.replace("$ID", serieId.toString()));
            List<FeedItem> newFeedItems = rssFeedParser.readFeed();
            List<FeedItem> feedItemsToPersist = new ArrayList<>(newFeedItems);
            List<FeedItem> oldFeedItems = feedDao.getItemsFromFeed(serieId);
            newFeedItems.removeAll(oldFeedItems);
            feedDao.updateFeed(serieId, feedItemsToPersist);
            notifyUsers(newFeedItems, serieId);
        }
    }

    private void notifyUsers(List<FeedItem> newFeedItems, Integer serieId) {
        if (!newFeedItems.isEmpty()) {
            Set<Long> usersFromFeed = userFeedsDao.getUsersFromFeed(serieId);
            for (Long user : usersFromFeed) {
                // TODO: notify user
            }
        }
    }
}
