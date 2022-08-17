package be.vdab.videowinkel.services;

import be.vdab.videowinkel.domain.Genre;
import be.vdab.videowinkel.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
    public Optional<Genre> findById(long id) {
        return genreRepository.findById(id);
    }
}
