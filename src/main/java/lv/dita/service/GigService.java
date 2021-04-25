package lv.dita.service;

import lv.dita.model.GigDTO;
import java.util.List;

public interface GigService {

    public List<GigDTO> findAllGigs();

    public GigDTO findGigById(Long id);

    public void createGig (GigDTO gigDTO);

    public void updateGig (Long id, GigDTO gigDTO);

    public void deleteGig (Long id);

}
