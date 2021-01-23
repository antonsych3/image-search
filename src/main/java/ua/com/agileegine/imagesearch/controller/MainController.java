package ua.com.agileegine.imagesearch.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ua.com.agileegine.imagesearch.service.TokenServiceImpl;

@Slf4j
@RestController
public class MainController {

    //todo
    private final RestTemplate restTemplate;
    private final TokenServiceImpl tokenServiceImpl;

    public MainController(RestTemplate restTemplate, TokenServiceImpl tokenServiceImpl) {
        this.restTemplate = restTemplate;
        this.tokenServiceImpl = tokenServiceImpl;
    }

    @ResponseBody
    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<String> index(@PathVariable String searchTerm) {

        return new ResponseEntity<>(searchTerm, HttpStatus.OK);
    }

}
