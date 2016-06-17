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
        Integer seriesId = getSeriesId(serieName);
        userSubscribeService.subscribeToFeed(userId, seriesId);
        return convertToNames(userSubscribeService.getSubscriptionsFromUser(userId));
    }

    @RequestMapping("/unsubscribe")
    public Set<String> unsubscribe(Long userId, String serieName) {
        Integer seriesId = getSeriesId(serieName);
        userSubscribeService.unsubscribeFromFeed(userId, seriesId);
        return convertToNames(userSubscribeService.getSubscriptionsFromUser(userId));
    }

    @RequestMapping("/subscriptions")
    public Set<String> subscriptions(Long userId) {
        return convertToNames(userSubscribeService.getSubscriptionsFromUser(userId));
    }

    @RequestMapping("/series")
    public Set<String> series() {
        return seriesRepository.findAll().stream().map(series -> series.getSeriesName()).collect(Collectors.toSet());
    }

    private Integer getSeriesId(String serieName) {
        Integer seriesId = seriesRepository.findBySeriesName(serieName).getSeriesId();
        if (seriesId == null)
            throw new RuntimeException("Serie: " + serieName + " was not found");
        return seriesId;
    }

    private Set<String> convertToNames(Set<Integer> feedsFromUser) {
        return feedsFromUser.stream().map(feed -> seriesRepository.findBySeriesId(feed).getSeriesName()).collect(Collectors.toSet());
    }

}
