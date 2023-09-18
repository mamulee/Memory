package com.baby.memory.common.config

import com.baby.memory.common.authority.JwtAuthenticationFilter
import com.baby.memory.common.authority.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .httpBasic{ it.disable() }
            .csrf{ it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) } // JWT를 사용하기 때문에 Session 사용 X
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/members/new",
                    "/members/sign-in"
                ).anonymous() // 해당 URL 요청은 인증하지 않은 사용자
                    .requestMatchers("/members/**", "/memories/**", "/saved-memories/**").hasRole("MEMBER")
                    .anyRequest().permitAll() // 그 외의 요청은 아무 권한없이 모두가 접근 가능
            }
            .addFilterBefore(
                JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder =
        PasswordEncoderFactories.createDelegatingPasswordEncoder()
}