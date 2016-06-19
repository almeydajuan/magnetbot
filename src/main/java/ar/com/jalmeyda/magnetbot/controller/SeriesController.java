package ar.com.jalmeyda.magnetbot.controller;

import ar.com.jalmeyda.magnetbot.domain.FeedItem;
import ar.com.jalmeyda.magnetbot.dto.EpisodeDTO;
import ar.com.jalmeyda.magnetbot.service.SeriesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Juan Almeyda on 6/19/2016.
 */
@RestController
public class SeriesController {

    @Resource
    private SeriesService seriesService;

    @RequestMapping("/latest")
    public List<EpisodeDTO> getLatestEpisode(String seriesName) {
        Integer seriesId = seriesService.getSeriesId(seriesName);
        List<FeedItem> latestEpisode = seriesService.getLatestEpisode(seriesId);
        return latestEpisode.stream().map(episode -> new EpisodeDTO(episode.getTitle(), episode.getLink()))
                .collect(Collectors.toList());
    }
}
