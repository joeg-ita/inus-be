package it.joeg.inus.be.dataproviders.controllers.actuator;

import it.joeg.inus.be.domain.port.PunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * Custom health indicator
 */
@Component
public class InusHealthIndicator extends AbstractHealthIndicator {

    @Autowired
    private PunchService punchService;

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        long fiveMinutesCount = punchService.retrievePunches(5);
        long thirtyMinutesCount = punchService.retrievePunches(30);
        long sixtyMinutesCount = punchService.retrievePunches(60);
        builder.up()
                .status(Status.UP)
                .withDetail("Punches in last 5 minutes", fiveMinutesCount)
                .withDetail("Punches in last 30 minutes", thirtyMinutesCount)
                .withDetail("Punches in last 60 minutes", sixtyMinutesCount);
    }

}
