package ar.com.jalmeyda.magnetbot.spring;

import ar.com.jalmeyda.magnetbot.dao.SeriesRepository;
import ar.com.jalmeyda.magnetbot.domain.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Juan Almeyda on 6/7/2016.
 */
@Configuration
public class SpringConfiguration {

    @Value("${seriesFile}")
    private String seriesFile;

    @Autowired
    private SeriesRepository seriesRepository;

    @PostConstruct
    public void init() throws IOException {
        Properties properties = new Properties();
        properties.load(SpringConfiguration.class.getClassLoader().getResourceAsStream(seriesFile));
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            if (seriesRepository.findBySeriesId(Integer.valueOf(key)) == null) {
                seriesRepository.save(new Series(Integer.valueOf(key), value));
            }
        }
    }

}
