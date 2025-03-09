//package com.anu3dev.backend.config;
//
//import com.anu3dev.backend.exception.CustomAccessDeniedHandler;
//import com.anu3dev.backend.exception.CustomAuthenticationEntryPoint;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//	@Autowired
//    private JWTFilterConfig jwtFilter;
//	@Autowired
//	private UserDetailsService userDetailsService;
//	@Autowired
//    private CustomAuthenticationEntryPoint authenticationEntryPoint;
//    @Autowired
//    private CustomAccessDeniedHandler accessDeniedHandler;
//
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//		return http
//				.csrf(customizer -> customizer.disable())
//				.exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler)
//                        .authenticationEntryPoint(authenticationEntryPoint))
//				.authorizeHttpRequests(request -> request
//						// "/employee/v1/getEmployee/*"
//						.requestMatchers("/user/v1/register", "/email/v1/contact", "/employee/v1/employees").permitAll()
//						.anyRequest().authenticated())
//				.httpBasic(Customizer.withDefaults())
//				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//	}
//
//	@Bean
//	AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//		System.out.print(userDetailsService);
//        provider.setUserDetailsService(userDetailsService);
//		return provider;
//	}
//
//	@Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//}

package com.anu3dev.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings({ "unused", "deprecation" })
//letting know the spring that, this is configuration file
@Configuration
// instructing spring to don't go with default spring security configuration, just follow as per below
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JWTFilterConfig jwtFilter;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// disabling csrf
		http.csrf(customizer -> customizer.disable());

		// adding so every request should be authenticated
		//http.authorizeHttpRequests(request -> request.anyRequest().authenticated());

		// adding so few request should public and remaining will be authenticated
		http.authorizeHttpRequests(request -> request
				.requestMatchers("/user/v1/register", "/employee/v1/employees", "/email/v1/contact", "/employee/v1/getEmployee/*").permitAll()
				.anyRequest().authenticated());

		// enabling form login for browser request
		//http.formLogin(Customizer.withDefaults());

		// enabling form login for postman request
		http.httpBasic(Customizer.withDefaults());

		// make http stateless means every request will get new session id.
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

		// we can also write above as below
		//return http
		//.csrf(customizer -> customizer.disable())
		//.authorizeHttpRequests(request -> request.anyRequest().authenticated())
		//.httpBasic(Customizer.withDefaults())
		//.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		//.build();
	}

	// this is how we can handle in memory authentication
//    @Bean
//    UserDetailsService userDetailsService() {
//      UserDetails user1 = User
//    		  .withDefaultPasswordEncoder()
//    		  .username("kiran")
//    		  .password("k@123")
//    		  .roles("USER")
//    		  .build();
//
//      UserDetails user2 = User
//    		  .withDefaultPasswordEncoder()
//    		  .username("harsh")
//    		  .password("h@123")
//    		  .roles("ADMIN")
//    		  .build();
//
//      return new InMemoryUserDetailsManager(user1, user2);
//	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
