package com.ggcorreia.randomjoke.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Joke {
    private long id;
    private String joke;
    private boolean safe;
    private Flags flags;

    public Joke() {
    }

    public Joke(long id, String joke, boolean safe, Flags flags) {
        this.id = id;
        this.joke = joke;
        this.safe = safe;
        this.flags = flags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }
}
