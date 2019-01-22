package com.auth.test.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

   private String clientid = "test";
   private String clientSecret = "test";
   private String path = "/home/brian/Documents/workspace-spring-tool-suite-4-4.1.0.RELEASE/users/" ;
   private String privateKey = "jwt.pem";
   private String publicKey = "jwtPublic.pem";

   private String loadFile(String path) throws IOException {
	   InputStream is = new FileInputStream(path); 
	   BufferedReader buf = new BufferedReader(new InputStreamReader(is)); 
	   String line = buf.readLine(); 
	   StringBuilder sb = new StringBuilder(); 
	   while(line != null){ 
	    sb.append(line).append("\n"); 
	    line = buf.readLine(); 
	   	} 
   		String fileAsString = sb.toString(); 
   		return  fileAsString ;
   }
   
   @Autowired
   @Qualifier("authenticationManagerBean")
   private AuthenticationManager authenticationManager;
   
   @Bean
   public JwtAccessTokenConverter tokenEnhancer() throws IOException {
      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
      // System.out.println(privateKey);
      privateKey = loadFile(path+privateKey);
      publicKey = loadFile(path+publicKey);
      converter.setSigningKey(privateKey);
      converter.setVerifierKey(publicKey);
      return converter;
   }
   @Bean
   public JwtTokenStore tokenStore() throws IOException {
      return new JwtTokenStore(tokenEnhancer());
   }
   @Override
   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
      .accessTokenConverter(tokenEnhancer());
   }
   @Override
   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
   }
   @Override
   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      clients.inMemory().withClient(clientid).secret(clientSecret).scopes("read", "write")
         .authorizedGrantTypes("password");

   }
} 