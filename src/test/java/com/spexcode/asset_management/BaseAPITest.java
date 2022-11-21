package com.spexcode.asset_management;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.spexcode.asset_management.api.security.jwt.JwtUtil;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = AssetManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseAPITest {
    @Autowired
    protected TestRestTemplate rest;

    @Autowired
    protected UserDetailsService userDetailsService;

    private String jwtToken = "";

    HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);
        return headers;
    }

    @BeforeAll
    public void setupTest() {
        // Le usuário
        UserDetails user = userDetailsService.loadUserByUsername("api_admin");
        assertNotNull(user);

        // Gera token
        jwtToken = JwtUtil.createToken(user);
        System.out.println(jwtToken);
        assertNotNull(jwtToken);
    }

    <T> ResponseEntity<T> post(String url, Object body, Class<T> responseType) {
        HttpHeaders headers = getHeaders();

        return rest.exchange(url, POST, new HttpEntity<>(body, headers), responseType);
    }

    <T> ResponseEntity<T> getList(String url, ParameterizedTypeReference responseType) {

        HttpHeaders headers = getHeaders();
        return rest.exchange(url, GET, new HttpEntity<>(headers), responseType);
    }

    <T> ResponseEntity<T> getItem(String url, Class<T> responseType) {

        HttpHeaders headers = getHeaders();
        return rest.getForEntity(url, responseType, new HttpEntity<>(headers));
    }
    

    <T> ResponseEntity<T> delete(String url, Class<T> responseType) {

        HttpHeaders headers = getHeaders();

        return rest.exchange(url, DELETE, new HttpEntity<>(headers), responseType);
    }
}