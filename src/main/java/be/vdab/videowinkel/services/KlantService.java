package be.vdab.videowinkel.services;

import be.vdab.videowinkel.domain.Klant;
import be.vdab.videowinkel.repositories.KlantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class KlantService {

    private final KlantRepository klantRepository;

    public KlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    public List<Klant> findKlantListByName(String name) {
        return klantRepository.findKlantListByName(name);
    }

    public Optional<Klant> findById(long id) {
        return klantRepository.findById(id);
    }
}
