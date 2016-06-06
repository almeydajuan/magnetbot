package ar.com.jalmeyda.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by Juan Almeyda on 6/3/2016.
 */
public class FeedItem {

    private String title;
    private String description;
    private String link;
    private String pubDate;

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FeedItem) {
            return new EqualsBuilder().append(getTitle(), ((FeedItem) obj).getTitle()).isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getTitle()).toHashCode();
    }
}
