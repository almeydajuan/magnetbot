package ar.com.jalmeyda.magnetbot.service;

import ar.com.jalmeyda.magnetbot.dao.FeedItemRepository;
import ar.com.jalmeyda.magnetbot.dao.SeriesRepository;
import ar.com.jalmeyda.magnetbot.domain.FeedItem;
import ar.com.jalmeyda.magnetbot.domain.Series;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Juan Almeyda on 6/17/2016.
 */
@Component
public class SeriesService {

    @Resource
    private SeriesRepository seriesRepository;

    @Resource
    private FeedItemRepository feedItemRepository;

    public Set<String> getAllSeries() {
        return seriesRepository.findAll().stream().map(series -> series.getSeriesName()).collect(Collectors.toSet());
    }

    public Integer getSeriesId(String seriesName) {
        Series series = seriesRepository.findBySeriesName(seriesName);
        if (series == null)
            throw new RuntimeException("Series: " + seriesName + " was not found");
        return series.getSeriesId();
    }

    public List<FeedItem> getLatestEpisode(Integer seriesId) {
        List<FeedItem> feedItems = feedItemRepository.findBySeriesId(seriesId);
        List<FeedItem> orderedFeedItems = feedItems.stream().sorted((f1, f2) -> f2.getPubDate()
                .compareTo(f1.getPubDate())).collect(Collectors.toList());
        Date latestDate = orderedFeedItems.get(0).getPubDate();
        return feedItems.stream().filter(feed -> feed.getPubDate().equals(latestDate)).collect(Collectors.toList());
    }
}
