package com.example.adapter.config;

import com.example.adapter.security.JwtAuthenticationEntryPoint;
import com.example.adapter.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


// @EnableMethodSecurity: Cette annotation permet la sécurité basée sur les annotations dans les méthodes.
@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {


    // Gestionnaire d'authentification pour la configuration
    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    // Filtre d'authentification basé sur JWT pour la configuration
    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    // Bean pour encoder les mots de passe
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuration de la sécurité HTTP

        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> {
                    // Configurer les autorisations d'accès aux différentes URL
                    // Remarque : les lignes suivantes sont en commentaire et ne sont pas actives
//                    authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
//                    authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");
//                    authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();

                    //authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    //authorize.anyRequest().authenticated();
                    authorize.requestMatchers("/api/auth/**").permitAll();
                }).httpBasic(Customizer.withDefaults());

        // Gestion des exceptions lors de l'authentification
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint));
        // Ajout du filtre d'authentification JWT avant le filtre d'authentification par nom d'utilisateur/mot de passe
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Bean pour récupérer le gestionnaire d'authentification de la configuration
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}