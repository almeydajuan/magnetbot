package ar.com.jalmeyda.job;

import ar.com.jalmeyda.domain.parser.RSSFeedParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
        rssFeedParser.readFeed();
    }
}
