package ar.com.jalmeyda.magnetbot.controller;

import ar.com.jalmeyda.magnetbot.dao.SeriesRepository;
import ar.com.jalmeyda.magnetbot.domain.Series;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Juan Almeyda on 6/17/2016.
 */
@RestController
public class AdminController {

    @Value("${minDays}")
    private Integer minDays;

    @Value("${maxDays}")
    private Integer maxDays;

    @Resource
    private SeriesRepository seriesRepository;

    @RequestMapping("/update")
    public Series forceUpdate(Integer serieId) {
        Series series = seriesRepository.findBySeriesId(serieId);
        series.setLastSuccessfulSync(DateTime.now(DateTimeZone.UTC).minusDays(minDays + 1));
        seriesRepository.save(series);
        return series;
    }

    @RequestMapping("/finish")
    public Series finish(Integer serieId) {
        Series series = seriesRepository.findBySeriesId(serieId);
        series.setLastSuccessfulSync(DateTime.now(DateTimeZone.UTC).minusDays(maxDays + 1));
        seriesRepository.save(series);
        return series;
    }
}
