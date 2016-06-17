package ar.com.jalmeyda.magnetbot.spring;

import ar.com.jalmeyda.magnetbot.service.SerieIdResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Juan Almeyda on 6/7/2016.
 */
@Configuration
public class SpringConfiguration {

    @Value("${seriesIndex}")
    private String seriesIndex;

    @Bean
    public SerieIdResolver serieIdResolver() throws IOException {
        Map<Integer, String> seriesNameById = new HashMap<>();
        Map<String, Integer> seriesIdByName = new HashMap<>();
        Properties properties = new Properties();
        properties.load(SpringConfiguration.class.getClassLoader().getResourceAsStream(seriesIndex));
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            seriesNameById.put(Integer.valueOf(key), value);
            seriesIdByName.put(value, Integer.valueOf(key));
        }
        return new SerieIdResolver(seriesNameById, seriesIdByName);
    }
}
