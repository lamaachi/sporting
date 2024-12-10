package org.projet.paimentservice.client;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    public StripeService(@Value("${stripe.api.key}") String apiKey) {
        Stripe.apiKey = apiKey;
    }

    public String attachPaymentMethodToCustomer(String paymentMethodId, String customerId) throws StripeException {
        if (customerId == null || customerId.isEmpty()) {
            // Create a new customer if customerId is not provided
            Customer customer = Customer.create(new HashMap<>());
            customerId = customer.getId();
        }

        // Retrieve the payment method and attach it to the customer
        PaymentMethod paymentMethod = PaymentMethod.retrieve(paymentMethodId);
        Map<String, Object> params = new HashMap<>();
        params.put("customer", customerId);
        paymentMethod.attach(params);

        return customerId;
    }

    public Charge makePaymentUsingCard(String paymentMethodId, String customerId, long amount) throws StripeException {
        // Create a PaymentIntent to charge the customer
        Map<String, Object> paymentParams = new HashMap<>();
        paymentParams.put("amount", amount);
        paymentParams.put("currency", "usd");
        paymentParams.put("customer", customerId);
        paymentParams.put("payment_method", paymentMethodId);
        paymentParams.put("off_session", true);
        paymentParams.put("confirm", true);

        return Charge.create(paymentParams);
    }
}
