package lv.dita.service.impl;

import lv.dita.model.Artist;
import lv.dita.exception.NotFoundException;
import lv.dita.repositories.ArtistRepository;
import lv.dita.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> findAllArtists() {
        List<Artist> artistList = (List<Artist>) artistRepository.findAll();

        if(artistList.size() > 0) {
            return artistList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Artist findArtistById(Long id) throws NotFoundException {
        Optional<Artist> optional = artistRepository.findById(id);
        Artist artist = null;
        if (optional.isPresent()) {
            artist = optional.get();
        } else {
            throw new NotFoundException("Artist not found for id :: " + id);
        }
        return artist;
    }

    @Override
    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public Artist updateArtists(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public Artist deleteArtist(Long id) {
        Artist artist = null;
        Optional optional = artistRepository.findById(id);
        if (optional.isPresent()) {
            artist = artistRepository.findById(id).get();
            artistRepository.deleteById(id);
        }
        return artist;
    }

    @Override
    public Page<Artist> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.artistRepository.findAll(pageable);
    }
}
