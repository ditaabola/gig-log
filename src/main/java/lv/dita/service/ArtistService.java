package lv.dita.service;

import lv.dita.domain.Artist;
import lv.dita.model.ArtistDTO;

import java.util.List;


public interface ArtistService {

    public List<ArtistDTO> findAllArtists();

    public ArtistDTO findArtistById(Long id);

    public void createArtist(ArtistDTO artistDTO);

    public void updateArtists(Long id, ArtistDTO artistDTO);

    public void deleteArtist(Long id);

   }