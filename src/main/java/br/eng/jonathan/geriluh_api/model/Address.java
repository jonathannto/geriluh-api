package br.eng.jonathan.geriluh_api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
@Data
@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Embeddable
public class Address {

    private String streetAddress;

    private Long addressNumber;

    private String city;

    private String state;

    private String zipCode;
}
