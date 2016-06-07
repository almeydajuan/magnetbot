package ar.com.jalmeyda.magnetbot.dao;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Juan Almeyda on 6/7/2016.
 */
@Component
public class InMemoryUserFeedsDao implements UserFeedsDao {

    private Map<Integer, Set<Long>> usersForFeed = new HashMap<>();
    private Map<Long, Set<Integer>> feedsForUsers = new HashMap<>();

    @Override
    public Set<Integer> getFeedsForUser(Long userId) {
        Set<Integer> feeds = feedsForUsers.get(userId);
        if (feeds == null) {
            feeds = new HashSet<>();
        }
        return feeds;
    }

    @Override
    public Set<Long> getUsersFromFeed(Integer feedItem) {
        Set<Long> users = usersForFeed.get(feedItem);
        if (users == null) {
            users = new HashSet<>();
        }
        return users;
    }

    @Override
    public void updateFeedsForUser(Long userId, Set<Integer> feedItems) {
        feedsForUsers.put(userId, feedItems);
    }

    @Override
    public void updateUsersForFeed(Integer feedItem, Set<Long> userIds) {
        usersForFeed.put(feedItem, userIds);
    }
}
