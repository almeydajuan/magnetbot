package ar.com.jalmeyda.magnetbot.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;

/**
 * Created by Juan Almeyda on 6/3/2016.
 */
public class FeedItem {

    @Id
    private String id;

    private String title;
    private String description;
    private String link;
    private String pubDate;
    private Integer seriesId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FeedItem) {
            FeedItem other = (FeedItem) obj;
            return new EqualsBuilder().append(getTitle(), other.getTitle()).append(getSeriesId(), other.getSeriesId()).isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getTitle()).append(getSeriesId()).toHashCode();
    }

    @Override
    public String toString() {
        return title;
    }
}

