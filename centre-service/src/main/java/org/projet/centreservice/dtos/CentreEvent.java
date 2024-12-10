package org.projet.centreservice.dtos;


import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CentreEvent implements Serializable {
    private String eventType;
    private Object data;

}
