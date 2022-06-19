package br.com.coelho.cittabronca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long id;
    private String postalCode;
    private String street;
    private String city;
    private String countryCode;
    private String country;
    private String text;
    private String state;
    private String stateCode;
    private String neighborhood;
    private Double latitude;
    private Double longitude;
}
