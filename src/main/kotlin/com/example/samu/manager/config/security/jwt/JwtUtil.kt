package com.example.samu.manager.config.security.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.stereotype.Component
import java.util.Date
import java.util.concurrent.TimeUnit

@Component
class JwtUtil {

    // Clave secreta para firmar el token. ¡No expongas esto en el código fuente!
    private val secretKey = "miClaveSecretaSuperSegura"


    // Tiempo de expiración del token (en este caso, 1 día)
    private val expirationTime = TimeUnit.DAYS.toMillis(1)

    /**
     * Método para crear un token JWT con el nombre de usuario como "subject".
     */
    fun create(username: String): String {
        val algorithm = Algorithm.HMAC256(secretKey) // Algoritmo de firma
        return JWT.create()
            .withSubject(username) // Usuario asociado al token
            .withIssuedAt(Date()) // Fecha de emisión
            .withExpiresAt(Date(System.currentTimeMillis() + expirationTime)) // Fecha de expiración
            .sign(algorithm) // Firma del token
    }


    fun validate(jwt: String): Boolean {
        try {
            JWT.require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(jwt)
            return true;
        } catch (exception: JWTVerificationException) {
            return false;
        }
    }

    fun getUsername(jwt: String): String {
        return  JWT.require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(jwt)
                .subject
    }
}
