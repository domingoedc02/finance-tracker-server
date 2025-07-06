package com.enlinka.peso_wise.security

import com.enlinka.peso_wise.config.JwtConfig
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtTokenProvider(
    private val jwtConfig: JwtConfig
) {
    
    private val logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)
    
    private val key: Key by lazy {
        Keys.hmacShaKeyFor(jwtConfig.secret.toByteArray())
    }
    
    fun generateToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserDetails
        val expiryDate = Date(System.currentTimeMillis() + jwtConfig.expiration * 1000)
        
        return Jwts.builder()
            .setSubject(userPrincipal.username)
            .setIssuedAt(Date())
            .setExpiration(expiryDate)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }
    
    fun generateTokenFromUsername(username: String): String {
        val expiryDate = Date(System.currentTimeMillis() + jwtConfig.expiration * 1000)
        
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(expiryDate)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }
    
    fun getUsernameFromToken(token: String): String {
        val claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
        
        return claims.subject
    }
    
    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
            return true
        } catch (ex: SecurityException) {
            logger.error("Invalid JWT signature: ${ex.message}")
        } catch (ex: MalformedJwtException) {
            logger.error("Invalid JWT token: ${ex.message}")
        } catch (ex: ExpiredJwtException) {
            logger.error("Expired JWT token: ${ex.message}")
        } catch (ex: UnsupportedJwtException) {
            logger.error("Unsupported JWT token: ${ex.message}")
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT claims string is empty: ${ex.message}")
        }
        return false
    }
    
    fun getExpirationDateFromToken(token: String): Date {
        val claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
        
        return claims.expiration
    }
}
