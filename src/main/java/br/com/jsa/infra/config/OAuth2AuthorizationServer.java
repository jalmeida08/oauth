package br.com.jsa.infra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter {
	
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
 
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
            .tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()")
            .allowFormAuthenticationForClients();
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
       endpoints.authenticationManager(authenticationManager);
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .inMemory()
            .withClient("clientapp").secret(passwordEncoder.encode("123456"))
            .authorizedGrantTypes("password", "authorization_code", "refresh_token")
            .authorities("READ_ONLY_CLIENT")
            .scopes("read_profile_info", "web")
            .resourceIds("oauth2-resource")
            .redirectUris("http://localhost:9000/login")
            .accessTokenValiditySeconds(120000)
            .refreshTokenValiditySeconds(480000);
    }
}