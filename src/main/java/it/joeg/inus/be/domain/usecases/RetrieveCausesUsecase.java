package it.joeg.inus.be.domain.usecases;

import it.joeg.inus.be.domain.entities.Causes;
import it.joeg.inus.be.domain.port.CausesService;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class RetrieveCausesUsecase {

    CausesService causesService;
    
    public RetrieveCausesUsecase(CausesService causesService) {
        this.causesService = causesService;
    }

    public List<Causes> getCauses(Optional<String> venueId){
        return causesService.retrieveCauses(venueId);
    }
    
}
