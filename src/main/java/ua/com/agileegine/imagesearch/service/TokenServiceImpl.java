package ua.com.agileegine.imagesearch.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.com.agileegine.imagesearch.dto.AuthRequestDto;
import ua.com.agileegine.imagesearch.dto.AuthResponseDto;
import ua.com.agileegine.imagesearch.exception.AuthorizeException;
import ua.com.agileegine.imagesearch.service.interfaces.TokenService;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${interview.agileengine.base-url}")
    private String baseUrl;

    @Value("${interview.agileengine.api-key}")
    private String apiKey;

    private static String token;
    private final RestTemplate restTemplate;

    public TokenServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRateString = "${interview.agileengine.token.fixed-rate}")
    @PostConstruct
    public void init() {
        requestToken(restTemplate, baseUrl, apiKey);
    }

    @Override
    public String getToken() {
        return token;
    }

    private void requestToken(RestTemplate restTemplate, String baseUrl, String apiKey) {
        HttpEntity<AuthRequestDto> request = new HttpEntity<>(new AuthRequestDto(apiKey));
        AuthResponseDto response = restTemplate.postForObject(baseUrl + "/auth", request, AuthResponseDto.class);
        if (response == null) {
            log.error("Cannot get token");
            throw new AuthorizeException("Token is null");
        }
        log.info("GOT TOKEN >>>: {}", response.getToken());
        token = response.getToken();
    }
}
