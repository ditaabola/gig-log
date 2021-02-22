package lv.dita.service;

import lv.dita.models.Artist;
import java.util.List;
import java.util.Optional;

public interface ArtistService {

    public List<Artist> findAllArtists();

    public Optional<Artist> findArtistById(Long id);

    public void createArtist(Artist artist);

    public void updateArtists(Artist artist);

    public void deleteArtist(Long id);
}
