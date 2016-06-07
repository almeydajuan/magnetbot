package ar.com.jalmeyda.magnetbot.controller;

import ar.com.jalmeyda.magnetbot.service.SerieIdResolver;
import ar.com.jalmeyda.magnetbot.service.UserSubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Juan Almeyda on 6/7/2016.
 */
@RestController
public class UserSubscriber {

    @Autowired
    private UserSubscribeService userSubscribeService;

    @Autowired
    private SerieIdResolver serieIdResolver;

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

    @RequestMapping("/series")
    public Set<String> series() {
        return serieIdResolver.getAllSerieNames();
    }

    private Integer getFeedId(String serieName) {
        Integer feedId = serieIdResolver.getSerieIdFromName(serieName);
        if (feedId == null)
            throw new RuntimeException("Serie: " + serieName + " was not found");
        return feedId;
    }

    private Set<String> convertToNames(Set<Integer> feedsFromUser) {
        return feedsFromUser.stream().map(feed -> serieIdResolver.getSerieNameFromId(feed)).collect(Collectors.toSet());
    }

}
