package lv.dita.service;

import lv.dita.entity.Gig;
import lv.dita.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface GigService {

    public List<Gig> findAllGigs();

//    public List<Gig> searchGigs(String keyword);

    public Gig findGigById(Long id) throws NotFoundException;

    public void createGig (Gig gig);

    public void updateGig (Gig gig);

    public void deleteGig (Long id);

}
