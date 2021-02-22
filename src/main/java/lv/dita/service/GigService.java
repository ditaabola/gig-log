package lv.dita.service;

import lv.dita.models.Artist;
import lv.dita.models.Gig;

import java.util.List;
import java.util.Optional;

public interface GigService {

    public List<Gig> findAllGigs();

    public List<Gig> searchGigs(String keyword);

    public Optional<Gig> findGigById(Long id);

    public void createGig (Gig gig);

    public void updateGig (Gig gig);

    public void deleteGig (Long id);

}
