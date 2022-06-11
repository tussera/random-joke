package com.ggcorreia.randomjoke.controller;

import com.ggcorreia.randomjoke.TestData;
import com.ggcorreia.randomjoke.exception.JokeCriteriaNotMatchedException;
import com.ggcorreia.randomjoke.model.Joke;
import com.ggcorreia.randomjoke.service.RandomJokeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RandomJokeController.class)
class RandomJokeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RandomJokeService randomJokeService;

    @Test
    @DisplayName("Should successfully return a Random Joke")
    void shouldSuccessfullyReturnRandomJoke() throws Exception {
        // GIVEN
        Joke expectedRandomJoke = TestData.shortestJokeMatchingAllCriteria;
        when(randomJokeService.getRandomJoke()).thenReturn(expectedRandomJoke);
        // THEN
        mockMvc.perform(get("/random-joke"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expectedRandomJoke.getId()))
                .andExpect(jsonPath("$.randomJoke").value(expectedRandomJoke.getJoke()));
    }

    @Test
    @DisplayName("Should return NOT_FOUND when no jokes for the required criteria")
    void shouldReturnNotFoundWhenNoJokesForRequiredCriteria() throws Exception {
        // GIVEN
        when(randomJokeService.getRandomJoke()).thenThrow(
                new JokeCriteriaNotMatchedException(RandomJokeService.CRITERIA_NOT_MATCHED_EXCEPTION_MSG)
        );
        // THEN
        mockMvc.perform(get("/random-joke"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(RandomJokeService.CRITERIA_NOT_MATCHED_EXCEPTION_MSG));
    }

}