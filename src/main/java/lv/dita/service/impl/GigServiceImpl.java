package lv.dita.service.impl;

import lv.dita.models.Gig;
import lv.dita.repositories.GigRepository;
import lv.dita.service.GigService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GigServiceImpl implements GigService {

    private final GigRepository gigRepository;

    public GigServiceImpl(GigRepository gigRepository) {
        this.gigRepository = gigRepository;
    }

    @Override
    public List<Gig> findAllGigs() {
        return gigRepository.findAll();
    }

    @Override
    public List<Gig> searchGigs(String keyword) {
        if (keyword != null) {
            return gigRepository.search(keyword);
        }
        return gigRepository.findAll();
    }

    @Override
    public Optional<Gig> findGigById(Long id) {
        return gigRepository.findById(id);
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
        final Optional<Gig> gig = gigRepository.findById(id);

        gigRepository.deleteById(gig.get().getId());
    }

}
