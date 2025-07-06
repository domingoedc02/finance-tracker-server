package com.enlinka.peso_wise.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "app.jwt")
data class JwtConfig(
    var secret: String = "",
    var expiration: Long = 15552000 // 6 months in seconds
)
