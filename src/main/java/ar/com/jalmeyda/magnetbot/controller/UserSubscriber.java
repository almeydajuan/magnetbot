package ar.com.jalmeyda.magnetbot.controller;

import ar.com.jalmeyda.magnetbot.dao.SeriesRepository;
import ar.com.jalmeyda.magnetbot.service.UserSubscribeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Juan Almeyda on 6/7/2016.
 */
@RestController
public class UserSubscriber {

    @Resource
    private UserSubscribeService userSubscribeService;

    @Resource
    private SeriesRepository seriesRepository;

    @RequestMapping("/subscribe")
    public Set<String> subscribe(Long userId, String serieName) {
        Integer feedId = getFeedId(serieName);
        userSubscribeService.subscribeToFeed(userId, feedId);
        return convertToNames(userSubscribeService.getFeedsFromUser(userId));
    }

    @RequestMapping("/unsubscribe")
    public Set<String> unsubscribe(Long userId, String serieName) {
        Integer feedId = getFeedId(serieName);
        userSubscribeService.unsubscribeFromFeed(userId, feedId);
        return convertToNames(userSubscribeService.getFeedsFromUser(userId));
    }

    @RequestMapping("/subscribtions")
    public Set<String> subscribtions(Long userId) {
        return convertToNames(userSubscribeService.getFeedsFromUser(userId));
    }

    @RequestMapping("/series")
    public Set<String> series() {
        return seriesRepository.findAll().stream().map(series -> series.getSeriesName()).collect(Collectors.toSet());
    }

    private Integer getFeedId(String serieName) {
        Integer feedId = seriesRepository.findBySeriesName(serieName).getSeriesId();
        if (feedId == null)
            throw new RuntimeException("Serie: " + serieName + " was not found");
        return feedId;
    }

    private Set<String> convertToNames(Set<Integer> feedsFromUser) {
        return feedsFromUser.stream().map(feed -> seriesRepository.findBySeriesId(feed).getSeriesName()).collect(Collectors.toSet());
    }

}
