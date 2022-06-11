package com.ggcorreia.randomjoke.service;

import com.ggcorreia.randomjoke.config.JokeConfig;
import com.ggcorreia.randomjoke.model.Joke;
import com.ggcorreia.randomjoke.model.JokeList;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Service responsible for generating a list of jokes from an external API
 */
@Service
public class JokeGeneratorService {

    private final JokeConfig jokeConfig;
    private final RestTemplate restTemplate;

    public JokeGeneratorService(JokeConfig jokeConfig, RestTemplate restTemplate) {
        this.jokeConfig = jokeConfig;
        this.restTemplate = restTemplate;
    }

    public List<Joke> generate() {
        JokeList jokeList = restTemplate.getForObject(jokeConfig.getUrl(), JokeList.class);
        return jokeList.getJokes();
    }
}
