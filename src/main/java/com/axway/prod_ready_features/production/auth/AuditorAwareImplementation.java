package com.axway.prod_ready_features.production.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImplementation implements AuditorAware<String> {

    // get Security context
    // get authentication
    // get the principle
    // get the username
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("SARFARAZ");
    }
}
