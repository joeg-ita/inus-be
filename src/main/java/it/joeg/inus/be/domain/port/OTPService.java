package it.joeg.inus.be.domain.port;

/**
 * OTP Generator 
 */
public interface OTPService {
    
    public String generate(int len);
    
    public String generate(int len, long seed);
}
