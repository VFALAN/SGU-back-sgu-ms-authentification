package com.msc.ms.authentification.filters;

import com.msc.ms.authentification.crypto.CryptoService;
import com.msc.ms.authentification.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    @Value("${msc.security.header}")
    private String securityHeader;
    @Value("#{'${msc.security.allowed.consumers}'.split(',')}")
    private String[] consumers;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final CryptoService cryptoService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final var token = this.getTokenFromRequest(request);
        final var securityHeader = request.getHeader(this.securityHeader);
        if (token == null && securityHeader == null) {
            filterChain.doFilter(request, response);
            return;
        } else if (token != null) {
            final var userName = jwtService.getUsernameFromToken(token);
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDatail = userDetailsService.loadUserByUsername(userName);
                if (!jwtService.isTokenExpired(token)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDatail, null, userDatail.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } else if (securityHeader != null) {
            log.info("security header getter {}", securityHeader);
            final String microservice;
            try {
                microservice = cryptoService.decrypt(securityHeader);
                log.info("header decrypted successful, {}", microservice);
            } catch (Exception e) {
                log.info("error in the decryption of header, {}", securityHeader);
                throw new RuntimeException(e);
            }
            try {
                if (validConsumer(microservice)) {
                    log.info("client allowed");
                    final var microServiceAuthentication = new UsernamePasswordAuthenticationToken(microservice, null, null);
                    microServiceAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(microServiceAuthentication);
                } else {
                    log.error("client: {}, not allowed", microservice);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    private boolean validConsumer(String consumerEncrypted) throws Exception {

        final var allowed = Arrays.stream(this.consumers).filter(c -> c == consumerEncrypted).toList();
        return allowed.isEmpty();
    }
}
