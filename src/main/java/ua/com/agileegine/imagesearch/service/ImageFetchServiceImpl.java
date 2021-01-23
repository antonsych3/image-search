package ua.com.agileegine.imagesearch.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.agileegine.imagesearch.entity.Page;
import ua.com.agileegine.imagesearch.entity.Picture;
import ua.com.agileegine.imagesearch.exception.AuthorizeException;
import ua.com.agileegine.imagesearch.service.interfaces.ImageFetchService;
import ua.com.agileegine.imagesearch.service.interfaces.TokenService;

@Slf4j
@Service
public class ImageFetchServiceImpl implements ImageFetchService {

    @Value("${interview.agileengine.base-url}")
    private String baseUrl;

    private static final String IMAGES_URL = "/images";
    private static final String SPECIFIC_IMAGES_URL = "/images/page={}";
    private static final String PARAMETER_NAME = "page";

    private final RestTemplate restTemplate;
    private final TokenService tokenService;

    public ImageFetchServiceImpl(RestTemplate restTemplate, TokenService tokenService
    ) {
        this.restTemplate = restTemplate;
        this.tokenService = tokenService;
    }

    public Page getPageInformation(int page) {
        try {
            HttpEntity<String> entity = formHttpEntity();
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + IMAGES_URL)
                    .queryParam(PARAMETER_NAME, page);
            ResponseEntity<Page> result = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    Page.class);
            Page resultBody = result.getBody();
            log.info("GOT RESPONSE FOR IMAGES FROM PAGE {} >>>:\n{}", page, resultBody);
            return resultBody;
        } catch (Exception e) {
            log.error("Cannot get information from page {}", page);
            throw new AuthorizeException(e.getMessage());
        }
    }

    @Override
    public Picture getPictureInformation(String id) {
        HttpEntity<String> entity = formHttpEntity();
        Picture resultPicture = restTemplate
                .getForObject(baseUrl + SPECIFIC_IMAGES_URL, Picture.class, HttpMethod.GET, entity, id);
        log.info("GOT DETAILS FOR PICTURE {}", id);
        return resultPicture;
    }

    private HttpEntity<String> formHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenService.getToken());
        return new HttpEntity<>(baseUrl, headers);
    }
}
