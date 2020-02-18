package it.joeg.inus.be.domain.usecases;

import it.joeg.inus.be.domain.entities.Registration;
import it.joeg.inus.be.domain.exceptions.InvalidInputException;
import it.joeg.inus.be.domain.port.OTPProvider;
import it.joeg.inus.be.domain.port.RegistrationService;

/**
 * Usecase of generating an OTP
 */
public class OTPUsecase {
    
    OTPProvider oTPService;
    RegistrationService registrationService;
    
    int defaultLen = 6;

    public OTPUsecase(OTPProvider oTPService, RegistrationService registrationService) {
        this.oTPService = oTPService;
        this.registrationService = registrationService;
    }
    
    public String generateOTP(){
        return oTPService.generate(defaultLen);
    }
    
    public Registration generateRegistrationOTP(Registration registration){
        if(registration == null){
            throw new InvalidInputException("Il valore registration non può essere null");
        }
        // 1 generate otp
        String otp = oTPService.generate(defaultLen);
        // TODO
        // 2 save otp
        registration.setOtp(otp);
        registrationService.savePreRegistration(registration);
        // 3 return otp
        return registration;
    }
}
