package ar.com.jalmeyda.magnetbot.service;

import ar.com.jalmeyda.magnetbot.dao.SeriesRepository;
import ar.com.jalmeyda.magnetbot.domain.Series;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Juan Almeyda on 6/17/2016.
 */
@Component
public class SeriesService {

    @Resource
    private SeriesRepository seriesRepository;

    public Set<String> getAllSeries() {
        return seriesRepository.findAll().stream().map(series -> series.getSeriesName()).collect(Collectors.toSet());
    }

    public Integer getSeriesId(String serieName) {
        Series series = seriesRepository.findBySeriesName(serieName);
        if (series == null)
            throw new RuntimeException("Serie: " + serieName + " was not found");
        return series.getSeriesId();
    }
}
