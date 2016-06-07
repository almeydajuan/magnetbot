package ar.com.jalmeyda.magnetbot.service;

import ar.com.jalmeyda.magnetbot.dao.UserFeedsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by Juan Almeyda on 6/7/2016.
 */
@Component
public class UserSubscribeService {

    @Autowired
    private UserFeedsDao userFeedsDao;

    public void subscribeToFeed(Long userId, Integer feedId) {
        Set<Integer> feedsForUser = userFeedsDao.getFeedsForUser(userId);
        feedsForUser.add(feedId);
        userFeedsDao.updateFeedsForUser(userId, feedsForUser);
    }

    public void unsubscribeFromFeed(Long userId, Integer feedId) {
        Set<Integer> feedsForUser = userFeedsDao.getFeedsForUser(userId);
        feedsForUser.remove(feedId);
        userFeedsDao.updateFeedsForUser(userId, feedsForUser);
    }

    public Set<Integer> getFeedsFromUser(Long userId) {
        return userFeedsDao.getFeedsForUser(userId);
    }
}
