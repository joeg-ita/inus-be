package it.joeg.inus.be.dataproviders;

import it.joeg.inus.be.domain.port.OTPService;
import java.util.Random;

/**
 * Concrete implementation of OTP Generator
 */
public class OTPServiceImpl implements OTPService {

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
