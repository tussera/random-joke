package com.ggcorreia.randomjoke.service;

import com.ggcorreia.randomjoke.builder.JokeBuilder;
import com.ggcorreia.randomjoke.exception.JokeCriteriaNotMatchedException;
import com.ggcorreia.randomjoke.model.Joke;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Service responsible for generating a random joke based on a required criteria
 */
@Service
public class RandomJokeService {

    public static final String CRITERIA_NOT_MATCHED_EXCEPTION_MSG =
            "No Jokes could be found for the criteria [Safe, Non-Sexist, Non-Explicit]";
    private final JokeGeneratorService jokeGeneratorService;

    public RandomJokeService(JokeGeneratorService jokeGeneratorService) {
        this.jokeGeneratorService = jokeGeneratorService;
    }

    public Joke getRandomJoke() throws JokeCriteriaNotMatchedException {
        List<Joke> jokeList = JokeBuilder
                .create(jokeGeneratorService.generate())
                .onlySafe()
                .nonSexist()
                .nonExplicit()
                .build();

        if (jokeList.isEmpty()) {
            throw new JokeCriteriaNotMatchedException(CRITERIA_NOT_MATCHED_EXCEPTION_MSG);
        }

        return getShortestJoke(jokeList);
    }

    private Joke getShortestJoke(List<Joke> jokeList) {
        return jokeList.stream()
                .min(Comparator.comparingInt(joke -> joke.getJoke().length()))
                .orElseThrow();
    }

}
