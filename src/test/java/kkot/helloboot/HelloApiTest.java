package kkot.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloApiTest {
    @Test
    void helloApi() {
        // localhost:8080/hello?name=K
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res = rest.getForEntity("http://localhost:9090/app/hello?name={name}", String.class, "K");
        // status code: 200
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        // header(content-type): text/plain
        // getFirst(컬렉션의 자료를 키로 조회하되 처음 만나는 키의 값만 반환 cf. get은 일치하는 모든 키의 값 반환)
        assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        // body: Hello, K
        assertThat(res.getBody()).isEqualTo("*Hello, K*");
    }

    @Test
    void failsHelloApi() {
        // localhost:8080/hello?name=K
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> res = rest.getForEntity("http://localhost:9090/app/hello?name=", String.class);
        // status code: 500
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
