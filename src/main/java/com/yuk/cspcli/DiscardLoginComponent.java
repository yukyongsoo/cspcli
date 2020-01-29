package com.yuk.cspcli;

import com.yuk.cspcli.api.HttpComponent;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.HashMap;

@Component
@Profile("default")
public class DiscardLoginComponent implements LoginComponent {
    private final HttpComponent httpComponent;
    private String token = "";

    DiscardLoginComponent(HttpComponent httpComponent) {
        this.httpComponent = httpComponent;
    }

    @NotNull
    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void login(@NotNull String ip, int port, @NotNull String id, @NotNull String password) {
        Login login = new Login(id, password);
        token = httpComponent.postDataSendingAndGet("/login",login,String.class, new HashMap<>());
    }
}
