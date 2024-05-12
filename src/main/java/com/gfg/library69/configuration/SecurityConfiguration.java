package com.gfg.library69.configuration;

import io.netty.util.internal.NoOpTypeParameterMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(authorize ->
                authorize
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/greet/**").hasAnyAuthority("USER")
                        .requestMatchers("/signup").permitAll()
                        .anyRequest().authenticated()
            ).httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    /*
     secure our apis
     1.onbord the user with credentials ->user object ->user detail
     2.accept the usernmme and password  ->Authentication provider -> authentication class
     3.Fetch the user from the db ->UserdetailsserviceImplementation
     4.Compare the password hash of the user -> Authntication provider -> PasswordEncoder
     5.Check if the user has authority over the api  -> SecurityFilterChain
     6.let the user use the api -> AuthenticationProvider

     */
}
