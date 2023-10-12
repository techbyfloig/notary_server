package com.techbyfloig.cubannotary.notary.conf

import com.techbyfloig.cubannotary.notary.env.Environment
import com.techbyfloig.cubannotary.notary.repo.UpsOAuthDb
import com.techbyfloig.cubannotary.notary.repo.UpsOauthNetwork
import com.techbyfloig.cubannotary.notary.repo.UpsOauthRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.client.RestTemplate


@Configuration
@EnableWebSecurity
class AppConfig {

    private val authEntryPoint: JwtAuthEntryPoint


    @Autowired
    constructor(authEntryPoint: JwtAuthEntryPoint) {
        this.authEntryPoint = authEntryPoint
    }


    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http.csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(authEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/auth/**").permitAll()
            .antMatchers("/api/pdfs/**").permitAll()
            .antMatchers("/api/ups/rate").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic()
        http.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    fun authenticationManager(authenticationConf: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConf.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun jwtAuthFilter(): JwtAuthFilter {
        return JwtAuthFilter()
    }

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    @Bean
    fun upsOauthNetwork(restTemplate: RestTemplate): UpsOauthNetwork {
        return UpsOauthNetwork(restTemplate)
    }

}