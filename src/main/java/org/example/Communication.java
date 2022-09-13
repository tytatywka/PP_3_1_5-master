package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Communication {

    private final String URl = "http://94.198.50.185:7081/api/users";

    @Autowired
    private RestTemplate restTemplate;

    public List<String> getCookie(){
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(URl, List.class);
        System.out.println("List of Users: "+responseEntity.getBody());
        List<String> cookie= responseEntity.getHeaders().get("Set-Cookie");
        return cookie;
    }

    public String saveUser(User user, List<String> cookies) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie",cookies.stream().collect(Collectors.joining()));
        HttpEntity<User> entity = new HttpEntity<User>(user,headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URl,HttpMethod.POST,entity, String.class);
        return responseEntity.getBody();
    }
    public String editUser(User user, List<String> cookies) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie",cookies.stream().collect(Collectors.joining()));
        HttpEntity<User> entity = new HttpEntity<User>(user,headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URl,HttpMethod.PUT,entity, String.class);
        return responseEntity.getBody();
    }

    public String deleteUser(int id, List<String> cookies) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie",cookies.stream().collect(Collectors.joining()));
        HttpEntity<User> entity = new HttpEntity<User>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URl +"/"+ id,HttpMethod.DELETE,entity, String.class);
        return responseEntity.getBody();
    }
}

