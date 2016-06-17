package ar.com.jalmeyda.magnetbot.domain;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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
    private DateTime lastSuccessfulSync;

    public Series(Integer seriesId, String seriesName) {
        this.seriesId = seriesId;
        this.seriesName = seriesName;
        this.userIds = new HashSet<>();
        this.lastSuccessfulSync = DateTime.now(DateTimeZone.UTC);
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

    public DateTime getLastSuccessfulSync() {
        return lastSuccessfulSync;
    }

    public void setLastSuccessfulSync(DateTime lastSuccessfulSync) {
        this.lastSuccessfulSync = lastSuccessfulSync;
    }

    @Override
    public String toString() {
        return seriesName;
    }
}
