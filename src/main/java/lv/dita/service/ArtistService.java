package lv.dita.service;

import lv.dita.model.Artist;
import lv.dita.exception.NotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ArtistService {

    public List<Artist> findAllArtists();

    Artist findArtistById(Long id) throws NotFoundException;

    public Artist createArtist(Artist artist);

    public Artist updateArtists(Artist artist);

    public void deleteArtist(Long id);

    Page<Artist> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}