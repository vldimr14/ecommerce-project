package org.ecommercebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("org.ecommercebackend.repositories")
public class MainConfig {
}
