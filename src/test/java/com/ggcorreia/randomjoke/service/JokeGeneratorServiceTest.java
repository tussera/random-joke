package com.ggcorreia.randomjoke.service;

import com.ggcorreia.randomjoke.TestData;
import com.ggcorreia.randomjoke.config.JokeConfig;
import com.ggcorreia.randomjoke.model.Joke;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JokeGeneratorService.class)
@EnableConfigurationProperties(value = JokeConfig.class)
@AutoConfigureWebClient(registerRestTemplate = true)
@DisplayName("Joke Generator Service Test Case")
class JokeGeneratorServiceTest {

    @Autowired
    private JokeGeneratorService jokeGeneratorService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JokeConfig jokeConfig;

    private MockRestServiceServer mockServer;

    @BeforeEach
    void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    @DisplayName("Should generate a list of jokes from a external API response")
    void shouldGenerateListOfJokes() throws URISyntaxException {
        // GIVEN
        List<Joke> expectedResult = TestData.randomJokesFromTestFile;
        mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI(jokeConfig.getUrl())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(TestData.jokeApiResponseAsString)
                );
        // WHEN
        List<Joke> jokes = jokeGeneratorService.generate();
        // THEN
        mockServer.verify();
        assertThat(jokes)
                .usingRecursiveComparison()
                .isEqualTo(expectedResult);
    }
}