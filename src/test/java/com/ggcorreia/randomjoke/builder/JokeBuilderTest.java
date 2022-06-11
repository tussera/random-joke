package com.ggcorreia.randomjoke.builder;

import com.ggcorreia.randomjoke.TestData;
import com.ggcorreia.randomjoke.model.Joke;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JokeBuilder Test Case")
public class JokeBuilderTest {

    private List<Joke> jokeList;

    @BeforeEach
    void setup() {
        jokeList = TestData.randomJokes;
    }

    @Test
    @DisplayName("Build only safe jokes")
    void shouldReturnOnlySafeJokes() {
        // WHEN
        List<Joke> safeJokes = JokeBuilder.create(jokeList)
                .onlySafe()
                .build();
        // THEN
        assertThat(safeJokes).allSatisfy(joke -> assertThat(joke.isSafe()).isTrue());
    }

    @Test
    @DisplayName("Build only non-sexist jokes")
    void shouldReturnOnlyNonSexistJokes() {
        // WHEN
        List<Joke> nonSexist = JokeBuilder.create(jokeList)
                .nonSexist()
                .build();
        // THEN
        assertThat(nonSexist).allSatisfy(joke -> assertThat(joke.getFlags().isSexist()).isFalse());
    }

    @Test
    @DisplayName("Build only non-explicit jokes")
    void shouldReturnOnlyNonExplicitJokes() {
        // WHEN
        List<Joke> nonExplicit = JokeBuilder.create(jokeList)
                .nonExplicit()
                .build();
        // THEN
        assertThat(nonExplicit).allSatisfy(joke -> assertThat(joke.getFlags().isExplicit()).isFalse());
    }
}
