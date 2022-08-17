package be.vdab.videowinkel.repositories;

import be.vdab.videowinkel.domain.Genre;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepository {
    private final JdbcTemplate template;
    private final RowMapper<Genre> genreMapper =
            (result, rowNum) ->
                    new Genre(result.getLong("id"), result.getString("naam"));

    public GenreRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<Genre> findAll() {
        var sql = """
        select id, naam
        from genres
        order by naam
        """;
        return template.query(sql, genreMapper);
    }

    public Optional<Genre> findById(long id) {
        try {
            var sql = """
            select id, naam from genres
            where id = ?
            """;
            return Optional.of(
                    template.queryForObject(sql, genreMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }
}
