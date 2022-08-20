package be.vdab.videowinkel.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(KlantRepository.class)
class KlantRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final KlantRepository repository;

    KlantRepositoryTest(KlantRepository repository) {
        this.repository = repository;
    }

    @Test
    void findKlantListByName() {
        assertThat(repository.findKlantListByName("co")).size().isEqualTo(4);
    }

    @Test
    void findById() {
        assertThat(repository.findById(1))
                .hasValueSatisfying(klant->assertThat(klant.getFamilienaam()).isEqualTo("Heiremans"));
    }
}