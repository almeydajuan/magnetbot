package ar.com.jalmeyda.magnetbot.dto;

/**
 * Created by Juan Almeyda on 6/19/2016.
 */
public class EpisodeDTO {

    private final String title;
    private final String link;

    public EpisodeDTO(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}
