package com.ggcorreia.randomjoke.builder;

import com.ggcorreia.randomjoke.model.Joke;

import java.util.function.Predicate;

/**
 * Predicate used for filtering some jokes from a list using the given criteria
 */
public class JokeFilterPredicate implements Predicate<Joke> {
    private boolean onlySafe;
    private boolean nonSexist;
    private boolean nonExplicit;

    public JokeFilterPredicate(boolean onlySafe, boolean nonSexist, boolean nonExplicit) {
        this.onlySafe = onlySafe;
        this.nonSexist = nonSexist;
        this.nonExplicit = nonExplicit;
    }

    @Override
    public boolean test(Joke joke) {
        return isOnlySafe(joke) && isNonSexist(joke) && isNonExplicit(joke);
    }

    private boolean isOnlySafe(Joke joke) {
        return joke != null && joke.isSafe() == this.onlySafe;
    }

    private boolean isNonSexist(Joke joke) {
        return joke != null && joke.getFlags().isNotSexist() == this.nonSexist;
    }

    private boolean isNonExplicit(Joke joke) {
        return joke != null && joke.getFlags().isNotExplicit() == this.nonExplicit;
    }

}
