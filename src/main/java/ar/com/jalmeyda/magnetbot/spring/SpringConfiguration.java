package ar.com.jalmeyda.magnetbot.spring;

import ar.com.jalmeyda.magnetbot.dao.SeriesRepository;
import ar.com.jalmeyda.magnetbot.domain.Series;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
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

    @Value("${maxDays}")
    private Integer maxDays;

    @Autowired
    private SeriesRepository seriesRepository;

    @PostConstruct
    public void init() throws IOException {
        Properties properties = new Properties();
        properties.load(SpringConfiguration.class.getClassLoader().getResourceAsStream(seriesFile));
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            if (seriesRepository.findBySeriesId(Integer.valueOf(key)) == null) {
                Series series = new Series(Integer.valueOf(key), value);
                series.setLastSuccessfulSync(DateTime.now(DateTimeZone.UTC).minusDays(maxDays + 1));
                seriesRepository.save(series);
            }
        }
    }

}
