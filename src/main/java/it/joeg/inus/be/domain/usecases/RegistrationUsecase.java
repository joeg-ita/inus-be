package it.joeg.inus.be.domain.usecases;

import it.joeg.inus.be.dataproviders.controllers.models.RegistrationDTO;
import it.joeg.inus.be.domain.entities.Registration;
import it.joeg.inus.be.domain.exceptions.InvalidDeviceIdException;
import it.joeg.inus.be.domain.exceptions.InvalidInputException;
import it.joeg.inus.be.domain.exceptions.InvalidOtpException;
import it.joeg.inus.be.domain.port.RegistrationService;
import java.util.Optional;
import java.util.UUID;

/**
 * Usecase of generating an OTP
 */
public class RegistrationUsecase {
    
    RegistrationService registrationService;
    
    int defaultLen = 6;

    public RegistrationUsecase(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
    
    public Registration deviceRegistration(RegistrationDTO registrationDTO) throws Exception{
        if(registrationDTO == null){
            throw new InvalidInputException("registration cannot be null");
        }
        
        Optional<Registration> optRegistration;
        
        // 1 find otp on DB and check
        optRegistration = registrationService.findRegistrationByOtp(registrationDTO.getOtp());
        if(!optRegistration.isPresent()){
            throw new InvalidOtpException("Otp NOT PRESENT");
        } 
        
        // 2 verify tha device id is not already present on DB
        Optional<Registration> registrationWithSameDeviceId = registrationService.findDeviceId(registrationDTO.getDeviceId());
        if(registrationWithSameDeviceId.isPresent()){
            throw new InvalidDeviceIdException("Device Id already associated");
        }
        
        // 3 generate unique ID (otional verify not already in DB)
        UUID uuid = UUID.randomUUID();
        Registration registration = optRegistration.get();
        registration.setDeviceId(registrationDTO.getDeviceId());
        registration.setApplicationId(uuid.toString());
        
        // 4 save registration
        registrationService.confirmRegistration(registration);
        
        // 5 return
        return registration;
    }
}
