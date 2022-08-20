package be.vdab.videowinkel.repositories;


import be.vdab.videowinkel.domain.Film;
import be.vdab.videowinkel.exceptions.ReserveerFilmsException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@JdbcTest
@Import(FilmRepository.class)
class FilmRepositoryTest {
    private final FilmRepository repository;

    FilmRepositoryTest(FilmRepository repository) {
        this.repository = repository;
    }

    @Test
    void findByGenreId() {
        assertThat(repository.findByGenreId(1)).hasSize(1);
        assertThat(repository.findByGenreId(2)).hasSize(7);
    }

    @Test
    void findById() {
        assertThat(repository.findById(1))
                .hasValueSatisfying(film->assertThat(film.getTitel()).isEqualTo("Raiders of the lost ark"));
    }

    @Test
    void findByIds() {
        long id1 = 3;
        long id2 = 5;
        assertThat(repository.findByIds(Set.of(id1, id2)))
                .extracting(Film::getId)
                .containsOnly(id1, id2)
                .isSorted();
    }

    @Test
    void reserveerFilmsByIdsGeeftFout() {
        Set<Long> ids = new HashSet<>();
        assertThatExceptionOfType(ReserveerFilmsException.class).isThrownBy(
                () -> repository.reserveerFilmsByIds(ids)
        );
    }

    @Test
    void findGereserveerdByIds() {
        long id1 = 26;
        long id2 = 15;
        long id3 = 14;
        long id4 = 9;
        assertThat(repository.findGereserveerdByIds(Set.of(id1, id2, id3, id4)))
                .extracting(Film::getId)
                .containsOnly(id1)
                .isSorted();
    }
}