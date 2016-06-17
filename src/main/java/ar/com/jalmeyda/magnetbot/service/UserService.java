package ar.com.jalmeyda.magnetbot.service;

import ar.com.jalmeyda.magnetbot.dao.SeriesRepository;
import ar.com.jalmeyda.magnetbot.dao.UserRepository;
import ar.com.jalmeyda.magnetbot.domain.Series;
import ar.com.jalmeyda.magnetbot.domain.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Juan Almeyda on 6/7/2016.
 */
@Component
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private SeriesRepository seriesRepository;

    public void subscribeToFeed(Long userId, Integer serieId) {
        User user = findOrCreateByUserId(userId);
        user.getSeriesIds().add(serieId);
        userRepository.save(user);

        Series series = seriesRepository.findBySeriesId(serieId);
        series.getUserIds().add(userId);
        seriesRepository.save(series);
    }

    private User findOrCreateByUserId(Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            user = new User(userId);
            userRepository.save(user);
        }
        return user;
    }

    public void unsubscribeFromFeed(Long userId, Integer serieId) {
        User user = userRepository.findByUserId(userId);
        user.getSeriesIds().remove(serieId);
        userRepository.save(user);

        Series series = seriesRepository.findBySeriesId(serieId);
        series.getUserIds().remove(userId);
        seriesRepository.save(series);
    }

    public Set<String> getSubscriptionsFromUser(Long userId) {
        User user = findOrCreateByUserId(userId);
        return convertToNames(user.getSeriesIds());
    }

    private Set<String> convertToNames(Set<Integer> feedsFromUser) {
        return feedsFromUser.stream().map(feed
                -> seriesRepository.findBySeriesId(feed).getSeriesName()).collect(Collectors.toSet());
    }
}
