package ar.com.jalmeyda.magnetbot.dao;

import java.util.Set;

/**
 * Created by Juan Almeyda on 6/7/2016.
 */
public interface UserFeedsDao {

    Set<Integer> getFeedsForUser(Long userId);

    Set<Long> getUsersFromFeed(Integer feedItem);

    void updateFeedsForUser(Long userId, Set<Integer> feedItems);

    void updateUsersForFeed(Integer feedItem, Set<Long> userIds);
}
