package com.ggcorreia.randomjoke.service;

import com.ggcorreia.randomjoke.TestData;
import com.ggcorreia.randomjoke.exception.JokeCriteriaNotMatchedException;
import com.ggcorreia.randomjoke.model.Joke;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = RandomJokeService.class)
@DisplayName("Random Joke Service Test Case")
class RandomJokeServiceTest {

    @MockBean
    private JokeGeneratorService jokeGeneratorService;

    @Autowired
    private RandomJokeService randomJokeService;

    @Test
    @DisplayName("Should generate a random joke matching all required criteria")
    void shouldGenerateRandomJokeMatchingAllRequiredCriteria() throws JokeCriteriaNotMatchedException {
        // GIVEN
        Joke expectedRandomJoke = TestData.shortestJokeMatchingAllCriteria;
        when(jokeGeneratorService.generate()).thenReturn(TestData.randomJokes);
        // WHEN
        Joke randomJoke = randomJokeService.getRandomJoke();
        // THEN
        assertThat(randomJoke)
                .usingRecursiveComparison()
                .isEqualTo(expectedRandomJoke);
    }

    @Test
    @DisplayName("Should throw JokeCriteriaNotMatchedException when no jokes with matching criteria")
    void shouldThrowExceptionWhenNoJokesMatchingRequiredCriteria() {
        // GIVEN
        when(jokeGeneratorService.generate()).thenReturn(TestData.onlyNotSafeJokes);
        // THEN
        assertThatExceptionOfType(JokeCriteriaNotMatchedException.class)
                .isThrownBy(() -> randomJokeService.getRandomJoke());
    }
}