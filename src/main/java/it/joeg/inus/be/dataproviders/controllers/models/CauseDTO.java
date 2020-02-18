package it.joeg.inus.be.dataproviders.controllers.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.joeg.inus.be.domain.entities.CausesType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 */
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CauseDTO {

    private String code;
    private String title;
    private String shortDescription;
    private String description;
    private String regulation;
    private CausesType type;

    public CauseDTO() {
    } 

}
