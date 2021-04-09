package lv.dita.service;

import lv.dita.entity.Artist;
import lv.dita.exception.NotFoundException;
import java.util.List;

public interface ArtistService {

    public List<Artist> findAllArtists();

    public Artist findArtistById(Long id) throws NotFoundException;

    public void createArtist(Artist artist);

    public void updateArtists(Artist artist);

    public void deleteArtist(Long id);
}