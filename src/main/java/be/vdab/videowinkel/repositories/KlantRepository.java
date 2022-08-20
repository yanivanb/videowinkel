package be.vdab.videowinkel.repositories;

import be.vdab.videowinkel.domain.Genre;
import be.vdab.videowinkel.domain.Klant;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class KlantRepository {
    private final JdbcTemplate template;
    private final RowMapper<Klant> klantMapper =
            (result, rowNum) ->
                    new Klant(result.getLong("id"), result.getString("voornaam"), result.getString("familienaam"), result.getString("straatNummer"), result.getLong("postcode"), result.getString("gemeente"));

    public KlantRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<Klant> findKlantListByName(String name) {
        try {
            //name = "'%"+name+"%'";
            var sql = """            
            select id, familienaam, voornaam, straatNummer, postcode, gemeente from klanten
            where familienaam like '%"""+name+"%' order by familienaam";
            return template.query(sql, klantMapper);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    public Optional<Klant> findById(long id) {
        try {
            var sql = """
            select id, familienaam, voornaam, straatNummer, postcode, gemeente from klanten
            where id = ?
            """;
            return Optional.of(
                    template.queryForObject(sql, klantMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }
}
