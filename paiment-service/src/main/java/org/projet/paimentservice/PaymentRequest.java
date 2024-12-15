// PaymentRequest.java
package org.projet.paimentservice;

import lombok.Data;

@Data
public class PaymentRequest {
    private String source;
    private long amount; // amount in cents
    private String currency;
    private String description;
}
