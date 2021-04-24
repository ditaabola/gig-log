package lv.dita.service.impl;

import lv.dita.domain.Gig;
import lv.dita.exception.NotFoundException;
import lv.dita.repositories.GigRepository;
import lv.dita.service.GigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GigServiceImpl implements GigService {

    @Autowired
    private final GigRepository gigRepository;

    public GigServiceImpl(GigRepository gigRepository) {
        this.gigRepository = gigRepository;
    }

    @Override
    public List<Gig> findAllGigs() {
        List<Gig> gigList = (List<Gig>) gigRepository.findAll();

        if(!gigList.isEmpty()){
            return gigList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Gig findGigById(Long id) {
        return gigRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Gig not found with ID %d", id)));
    }

    @Override
    public void createGig(Gig gig) {
        gigRepository.save(gig);
    }

    @Override
    public void updateGig(Gig gig) {
        gigRepository.save(gig);
    }

    @Override
    public void deleteGig(Long id) {
        final Gig gig = gigRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Gig not found with ID %d", id)));

        gigRepository.deleteById(gig.getId());
    }
}
