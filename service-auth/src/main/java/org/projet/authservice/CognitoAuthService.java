package org.projet.authservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.AuthenticationException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CognitoAuthService {

    @Value("${cognito.endpoint}")
    private String cognitoEndpoint;

    @Value("${cognito.clientId}")
    private String clientId;

    @Value("${cognito.clientSecret}")
    private String clientSecret;

    public String authenticate(String username, String password) throws AuthenticationException {
        RestTemplate restTemplate = createCustomRestTemplate();

        // Définir les en-têtes pour la requête
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/x-amz-json-1.1"));
        headers.add("X-Amz-Target", "AWSCognitoIdentityProviderService.InitiateAuth");

        // Préparer le corps de la requête
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("AuthFlow", "USER_PASSWORD_AUTH");
        requestBody.put("ClientId", clientId);

        // Calculer le SECRET_HASH
        String secretHash = calculateSecretHash(username);

        // Ajouter les paramètres d'authentification
        Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", username);
        authParams.put("PASSWORD", password);
        authParams.put("SECRET_HASH", secretHash); // Ajouter le SECRET_HASH
        requestBody.put("AuthParameters", authParams);

        // Créer l'objet HttpEntity avec les en-têtes et le corps
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            // Envoyer la requête POST à Cognito
            ResponseEntity<Map> response = restTemplate.exchange(
                    cognitoEndpoint, HttpMethod.POST, entity, Map.class
            );

            // Vérifier la réponse et extraire le jeton
            if (response.getBody() != null && response.getBody().containsKey("AuthenticationResult")) {
                Map<String, Object> authResult = (Map<String, Object>) response.getBody().get("AuthenticationResult");
                return (String) authResult.get("IdToken");
            }
            throw new AuthenticationException("Invalid username or password");
        } catch (Exception e) {
            throw new AuthenticationException("Failed to authenticate: " + e.getMessage());
        }
    }

    // Méthode pour calculer le SECRET_HASH
    private String calculateSecretHash(String username) {
        try {
            String message = username + clientId;
            SecretKeySpec signingKey = new SecretKeySpec(clientSecret.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating SECRET_HASH: " + e.getMessage(), e);
        }
    }

    // Méthode pour créer un RestTemplate personnalisé
    private RestTemplate createCustomRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // Ajouter un convertisseur JSON pour le type MIME spécifique
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(List.of(MediaType.valueOf("application/x-amz-json-1.1")));

        restTemplate.getMessageConverters().add(converter);
        return restTemplate;
    }
}
