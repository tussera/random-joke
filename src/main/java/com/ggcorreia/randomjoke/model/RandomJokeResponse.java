package com.ggcorreia.randomjoke.model;

public class RandomJokeResponse {
    private long id;
    private String randomJoke;

    public RandomJokeResponse(long id, String randomJoke) {
        this.id = id;
        this.randomJoke = randomJoke;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRandomJoke() {
        return randomJoke;
    }

    public void setRandomJoke(String randomJoke) {
        this.randomJoke = randomJoke;
    }
}
