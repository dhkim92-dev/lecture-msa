package kr.dohoon_kim.msa.auth_service.configs

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.filter.CorsFilter

@Configuration
class SecurityConfiguration {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @ConditionalOnProperty(name = ["spring.h2.console.enabled"],havingValue = "true")
    fun ignoringWebSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer {
            it.ignoring().requestMatchers(PathRequest.toH2Console())
        }
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.sessionManagement { sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .cors{cors -> cors.disable()}
            .csrf{csrf -> csrf.disable()}
            .formLogin{formLogin -> formLogin.disable()}
            .httpBasic{httpBasic -> httpBasic.disable()}
            .logout{logout -> logout.disable()}
            .authorizeHttpRequests { authorizeHttpRequests -> authorizeHttpRequests.anyRequest().permitAll() }
        return http.build()
    }
}