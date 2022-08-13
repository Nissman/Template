package org.example;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        String getU="http://94.198.50.185:7081/api/users";
         String getPo="http://94.198.50.185:7081/api/users";
         String getPu="http://94.198.50.185:7081/api/users";
     String getD="http://94.198.50.185:7081/api/users/";
        URI e = new URI("http://94.198.50.185:7081/api/users");
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<?> request = new RequestEntity<>(HttpMethod.GET, e);
      //ResponseEntity<List<User>> entity =  restTemplate.exchange(request, new ParameterizedTypeReference<>() {});
        HttpHeaders headers = new HttpHeaders();
       //headers.add("Cookie", "credentials=";
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
       // System.out.println(entity.getHeaders().get("Set-Cookie").get(0));
        System.out.println(  restTemplate.exchange(getU, HttpMethod.GET, entity, String.class).getHeaders().get("Set-Cookie"));

        headers.add("Cookie", restTemplate.exchange(getU, HttpMethod.GET, entity, String.class).getHeaders().get("Set-Cookie").get(0));
        entity = new HttpEntity<String>(headers);
        User user = new User(3L, "James", "Brown", (byte)1);
        HttpEntity<User> requestEntity =
                new HttpEntity<>(user, headers);

        System.out.println(  restTemplate.exchange(getPo, HttpMethod.POST, requestEntity, String.class).getBody());
       user.setName("Thomas");
       user.setLastName("Shelby");
        requestEntity =
                new HttpEntity<>(user, headers);
        System.out.println(  restTemplate.exchange(getPu, HttpMethod.PUT, requestEntity, String.class).getBody());
        Long id = user.getId();
        System.out.println(  restTemplate.exchange(getD + user.getId(), HttpMethod.DELETE, entity, String.class).getBody());
    }
}