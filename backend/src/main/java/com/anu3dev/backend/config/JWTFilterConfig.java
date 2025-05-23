//package com.anu3dev.backend.config;
//
//import com.anu3dev.backend.exception.ResourceUnAuthorizedException;
//import com.anu3dev.backend.service.JWTService;
//import com.anu3dev.backend.service.MyUserDetailsService;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.UnsupportedJwtException;
//import io.jsonwebtoken.security.SignatureException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//@Component
//public class JWTFilterConfig extends OncePerRequestFilter {
//
//    @Autowired
//    private JWTService jwtService;
//
//    @Autowired
//    ApplicationContext context;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//    	try {
//	    	//  Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraWxsIiwiaWF0IjoxNzIzMTgzNzExLCJleHAiOjE3MjMxODM4MTl9.5nf7dRzKRiuGurN2B9dHh_M5xiu73ZzWPr6rbhOTTHs
//	        String authHeader = request.getHeader("Authorization");
//	        String token = null;
//	        String username = null;
//
//	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//	            token = authHeader.substring(7);
//	            username = jwtService.extractUserName(token);
//	        }
//
//	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//				System.out.println(username);
//	            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
//	            if (jwtService.validateToken(token, userDetails)) {
//	                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//	                authToken.setDetails(new WebAuthenticationDetailsSource()
//	                        .buildDetails(request));
//	                SecurityContextHolder.getContext().setAuthentication(authToken);
//	            }
//	        }
//	        filterChain.doFilter(request, response);
//
//    	} catch (ExpiredJwtException e) {
//            new ResourceUnAuthorizedException("JWT token has expired");
//        } catch (UnsupportedJwtException e) {
//            new ResourceUnAuthorizedException("Unsupported JWT token");
//        } catch (MalformedJwtException e) {
//            new ResourceUnAuthorizedException("Invalid JWT token");
//        } catch (SignatureException e) {
//            new ResourceUnAuthorizedException("Invalid JWT signature");
//        } catch (IllegalArgumentException e) {
//            new ResourceUnAuthorizedException("JWT claims are invalid");
//        }
//    }
//}

package com.anu3dev.backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.anu3dev.backend.service.JWTService;
import com.anu3dev.backend.service.MyUserDetailsService;

import java.io.IOException;
@Component
public class JWTFilterConfig extends OncePerRequestFilter {

	@Autowired
	private JWTService jwtService;

	@Autowired
	ApplicationContext context;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		//  Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraWxsIiwiaWF0IjoxNzIzMTgzNzExLCJleHAiOjE3MjMxODM4MTl9.5nf7dRzKRiuGurN2B9dHh_M5xiu73ZzWPr6rbhOTTHs
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username = jwtService.extractUserName(token);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			try {
				UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
				if (jwtService.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource()
							.buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			} catch (UsernameNotFoundException exception) {
				throw new UsernameNotFoundException("hello");
			}
		}

		filterChain.doFilter(request, response);
	}
}