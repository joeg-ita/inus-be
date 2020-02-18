package it.joeg.inus.be.configuration;

import it.joeg.inus.be.dataproviders.adapters.CausesServiceImpl;
import it.joeg.inus.be.dataproviders.adapters.OTPProviderImpl;
import it.joeg.inus.be.dataproviders.adapters.PunchServiceImpl;
import it.joeg.inus.be.dataproviders.adapters.RegistrationServiceImpl;
import it.joeg.inus.be.dataproviders.adapters.mocks.CausesServiceImplMock;
import it.joeg.inus.be.dataproviders.adapters.mocks.PunchServiceImplMock;
import it.joeg.inus.be.dataproviders.adapters.mocks.RegistrationServiceImplMock;
import it.joeg.inus.be.domain.port.CausesService;
import it.joeg.inus.be.domain.usecases.OTPUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import it.joeg.inus.be.domain.port.OTPProvider;
import it.joeg.inus.be.domain.port.PunchService;
import it.joeg.inus.be.domain.port.RegistrationService;
import it.joeg.inus.be.domain.usecases.RegistrationUsecase;
import it.joeg.inus.be.domain.usecases.RetrieveCausesUsecase;
import it.joeg.inus.be.domain.usecases.RetrievePunchesUsecase;
import org.springframework.beans.factory.annotation.Value;

/**
 * Configuration DI
 */
@Configuration
public class BeansConfiguration {

    @Value("${app.mock}")
    private Boolean useMock;

    @Bean
    public OTPProvider otpProvider() {
        return new OTPProviderImpl();
    }

    @Bean
    public RegistrationService registrationService() {
        if (useMock == true) {
            return new RegistrationServiceImplMock();
        } else {
            return new RegistrationServiceImpl();
        }
    }

    @Bean
    public PunchService punchService() {
        if (useMock == true) {
            return new PunchServiceImplMock();
        } else {
            return new PunchServiceImpl();
        }
    }
    
    @Bean
    public CausesService causesService() {
        if (useMock == true) {
            return new CausesServiceImplMock();
        } else {
            return new CausesServiceImpl();
        }
    }

    @Bean
    public OTPUsecase otpUsecase() {
        return new OTPUsecase(otpProvider(), registrationService());
    }

    @Bean
    public RegistrationUsecase registrationUsecase() {
        return new RegistrationUsecase(registrationService());
    }

    @Bean
    public RetrievePunchesUsecase punchesUsecase() {
        return new RetrievePunchesUsecase(punchService());
    }
    
    @Bean
    public RetrieveCausesUsecase causesUsecase(){
        return new RetrieveCausesUsecase(causesService());
    }
}
