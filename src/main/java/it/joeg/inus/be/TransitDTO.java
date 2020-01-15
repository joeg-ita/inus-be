package it.joeg.inus.be;

import lombok.Data;

/**
 *
 */
@Data
public class TransitDTO {

    String code;
    String cause;
    String type;
    String timestamp;
    String coordinates;
    String appId;
    
}
