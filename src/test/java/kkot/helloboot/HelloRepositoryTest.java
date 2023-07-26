package kkot.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class HelloRepositoryTest {
    @Autowired HelloRepository helloRepository;

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
