package ar.com.jalmeyda.magnetbot.domain;

import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Juan Almeyda on 6/17/2016.
 */
public class User {

    @Id
    private String id;

    private Long userId;
    private Set<Integer> seriesIds;

    public User(Long userId) {
        this.userId = userId;
        this.seriesIds = new HashSet<>();
    }

    public Long getUserId() {
        return userId;
    }

    public Set<Integer> getSeriesIds() {
        return seriesIds;
    }
}
