package com.ggcorreia.randomjoke.controller;

import com.ggcorreia.randomjoke.exception.JokeCriteriaNotMatchedException;
import com.ggcorreia.randomjoke.model.Joke;
import com.ggcorreia.randomjoke.model.RandomJokeResponse;
import com.ggcorreia.randomjoke.service.RandomJokeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("random-joke")
public class RandomJokeController {

    private final RandomJokeService randomJokeService;

    public RandomJokeController(RandomJokeService randomJokeService) {
        this.randomJokeService = randomJokeService;
    }

    @GetMapping
    public ResponseEntity<RandomJokeResponse> getRandomJoke() throws JokeCriteriaNotMatchedException {
        Joke randomJoke = randomJokeService.getRandomJoke();
        RandomJokeResponse response = new RandomJokeResponse(
                randomJoke.getId(),
                randomJoke.getJoke()
        );
        return ResponseEntity.ok(response);
    }

}
