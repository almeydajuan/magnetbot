package ar.com.jalmeyda.magnetbot.service;

import java.util.Map;
import java.util.Set;

/**
 * Created by Juan Almeyda on 6/7/2016.
 */
public class SerieIdResolver {

    private final Map<Integer, String> seriesNameById;
    private final Map<String, Integer> seriesIdByName;

    public SerieIdResolver(Map<Integer, String> seriesNameById, Map<String, Integer> seriesIdByName) {
        this.seriesNameById = seriesNameById;
        this.seriesIdByName = seriesIdByName;
    }

    public String getSerieNameFromId(Integer serieId) {
        return seriesNameById.get(serieId);
    }

    public Integer getSerieIdFromName(String serieName) {
        return seriesIdByName.get(serieName);
    }

    public Set<String> getAllSerieNames() {
        return seriesIdByName.keySet();
    }

    public Set<Integer> getAllSerieIds() {
        return seriesNameById.keySet();
    }
}
