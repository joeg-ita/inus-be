package it.joeg.inus.be.configuration;

import it.joeg.inus.be.dataproviders.OTPServiceImpl;
import it.joeg.inus.be.domain.port.OTPService;
import it.joeg.inus.be.domain.usecases.OTPUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration DI
 */
@Configuration
public class BeansConfiguration {
    
    
    @Bean
    public OTPService otpService() {
        return new OTPServiceImpl();
    }
    
    @Bean
    public OTPUsecase otpUsecase() {
        return new OTPUsecase(otpService());
    }
    
}
