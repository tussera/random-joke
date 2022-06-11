# Simple Random Joke Generator API

### Reference Documentation :book:

The API fetches some jokes from the external API `https://v2.jokeapi.dev/` and return a single random joke matching the following criteria:

- Safe to display
- Not sexist
- Not explicit

If more than one is found, then it returns the shortest one.

#### Basic functionalities

- Generate Random Joke

### Tech Stack :technologist:

- JAVA 11
- Spring Boot (web, test)
- Gradle
- JUnit5 + AssertJ
- Docker

### Build, compile and test :hammer:

The following command can be used to compile, test and build the application.

```
./gradlew clean build
```

### Test Suite :white_check_mark:

All layers of the application are being tested with the below test classes:

- `JokeBuilderTest`
- `RandomJokeControllerTest`
- `JokeGeneratorServiceTest`
- `RandomJokeServiceTest`

### Running :rocket:

The application can be started using docker. For this follow the next steps:

- build application
```
./gradlew clean build
```
- build a docker image
```
docker build -t ggcorreia/random-joke-api .
```
- run
```
docker run -p 8080:8080 ggcorreia/random-joke-api
```

Done! 

Now you can generate a Random Joke using

```
curl --request GET 'localhost:8080/random-joke'
```

Enjoy! :runner: