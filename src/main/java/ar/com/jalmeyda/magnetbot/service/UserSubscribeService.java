package ar.com.jalmeyda.magnetbot.service;

import ar.com.jalmeyda.magnetbot.dao.SeriesRepository;
import ar.com.jalmeyda.magnetbot.dao.UserRepository;
import ar.com.jalmeyda.magnetbot.domain.Series;
import ar.com.jalmeyda.magnetbot.domain.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by Juan Almeyda on 6/7/2016.
 */
@Component
public class UserSubscribeService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private SeriesRepository seriesRepository;

    public void subscribeToFeed(Long userId, Integer serieId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            user = new User(userId);
            userRepository.save(user);
        }
        user.getSeriesIds().add(serieId);
        userRepository.save(user);

        Series series = seriesRepository.findBySeriesId(serieId);
        series.getUserIds().add(userId);
        seriesRepository.save(series);
    }

    public void unsubscribeFromFeed(Long userId, Integer serieId) {
        User user = userRepository.findByUserId(userId);
        user.getSeriesIds().remove(serieId);
        userRepository.save(user);

        Series series = seriesRepository.findBySeriesId(serieId);
        series.getUserIds().remove(userId);
        seriesRepository.save(series);
    }

    public Set<Integer> getSubscriptionsFromUser(Long userId) {
        return userRepository.findByUserId(userId).getSeriesIds();
    }
}
