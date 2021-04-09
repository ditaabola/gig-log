package lv.dita.service.impl;

import lv.dita.entity.Gig;
import lv.dita.exception.NotFoundException;
import lv.dita.repositories.GigRepository;
import lv.dita.service.GigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        if (gigList.size() > 0) {
            return gigList;
        } else {
            return new ArrayList<>();
        }
    }

//    @Override
//    public List<Gig> searchGigs(String keyword) {
//        if (keyword != null) {
//            return gigRepository.search(keyword);
//        }
//        return gigRepository.findAll();
//    }

    @Override
    public Gig findGigById(Long id) throws NotFoundException {
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
        final Optional<Gig> gig = gigRepository.findById(id);
        gigRepository.deleteById(gig.get().getId());
    }

}
