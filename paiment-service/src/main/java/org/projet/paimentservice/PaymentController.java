package org.projet.paimentservice;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    // Clé secrète Stripe
    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @PostMapping("/addCard")
    public ResponseEntity<?> addCard(@RequestBody Map<String, String> payload) {
        // Vérifiez ce qui est reçu
        System.out.println("Payload received: " + payload);

        String paymentMethodId = payload.get("paymentMethodId");

        if (paymentMethodId == null || paymentMethodId.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "PaymentMethod ID is required."));
        }

        try {
            // Traitez Stripe
            Stripe.apiKey = stripeSecretKey;

            // Récupérer la méthode de paiement
            PaymentMethod paymentMethod = PaymentMethod.retrieve(paymentMethodId);

            // Créer le PaymentIntent
            Map<String, Object> params = new HashMap<>();
            params.put("amount", 3000); // Montant en centimes
            params.put("currency", "eur");
            params.put("payment_method", paymentMethodId);
            params.put("confirmation_method", "manual");
            params.put("confirm", true);

            PaymentIntent paymentIntent = PaymentIntent.create(params);

            // Retourner le clientSecret et paymentIntentId
            return ResponseEntity.ok(Map.of(
                    "message", "Payment method attached successfully.",
                    "clientSecret", paymentIntent.getClientSecret(),
                    "paymentIntentId", paymentIntent.getId()  // Ajout de l'ID du PaymentIntent
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error processing payment", "message", e.getMessage()));
        }
    }

    @PostMapping("/confirmReservation")
    public ResponseEntity<?> confirmReservation(@RequestBody Map<String, String> payload) {
        String paymentIntentId = payload.get("paymentIntentId");  // Utilisez paymentIntentI

        // Vérification des paramètres nécessaires
        if (paymentIntentId == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing paymentIntentId."));
        }

        try {
            // Utilisez Stripe pour récupérer le PaymentIntent à partir du paymentIntentId
            Stripe.apiKey = stripeSecretKey;

            // Récupération du PaymentIntent avec son ID
            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

            // Vérifier si le paiement a réussi
            if ("succeeded".equals(paymentIntent.getStatus())) {
                return ResponseEntity.ok(Map.of("message", "Reservation confirmed and paid successfully."));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Payment not completed or still pending."));
            }
        } catch (Exception e) {
            // Si une erreur survient, renvoyer une réponse avec un message d'erreur détaillé
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error confirming reservation", "message", e.getMessage()));
        }
    }
}

