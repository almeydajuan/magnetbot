package ar.com.jalmeyda.magnetbot.dto;

import java.util.Set;

/**
 * Created by Juan Almeyda on 6/17/2016.
 */
public class UserDTO {

    private Long userId;
    private Set<String> seriesNames;
    private String error;

    public UserDTO(Long userId, Set<String> seriesNames, String error) {
        this.userId = userId;
        this.seriesNames = seriesNames;
        this.error = error;
    }

    public Long getUserId() {
        return userId;
    }

    public Set<String> getSeriesNames() {
        return seriesNames;
    }

    public String getError() {
        return error;
    }
}
