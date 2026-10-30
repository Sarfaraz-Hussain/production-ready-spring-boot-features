package com.axway.prod_ready_features.production.config;

import com.axway.prod_ready_features.production.auth.AuditorAwareImplementation;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareImpl")
public class AppConfig {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public AuditorAware<String> getAuditorAwareImpl(){
        return new AuditorAwareImplementation();
    }
}
