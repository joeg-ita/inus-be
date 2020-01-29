package it.joeg.inus.be.domain.usecases;

import it.joeg.inus.be.domain.port.OTPService;

/**
 * Usecase of generating an OTP
 */
public class OTPUsecase {
    
    OTPService oTPService;
    
    int defaultLen = 6;

    public OTPUsecase(OTPService oTPService) {
        this.oTPService = oTPService;
    }
    
    public String generateOTP(){
        // 1 generate otp
        String otp = oTPService.generate(defaultLen);
        // TODO
        // 2 save otp
        
        // 3 return otp
        return otp;
    }
}
