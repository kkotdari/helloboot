package kkot.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HelloBootTest
public class HelloRepositoryTest {
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired HelloRepository helloRepository;

    @BeforeEach
    void init() {
        System.out.println("jdbcTemplate: " + jdbcTemplate);
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }

    @Test
    void findHelloFailed() {
        Assertions.assertThat(helloRepository.findHello("kkot")).isNull();
    }

    @Test
    void increaseCount() {
        Assertions.assertThat(helloRepository.countOf("kkot")).isEqualTo(0);

        helloRepository.increaseCount("kkot");
        Assertions.assertThat(helloRepository.countOf("kkot")).isEqualTo(1);

        helloRepository.increaseCount("kkot");
        Assertions.assertThat(helloRepository.countOf("kkot")).isEqualTo(2);
    }
}
