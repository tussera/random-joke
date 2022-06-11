package com.ggcorreia.randomjoke.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper class for the response result of the external Joke API
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JokeList {
    private List<Joke> jokes;

    public JokeList() {
        jokes = new ArrayList<>();
    }

    public JokeList(List<Joke> jokes) {
        this.jokes = jokes;
    }

    public List<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(List<Joke> jokes) {
        this.jokes = jokes;
    }
}
