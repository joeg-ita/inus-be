package it.joeg.inus.be.domain.usecases;

import it.joeg.inus.be.domain.entities.Punch;
import it.joeg.inus.be.domain.entities.Registration;
import it.joeg.inus.be.domain.exceptions.InvalidApplicationIdException;
import it.joeg.inus.be.domain.port.PunchService;
import it.joeg.inus.be.domain.port.RegistrationService;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public class InsertPunchUsecase {
    
    PunchService punchService;
    RegistrationService registrationService;

    public InsertPunchUsecase(PunchService punchService, RegistrationService registrationService) {
        this.punchService = punchService;
        this.registrationService = registrationService;
    }

    public void insertOnePunch(Punch punch) throws InvalidApplicationIdException {
        // 1 verify applicationId
        Optional<Registration> reg = registrationService.findRegistrationByApplicationId(punch.getAppId());
        if(!reg.isPresent()){
            throw new InvalidApplicationIdException("Application Id NOT PRESENT!");
        }
        
        // 2 Save punch
        punchService.save(punch);
    }
    
    public void insertPunches(List<Punch> punches) throws InvalidApplicationIdException {
        // 1 verify applicationId
        for(Punch p: punches){
            Optional<Registration> reg = registrationService.findRegistrationByApplicationId(p.getAppId());
            if (!reg.isPresent()) {
                throw new InvalidApplicationIdException("Application Id NOT PRESENT!");
            }
        }
        
        // 2 Save punch
        punchService.saveMany(punches);
        
    }
}
