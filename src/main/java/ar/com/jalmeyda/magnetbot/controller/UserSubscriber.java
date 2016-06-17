package ar.com.jalmeyda.magnetbot.controller;

import ar.com.jalmeyda.magnetbot.dto.UserDTO;
import ar.com.jalmeyda.magnetbot.service.SeriesService;
import ar.com.jalmeyda.magnetbot.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by Juan Almeyda on 6/7/2016.
 */
@RestController
public class UserSubscriber {

    @Resource
    private UserService userService;

    @Resource
    private SeriesService seriesService;

    @RequestMapping("/subscribe")
    public UserDTO subscribe(Long userId, String serieName) {
        try {
            Integer seriesId = seriesService.getSeriesId(serieName);
            userService.subscribeToFeed(userId, seriesId);
            return getUserDTO(userId);
        } catch (Exception e) {
            return new UserDTO(userId, null, e.getMessage());
        }
    }

    @RequestMapping("/unsubscribe")
    public UserDTO unsubscribe(Long userId, String serieName) {
        try {
            Integer seriesId = seriesService.getSeriesId(serieName);
            userService.unsubscribeFromFeed(userId, seriesId);
            return getUserDTO(userId);
        } catch (Exception e) {
            return new UserDTO(userId, null, e.getMessage());
        }
    }

    @RequestMapping("/subscriptions")
    public UserDTO subscriptions(Long userId) {
        return getUserDTO(userId);
    }

    @RequestMapping("/series")
    public Set<String> series() {
        return seriesService.getAllSeries();
    }

    private UserDTO getUserDTO(Long userId) {
        return new UserDTO(userId, userService.getSubscriptionsFromUser(userId), null);
    }

}
