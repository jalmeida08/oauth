package br.com.jsa.infra.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    	var configuration = new org.springframework.web.cors.CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("GET","POST", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
   }
//	@Override @Bean
//	protected UserDetailsService userDetailsService() {
//		return super.userDetailsService();
//	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    		.cors().and()
    		.csrf().disable()
        	.antMatcher("/**")
	        	.authorizeRequests()
	        	.antMatchers("/oauth/authorize**", "/login**", "/error**")
	        	.permitAll()
        	.and()
            	.authorizeRequests()
            	.anyRequest().authenticated();
//        	.and()
//        		.formLogin().permitAll();
    }

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}