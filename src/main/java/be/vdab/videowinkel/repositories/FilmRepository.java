package be.vdab.videowinkel.repositories;

import be.vdab.videowinkel.domain.Film;
import be.vdab.videowinkel.exceptions.ReserveerFilmsException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class FilmRepository {
    private final JdbcTemplate template;
    private final RowMapper<Film> filmMapper =
            (result, rowNum) ->
                    new Film(result.getLong("id"), result.getLong("genreId"), result.getString("titel"), result.getLong("voorraad"), result.getLong("gereserveerd"), result.getBigDecimal("prijs"));

    public FilmRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<Film> findByGenreId(long id) {
        var sql = """
        select id, genreId,titel, voorraad, gereserveerd, prijs
        from films
        where genreId = ?
        """;
        return template.query(sql, filmMapper, id);
    }

    public Optional<Film> findById(long id) {
        try {
            var sql = """
            select id, genreId,titel, voorraad, gereserveerd, prijs
            from films
            where id = ?
            """;
            return Optional.of(
                    template.queryForObject(sql, filmMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    public List<Film> findByIds(Set<Long> ids) {
        if (ids.isEmpty()) {
            return List.of();
        }
        var sql = """
        select id, genreId,titel, voorraad, gereserveerd, prijs
        from films
        where id in (
        """
                + "?,".repeat(ids.size() - 1)
                + "?) order by id";
        return template.query(sql, filmMapper, ids.toArray());
    }

    public void reserveerFilmsByIds(Set<Long> ids) {
        if (ids.isEmpty()) {
            throw new ReserveerFilmsException("Er waren geen ids aanwezig");
        }
        else {
            var sql = """
            UPDATE films
            set gereserveerd = gereserveerd+1
            WHERE id in(
        """
                    + "?,".repeat(ids.size() - 1)
                    + "?)";
            template.update(sql, ids.toArray());
        }
    }

    public List<Film> findGereserveerdByIds(Set<Long> ids) {
        if (ids.isEmpty()) {
            return List.of();
        }
        var sql = """
        select id, genreId,titel, voorraad, gereserveerd, prijs
        from films
        where id in (
        """
                + "?,".repeat(ids.size() - 1)
                + "?) AND voorraad <= gereserveerd order by id";
        return template.query(sql, filmMapper, ids.toArray());
    }


}
