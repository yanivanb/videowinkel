package be.vdab.videowinkel.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;


@JdbcTest
@Import(GenreRepository.class)
class GenreRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    private final GenreRepository repository;

    GenreRepositoryTest(GenreRepository repository) {
        this.repository = repository;
    }

    @Test
    void findAll() {
        assertThat(repository.findAll()).size().isEqualTo(countRowsInTable("genres"));
    }

    @Test
    void findById() {
        assertThat(repository.findById(1))
                .hasValueSatisfying(genre->assertThat(genre.getNaam()).isEqualTo("Aktiefilm"));
    }
}