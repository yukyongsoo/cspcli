package com.yuk.cspcli;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@Profile("default")
public class DiscardLoginComponent implements LoginComponent {
    private RestTemplate restTemplate;
    private String token = "";

    DiscardLoginComponent(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @NotNull
    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void login(@NotNull String ip, int port, @NotNull String id, @NotNull String password) {
        URI uri = URI.create(ip + ":" + port);
        Login login = new Login(id, password);
        token = restTemplate.postForObject(uri,login,String.class);
    }


}
