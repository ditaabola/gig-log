package lv.dita.service;

import lv.dita.domain.Gig;
import lv.dita.exception.NotFoundException;

import java.util.List;

public interface GigService {

    public List<Gig> findAllGigs();

    public Gig findGigById(Long id) throws NotFoundException;

    public void createGig (Gig gig);

    public void updateGig (Gig gig);

    public void deleteGig (Long id);

}
