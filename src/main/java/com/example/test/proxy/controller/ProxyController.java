package com.example.test.proxy.controller;

import java.util.Collections;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1")
@RestController
public class ProxyController {

    @GetMapping("/proxy500")
    public ResponseEntity<?> proxy500(ProxyExchange<byte[]> proxy) {
       MultiValueMap<String, String> multiValueMap = new HttpHeaders();
       multiValueMap.put("Transfer-Encoding", Collections.singletonList("chunked"));
        multiValueMap.put("Connection", Collections.singletonList("close"));
        return new ResponseEntity<>("{", multiValueMap, HttpStatus.INTERNAL_SERVER_ERROR);

    }
  /*  @GetMapping("/proxy500")
    public ResponseEntity<?> proxy500(ProxyExchange<byte[]> proxy) {
        ResponseEntity<?>entity = proxy.uri("http://localhost:8081/api/v1/error500").get();
        //  return entity;
        MultiValueMap<String, String> multiValueMap = new HttpHeaders();
        for (Entry<String, List<String>> stringListEntry : entity.getHeaders().entrySet()) {
            // just remove connection
          // if ("connection".equalsIgnoreCase(stringListEntry.getKey())
            //    && "close".equalsIgnoreCase(stringListEntry.getValue().get(0))) {
                multiValueMap.put(stringListEntry.getKey(), stringListEntry.getValue());
            //}
        }
        return new ResponseEntity<>(entity.getBody(), multiValueMap, entity.getStatusCode());

    }*/
}
