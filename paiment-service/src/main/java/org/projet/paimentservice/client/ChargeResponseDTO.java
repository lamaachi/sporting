// ChargeResponseDTO.java
package org.projet.paimentservice.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChargeResponseDTO {
    private String id;
    private String status;
    private String description;
    private Long amount;
}
