package lv.dita.service;

import lv.dita.domain.Artist;
import lv.dita.model.ArtistDTO;

import java.util.List;


public interface ArtistService {

    public List<Artist> findAllArtists();

    Artist findArtistById(Long id);

    public void createArtist(Artist artist);

    public void updateArtists(Artist artist);

    public void deleteArtist(Long id);

   }