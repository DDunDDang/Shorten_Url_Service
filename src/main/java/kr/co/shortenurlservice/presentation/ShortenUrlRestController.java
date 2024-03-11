package kr.co.shortenurlservice.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShortenUrlRestController {

    @RequestMapping(value = "/shorten-url", method = RequestMethod.POST)
    public ResponseEntity<?> createShortenUrl() {
        return ResponseEntity.ok().body(null);
    }

    @RequestMapping(value = "/{shorten-url-key}", method = RequestMethod.GET)
    public ResponseEntity<?> redirectShortenUrl() {
        return ResponseEntity.ok().body(null);
    }

    @RequestMapping(value = "/shorten-url/{shorten-url-key}", method = RequestMethod.GET)
    public ResponseEntity<?> getShortenUrlInformation() {
        return ResponseEntity.ok().body(null);
    }
}
