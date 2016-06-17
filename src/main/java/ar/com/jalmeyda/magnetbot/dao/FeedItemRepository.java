package ar.com.jalmeyda.magnetbot.dao;

import ar.com.jalmeyda.magnetbot.domain.FeedItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeedItemRepository extends MongoRepository<FeedItem, String> {

    List<FeedItem> findBySeriesId(Integer seriesId);
    
}
