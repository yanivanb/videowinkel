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
                    new Klant(result.getLong("id"), result.getString("familienaam"), result.getString("voornaam"), result.getString("straatNummer"), result.getLong("postcode"), result.getString("familienaam"));

    public KlantRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<Klant> findKlantListByName(String name) {
        try {
            var sql = """
            select id, familienaam, voornaam, straatNummer, postcode, gemeente from klanten
            where familienaam like '%"""+name+"%' order by familienaam";
            return template.query(sql, klantMapper);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }
}
