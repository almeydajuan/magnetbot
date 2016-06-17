package ar.com.jalmeyda.magnetbot.job;

import ar.com.jalmeyda.magnetbot.dao.FeedItemRepository;
import ar.com.jalmeyda.magnetbot.dao.SeriesRepository;
import ar.com.jalmeyda.magnetbot.domain.FeedItem;
import ar.com.jalmeyda.magnetbot.domain.Series;
import ar.com.jalmeyda.magnetbot.service.RSSFeedParser;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Juan Almeyda on 6/3/2016.
 */
@Component
public class ReadFeedJob {

    @Value("${feedUrl}")
    private String feedUrl;

    @Value("${minDays}")
    private Integer minDays;

    @Value("${maxDays}")
    private Integer maxDays;

    @Resource
    private FeedItemRepository feedItemRepository;

    @Resource
    private SeriesRepository seriesRepository;

    @Scheduled(fixedRate = 3600000)
    public void readFeed() {
        for (Series series : seriesRepository.findAll()) {
            Integer daysFromLastUpdate = Days.daysBetween(series.getLastSuccessfulSync(), getCurrentTime()).getDays();
            if (daysFromLastUpdate > minDays && daysFromLastUpdate < maxDays) {
                Integer seriesId = series.getSeriesId();
                RSSFeedParser rssFeedParser = new RSSFeedParser(String.format(feedUrl, seriesId), seriesId);
                List<FeedItem> newFeedItems = rssFeedParser.readFeed();
                List<FeedItem> oldFeedItems = feedItemRepository.findBySeriesId(seriesId);
                newFeedItems.removeAll(oldFeedItems);
                if (!newFeedItems.isEmpty()) {
                    feedItemRepository.save(newFeedItems);
                    series.setLastSuccessfulSync(getCurrentTime());
                    seriesRepository.save(series);
                    notifyUsers(newFeedItems, series);
                }
            }
        }
    }

    private DateTime getCurrentTime() {
        return DateTime.now(DateTimeZone.UTC);
    }

    private void notifyUsers(List<FeedItem> newFeedItems, Series series) {
        for (Long user : series.getUserIds()) {
            // TODO: notify user
        }
    }
}
