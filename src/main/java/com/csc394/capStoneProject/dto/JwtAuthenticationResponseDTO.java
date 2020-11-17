package com.csc394.capStoneProject.dto;

import java.io.Serializable;

public class JwtAuthenticationResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

}
