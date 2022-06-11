package com.ggcorreia.randomjoke;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggcorreia.randomjoke.model.Flags;
import com.ggcorreia.randomjoke.model.Joke;
import com.ggcorreia.randomjoke.model.JokeList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class TestData {
    public static final String JOKE_API_RESPONSE_TEST_FILE_NAME = "externalJokeApiResponseTest.json";
    public static final Flags onlySexistFlag = new Flags(true, false);
    public static final Flags onlyExplicitFlag = new Flags(false, true);
    public static final Flags sexistAndSexistFlag = new Flags(true, true);
    public static final Flags notSexistAndNotExplicit = new Flags(false, false);
    public static final String jokeApiResponseAsString = getResponseAsString();
    public static final List<Joke> randomJokesFromTestFile = generateJokesFromTestFile();
    public static final List<Joke> randomJokes = generateRandomJokes();
    public static final List<Joke> onlyNotSafeJokes = randomJokes.stream()
            .map(joke -> new Joke(joke.getId(), joke.getJoke(), false, joke.getFlags()))
            .collect(Collectors.toList());
    public static final Joke shortestJokeMatchingAllCriteria = randomJokes.stream()
            .filter(joke -> joke.getId() == 2L)
            .findFirst()
            .get();

    public static String getResponseAsString() {
        ClassLoader classLoader = TestData.class.getClassLoader();
        try {
            return Files.readString(Paths.get(classLoader.getResource(JOKE_API_RESPONSE_TEST_FILE_NAME).toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Joke> generateJokesFromTestFile() {
        JokeList jokeList;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ClassLoader classLoader = TestData.class.getClassLoader();

        try {
            URL url = classLoader.getResource(JOKE_API_RESPONSE_TEST_FILE_NAME);
            jokeList = mapper.readValue(url, JokeList.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jokeList.getJokes();
    }

    private static List<Joke> generateRandomJokes() {
        List<Joke> result = new ArrayList<>();
        // Safes
        Joke joke1 = new Joke(
                1L,
                "Super Funny Joke 1",
                true,
                sexistAndSexistFlag
        );
        result.add(joke1);

        Joke joke2 = new Joke(
                2L,
                "Shortest Funny!",
                true,
                notSexistAndNotExplicit
        );
        result.add(joke2);

        Joke joke3 = new Joke(
                3L,
                "Quite Funny Joke 3",
                true,
                onlyExplicitFlag
        );
        result.add(joke3);

        Joke joke4 = new Joke(
                4L,
                "Not Funny Joke 4",
                true,
                onlySexistFlag
        );
        result.add(joke4);

        Joke joke5 = new Joke(
                5L,
                "So So Funny Joke 5",
                true,
                notSexistAndNotExplicit
        );
        result.add(joke5);

        // Not safes
        Joke joke6 = new Joke(
                6L,
                "Super Funny Joke 6",
                false,
                sexistAndSexistFlag
        );
        result.add(joke6);

        Joke joke7 = new Joke(
                7L,
                "Not that Funny Joke 7",
                false,
                notSexistAndNotExplicit
        );
        result.add(joke7);

        Joke joke8 = new Joke(
                8L,
                "Quite Funny Joke 8",
                false,
                onlyExplicitFlag
        );
        result.add(joke8);

        Joke joke9 = new Joke(
                9L,
                "Not Funny Joke 9",
                false,
                onlySexistFlag
        );
        result.add(joke9);

        Joke joke10 = new Joke(
                10L,
                "So So Funny Joke 10",
                false,
                notSexistAndNotExplicit
        );
        result.add(joke10);

        return result;
    }
}
