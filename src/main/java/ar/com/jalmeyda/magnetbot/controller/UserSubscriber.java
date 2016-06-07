package ar.com.jalmeyda.magnetbot.controller;

import ar.com.jalmeyda.magnetbot.service.UserSubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by Juan Almeyda on 6/7/2016.
 */
@RestController
public class UserSubscriber {

    @Autowired
    private UserSubscribeService userSubscribeService;

    @RequestMapping("/subscribe")
    public Set<Integer> subscribe(Long userId, Integer feedId) {
        userSubscribeService.subscribeToFeed(userId, feedId);
        return userSubscribeService.getFeedsFromUser(userId);
    }

    @RequestMapping("/unsubscribe")
    public Set<Integer> unsubscribe(Long userId, Integer feedId) {
        userSubscribeService.unsubscribeFromFeed(userId, feedId);
        return userSubscribeService.getFeedsFromUser(userId);
    }


}
