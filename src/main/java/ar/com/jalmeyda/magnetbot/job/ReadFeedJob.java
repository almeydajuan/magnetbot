package ar.com.jalmeyda.magnetbot.job;

import ar.com.jalmeyda.magnetbot.dao.FeedItemRepository;
import ar.com.jalmeyda.magnetbot.dao.SeriesRepository;
import ar.com.jalmeyda.magnetbot.domain.FeedItem;
import ar.com.jalmeyda.magnetbot.domain.Series;
import ar.com.jalmeyda.magnetbot.service.RSSFeedParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
    private FeedItemRepository feedItemRepository;

    @Resource
    private SeriesRepository seriesRepository;

    @Scheduled(fixedRate = 3600000)
    public void readFeed() {
        for (Series series : seriesRepository.findAll()) {
            Integer seriesId = series.getSeriesId();
            RSSFeedParser rssFeedParser = new RSSFeedParser(String.format(feedUrl, seriesId), seriesId);
            List<FeedItem> newFeedItems = rssFeedParser.readFeed();
            List<FeedItem> oldFeedItems = feedItemRepository.findBySeriesId(seriesId);
            newFeedItems.removeAll(oldFeedItems);
            if (!newFeedItems.isEmpty()) {
                feedItemRepository.save(newFeedItems);
                notifyUsers(newFeedItems, seriesId);
            }
        }
    }

    private void notifyUsers(List<FeedItem> newFeedItems, Integer seriesId) {
        Set<Long> usersFromFeed = seriesRepository.findBySeriesId(seriesId).getUserIds();
        for (Long user : usersFromFeed) {
            // TODO: notify user
        }
    }
}
