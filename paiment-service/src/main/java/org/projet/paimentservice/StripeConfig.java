package org.projet.paimentservice;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class StripeConfig {
    @PostConstruct
    public void init() {
        Stripe.apiKey = "sk_test_51QL5EyFY8ODNUY9tpuuvjxJYte5tddYxN9r2yswSjEjNkGczGI35rvo5wDJiG1cYgaFBlrm0aosdFtsUWvfiqRGI006fdZEIOq"; // Remplacez par votre clé Stripe secrète
    }


}