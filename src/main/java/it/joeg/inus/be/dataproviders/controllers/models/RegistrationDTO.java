package it.joeg.inus.be.dataproviders.controllers.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationDTO {

    private String deviceId;
    private String otp;
    private String applicationId;
}
