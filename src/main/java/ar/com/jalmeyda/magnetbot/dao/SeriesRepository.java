package ar.com.jalmeyda.magnetbot.dao;

import ar.com.jalmeyda.magnetbot.domain.Series;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SeriesRepository extends MongoRepository<Series, String> {

    Series findBySeriesId(Integer seriesId);

    Series findBySeriesName(String seriesName);
}
