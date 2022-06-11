package com.ggcorreia.randomjoke.builder;

import com.ggcorreia.randomjoke.model.Joke;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Builder responsible for creating a List of Jokes following some criteria
 */
public final class JokeBuilder {
    private final List<Joke> jokeList;
    private boolean onlySafe = false;
    private boolean nonSexist = false;
    private boolean nonExplicit = false;

    private JokeBuilder(List<Joke> jokeList) {
        this.jokeList = jokeList;
    }

    public static JokeBuilder create(List<Joke> jokeList) {
        return new JokeBuilder(jokeList);
    }

    public JokeBuilder onlySafe() {
        this.onlySafe = true;
        return this;
    }

    public JokeBuilder nonSexist() {
        this.nonSexist = true;
        return this;
    }

    public JokeBuilder nonExplicit() {
        this.nonExplicit = true;
        return this;
    }

    public List<Joke> build() {
        return jokeList.stream()
                .filter(new JokeFilterPredicate(onlySafe, nonSexist, nonExplicit))
                .collect(Collectors.toList());
    }
}
