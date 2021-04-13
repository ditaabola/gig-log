package lv.dita.service;

import lv.dita.model.Artist;
import lv.dita.exception.NotFoundException;
import java.util.List;


public interface ArtistService {

    public List<Artist> findAllArtists();

    Artist findArtistById(Long id) throws NotFoundException;

    public Artist createArtist(Artist artist);

    public Artist updateArtists(Artist artist);

    public void deleteArtist(Long id);

   }