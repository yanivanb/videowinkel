package be.vdab.videowinkel.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Repository
public class ReservatieRepository {
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;

    public ReservatieRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template).withTableName("reservaties");
    }

    public void create(long klantId, Set<Long> filmId) {
        for(var id : filmId){
            insert.execute(
                    Map.of("klantId", klantId,
                            "filmId", id,
                            "reservatie", LocalDate.now())
            );
        }
    }

}
