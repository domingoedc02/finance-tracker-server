package com.enlinka.peso_wise.config

import com.enlinka.peso_wise.security.JwtAuthenticationFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {

    @Value("\${app.cors.allowed-origins}")
    private lateinit var allowedOrigins: List<String>

    @Value("\${app.cors.allowed-methods}")
    private lateinit var allowedMethods: List<String>

    @Value("\${app.cors.allowed-headers}")
    private lateinit var allowedHeaders: String

    @Value("\${app.cors.allow-credentials}")
    private var allowCredentials: Boolean = true

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
        return authConfig.authenticationManager
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            allowedOriginPatterns = allowedOrigins
            allowedMethods = this@SecurityConfig.allowedMethods
            allowedHeaders = listOf("*")
            allowCredentials = this@SecurityConfig.allowCredentials
            maxAge = 3600L
        }

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors { it.configurationSource(corsConfigurationSource()) }
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { authz ->
                authz
                    // Public authentication endpoints
                    .requestMatchers("/api/v1/auth/login").permitAll()
                    .requestMatchers("/api/v1/auth/register").permitAll()
                    .requestMatchers("/api/v1/auth/verification").permitAll()
                    
                    // Health check and actuator endpoints
                    .requestMatchers("/actuator/health").permitAll()
                    
                    // H2 Console (development only)
                    .requestMatchers("/h2-console/**").permitAll()
                    
                    // All other API endpoints require authentication
                    .requestMatchers("/api/v1/**").authenticated()
                    
                    // Deny all other requests
                    .anyRequest().denyAll()
            }
            .headers { headers ->
                headers.frameOptions { it.sameOrigin() } // For H2 console
            }

        // Add JWT authentication filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        // This will be implemented later with proper user details loading
        return UserDetailsService { username ->
            throw RuntimeException("UserDetailsService implementation needed")
        }
    }
}
