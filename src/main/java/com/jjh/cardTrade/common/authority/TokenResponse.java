package com.jjh.cardTrade.common.authority;

import lombok.Getter;

@Getter
public class TokenResponse {
    private String code;
    private String msg;
    private String token;

    public TokenResponse(String code, String msg, String token) {
        this.code = code;
        this.msg = msg;
        this.token = token;
    }
}
