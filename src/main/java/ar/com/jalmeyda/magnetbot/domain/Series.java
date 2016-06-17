package ar.com.jalmeyda.magnetbot.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by Juan Almeyda on 6/17/2016.
 */
public class Series {

    @Id
    private String id;

    private Integer seriesId;
    private String seriesName;

    public Series(Integer seriesId, String seriesName) {
        this.seriesId = seriesId;
        this.seriesName = seriesName;
    }

    public Integer getSeriesId() {
        return seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    @Override
    public String toString() {
        return seriesName;
    }
}
