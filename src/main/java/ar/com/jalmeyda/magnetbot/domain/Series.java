package ar.com.jalmeyda.magnetbot.domain;

import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Juan Almeyda on 6/17/2016.
 */
public class Series {

    @Id
    private String id;

    private Integer seriesId;
    private String seriesName;
    private Set<Long> userIds;

    public Series(Integer seriesId, String seriesName) {
        this.seriesId = seriesId;
        this.seriesName = seriesName;
        this.userIds = new HashSet<>();
    }

    public Integer getSeriesId() {
        return seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public Set<Long> getUserIds() {
        return userIds;
    }

    @Override
    public String toString() {
        return seriesName;
    }
}
