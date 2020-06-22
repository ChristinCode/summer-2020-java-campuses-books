package org.wcci.campuslibraries.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void homePageShouldReturnA200ResponseCodeAndHtml() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:"+port+"/", String.class);
        HttpStatus status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.OK);
        MediaType mediaType = response.getHeaders().getContentType();
        assertThat(mediaType).isEqualTo(MediaType.valueOf("text/html;charset=UTF-8"));
    }
    @Test
    public void columbusCampusPageShouldReturnA200ResponseCodeAndHtml() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:"+port+"/campuses/Columbus", String.class);
        HttpStatus status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.OK);
        MediaType mediaType = response.getHeaders().getContentType();
        assertThat(mediaType).isEqualTo(MediaType.valueOf("text/html;charset=UTF-8"));
    }
    @Test
    public void headFirstJavaBookPageShouldReturnA200ResponseCodeAndHtml() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:"+port+"/books/Head First Java", String.class);
        HttpStatus status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.OK);
        MediaType mediaType = response.getHeaders().getContentType();
        assertThat(mediaType).isEqualTo(MediaType.valueOf("text/html;charset=UTF-8"));
    }
    @Test
    public void kathySierraAuthorPageShouldReturnA200ResponseCodeAndHtml() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:"+port+"/authors/3", String.class);
        HttpStatus status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.OK);
        MediaType mediaType = response.getHeaders().getContentType();
        assertThat(mediaType).isEqualTo(MediaType.valueOf("text/html;charset=UTF-8"));
    }
    @Test
    public void authorPostShouldReturnA302ResponseCode() throws Exception {
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:"+port+"/authors/add", null,
                String.class, Map.of("firstName", "Testy", "lastName","Tester"));
        HttpStatus status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.FOUND);
    }
    @Test
    public void campusPostShouldReturnA302ResponseCode() throws Exception {
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:"+port+"/campuses/add", null,
                String.class, Map.of("firstName", "Testy", "lastName","Tester"));
        HttpStatus status = response.getStatusCode();
        assertThat(status).isEqualTo(HttpStatus.FOUND);
    }
}