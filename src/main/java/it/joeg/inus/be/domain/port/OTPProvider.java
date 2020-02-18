package it.joeg.inus.be.domain.port;

/**
 * OTP Generator 
 */
public interface OTPProvider {
    
    public String generate(int len);
    
    public String generate(int len, long seed);
}
