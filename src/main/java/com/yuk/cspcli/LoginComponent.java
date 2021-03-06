package com.yuk.cspcli;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public interface LoginComponent {
    @NotNull
    String getToken();

    void login(@NotNull String ip, int port, @NotNull String id, @NotNull String password) throws IOException;
}
