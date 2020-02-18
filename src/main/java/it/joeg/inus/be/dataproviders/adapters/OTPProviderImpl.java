package it.joeg.inus.be.dataproviders.adapters;

import java.util.Random;
import it.joeg.inus.be.domain.port.OTPProvider;

/**
 * Concrete implementation of OTP Generator
 */
public class OTPProviderImpl implements OTPProvider {

    final String NUMBERS = "0123456789";

    @Override
    public String generate(int len) {

        Random random = new Random();

        char[] otp = new char[len];

        for (int i = 0; i < len; i++) {
            otp[i] = NUMBERS.charAt(random.nextInt(NUMBERS.length()));
        }

        return String.copyValueOf(otp);
    }

    @Override
    public String generate(int len, long seed) {
        Random random = new Random(seed);

        char[] otp = new char[len];

        for (int i = 0; i < len; i++) {
            otp[i] = NUMBERS.charAt(random.nextInt(NUMBERS.length()));
        }

        return String.copyValueOf(otp);
    }

}
