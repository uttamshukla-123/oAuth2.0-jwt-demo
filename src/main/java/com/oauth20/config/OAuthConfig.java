package com.oauth20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuthConfig extends AuthorizationServerConfigurerAdapter {

	private String clientId = "uttam";
	private String clientSecret = "uttamshukla";
	private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n"
			+ "MIIEowIBAAKCAQEAqrDNz4aVH2iKAFLEf76diKWrR0H6JQ4CkPm+/4cD4GQN3wgR\n"
			+ "hGrP83Qrwnf7zuljdM66WArT0jWNpC9erO6N2EhC/P29D68OVqHIPSUape/BbMkX\n"
			+ "xCKaGap7LPAzLxDrboqK3lDw02To6k9UNh7t8HP7XT3Cy/RzgTjUmRxeFoJ4wc/B\n"
			+ "JKzO0QpljDZr6xlE0eQYPqo80CvniEdx1r65wCvE5HTRirWCnL8R3gg23Auckw/E\n"
			+ "MCfn7FQrX707twb/Lzmtqv856cirfVpxS7pOMQkQsCQjUvH6UDsbCNkB8skCQE83\n"
			+ "d6DzmiYGbwUuNCPJ5f7boAm4hq5eNN71R4m77wIDAQABAoIBAEkBAaWmzBT44z0s\n"
			+ "KTrxzfLp2I3LnC9JBLP4/UXQ+0l3wa8IhmV6QaLApw6vvpVKoO0Olgid6ObZXNFC\n"
			+ "KV4RUb2VeoE2DSyuFJXc8AWm3vTlzHa4s1+B5SLhFXms6ldRB0DqJGbde4MOf8fn\n"
			+ "Uf8rz1yuXm4Mwkn1LYmxQsyRo3RAeogpO9MHUmYwuqxo77oEe1+fCBAzrbzWAFpM\n"
			+ "RPIV4PV4IbeHb8hALtRrRo+W2V+EGaOFqp3d7Y9wCKDDsny5LRedvNSc6ZxpBip1\n"
			+ "+RKbtCU77kS6wtmdsTqtowW/7WKhAKTSnMv7r6oBlfLeQec5FyTmBEYr3mp2C6+U\n"
			+ "eI0Uw9ECgYEA1UuGNsrg5V+mhsLXkSFmJzWbpjTBxU0lMC+DNLJzqngjEjgP7rDN\n"
			+ "MuewGHhxpzkZ9oUn2j9ER5eeHePT8ghEDZ0L5t/hzrJaY0MOjoxGiudKK2TkmmkC\n"
			+ "fw69cZqg60oxwBaLUDgqv2bS/sqcMgOxMx4HKO8IQs0oQ4WfiquunD0CgYEAzN2V\n"
			+ "nnpIEnRy0oUFrZa3iqiN3BSmot72TWZ/PHsfd5ZGs17hW6E6Wl6DNgOmhFOQaEzI\n"
			+ "yXx+CQUcamDbLMgi4Q0aNZxkeNlovgM0WE2u/jlu7lkQ9cIg65IqHX/M+9a6Q1Vy\n"
			+ "RoAZ6IbTYu6o+43/aufsN1akTBMrfAvpEjkY35sCgYAKjs5579jqAa4QhZx+BpUz\n"
			+ "46dstrfQuFza4b8w4JRxH7k8saG4QvAlUOqai3u0t1LdrQGn//G1SX/+IsTe+iHE\n"
			+ "Or3YE8Ttok5vE2apD7B+N6yoMTpPmlsRRv2BnhHRE78arwL+WYlkKAbRrd3FbAFd\n"
			+ "cmMQV6SlCdqwXPDkwFLIUQKBgFDvHWPdhUmdLZuTp9mbWGl0C48trWNc2EtdudiH\n"
			+ "/7d76/SDAF71xBrdidK2AHv4k21RIGXRQ+2GUmKIXy5C5tyoE3mojlzb7zBaDbzw\n"
			+ "+7g9smCBWVzt05mdRfwfeDjp7O4a/KlzhEjDsxE7nhC7WXO/qid2mDPhwuUfw3VK\n"
			+ "j1QPAoGBALyjelrLAPx/RjCs9PkzrKOb1uJBmY2wyo4bNA+BTxJ0PVkoDlTyR64s\n"
			+ "uQBDW2oVk45yfwWT9H28K2afDvth1tN6QTjXW1qzaRQPdYjkacj6mY1Hk1IA0dGo\n"
			+ "ArR5qrU/eiZtpq07vyIhv7P0+MA9Nm2sqCCRve+7HQCVyhq+ZbPh\n" + "-----END RSA PRIVATE KEY-----\n" + "";
	private String publicKey = "-----BEGIN PUBLIC KEY-----\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqrDNz4aVH2iKAFLEf76d\n"
			+ "iKWrR0H6JQ4CkPm+/4cD4GQN3wgRhGrP83Qrwnf7zuljdM66WArT0jWNpC9erO6N\n"
			+ "2EhC/P29D68OVqHIPSUape/BbMkXxCKaGap7LPAzLxDrboqK3lDw02To6k9UNh7t\n"
			+ "8HP7XT3Cy/RzgTjUmRxeFoJ4wc/BJKzO0QpljDZr6xlE0eQYPqo80CvniEdx1r65\n"
			+ "wCvE5HTRirWCnL8R3gg23Auckw/EMCfn7FQrX707twb/Lzmtqv856cirfVpxS7pO\n"
			+ "MQkQsCQjUvH6UDsbCNkB8skCQE83d6DzmiYGbwUuNCPJ5f7boAm4hq5eNN71R4m7\n" + "7wIDAQAB\n"
			+ "-----END PUBLIC KEY-----\n" + "";

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@SuppressWarnings("deprecation")
	@Bean
	public JwtAccessTokenConverter tokenEnhancer() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(privateKey);
		converter.setVerifierKey(publicKey);
		return converter;
	}

	@SuppressWarnings("deprecation")
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(tokenEnhancer());
	}

	@SuppressWarnings("deprecation")
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(tokenEnhancer());
	}

	@Override
	public void configure(@SuppressWarnings("deprecation") AuthorizationServerSecurityConfigurer security)
			throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
				.authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
				.refreshTokenValiditySeconds(20000);

	}

}