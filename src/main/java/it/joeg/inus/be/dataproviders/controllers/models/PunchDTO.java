package it.joeg.inus.be.dataproviders.controllers.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PunchDTO {

    String code;
    String cause;
    String type;
    String timestamp;
    String coordinates;
    String appId;
    
}
