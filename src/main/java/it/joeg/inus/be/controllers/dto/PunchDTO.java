package it.joeg.inus.be.controllers.dto;

import lombok.Data;

/**
 *
 */
@Data
public class PunchDTO {

    String code;
    String cause;
    String type;
    String timestamp;
    String coordinates;
    String appId;
    
}
