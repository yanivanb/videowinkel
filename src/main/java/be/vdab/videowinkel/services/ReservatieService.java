package be.vdab.videowinkel.services;

import be.vdab.videowinkel.repositories.ReservatieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class ReservatieService {
    private final ReservatieRepository reservatieRepository;

    public ReservatieService(ReservatieRepository reservatieRepository) {
        this.reservatieRepository = reservatieRepository;
    }

    public long create(long klantId, long filmId){
        return reservatieRepository.create(klantId, filmId);
    }

    public void create(long klantId, Set<Long> filmId){
        reservatieRepository.create(klantId, filmId);
    }

}
