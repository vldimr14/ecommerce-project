package org.ecommercebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"org.ecommercebackend.repositories", "org.ecommercebackend.security"})
public class MainConfig {
}
