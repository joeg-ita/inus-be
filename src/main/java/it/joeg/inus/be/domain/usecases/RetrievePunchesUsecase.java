package it.joeg.inus.be.domain.usecases;

import it.joeg.inus.be.domain.entities.Punch;
import it.joeg.inus.be.domain.port.PunchService;
import java.util.List;
import java.util.Optional;

/**
 * Usecase to retrieve users punches
 */
public class RetrievePunchesUsecase {
    
    PunchService punchService;
    
    int defaultLen = 6;

    public RetrievePunchesUsecase(PunchService punchService) {
        this.punchService = punchService;
    }
    
    public List<Punch> retrievePunches(String badgeId){
        return punchService.retrievePunches(badgeId);
    }
    
    public Optional<Punch> retrieveLastPunch(String badgeId){
        return punchService.retrieveLastPunch(badgeId);
    }
   
}
