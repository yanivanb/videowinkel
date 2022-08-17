package be.vdab.videowinkel.services;

import be.vdab.videowinkel.domain.Film;
import be.vdab.videowinkel.repositories.FilmRepository;
import be.vdab.videowinkel.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class FilmService {
    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;


    public FilmService(FilmRepository filmRepository, GenreRepository genreRepository) {
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
    }

    public Optional<Film> findById(long id) {
        return filmRepository.findById(id);
    }

    public List<Film> findByIds(Set<Long> id) {
        return filmRepository.findByIds(id);
    }

    public List<Film> findByGenreId(long id) {
        return filmRepository.findByGenreId(id);
    }

}
