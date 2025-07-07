package com.enlinka.peso_wise.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.security.crypto.encrypt.TextEncryptor
import org.springframework.security.crypto.keygen.KeyGenerators

@Configuration
class EncryptionConfig {

    @Value("\${app.encryption.secret}")
    private lateinit var encryptionSecret: String

    @Bean
    fun textEncryptor(): TextEncryptor {
        return Encryptors.text(encryptionSecret, KeyGenerators.string().generateKey())
    }

//    @Bean
//    fun queryableTextEncryptor(): TextEncryptor {
//        return Encryptors.queryableText(encryptionSecret, KeyGenerators.string().generateKey())
//    }
}
