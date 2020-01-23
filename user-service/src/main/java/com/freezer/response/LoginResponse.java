/**
 * @FileName: LoginResponse
 * @Author: zzc
 * @Date: 2019年12月28日 14:23:47
 * @Version V1.0.0
 */

package com.freezer.response;

import freezer.response.Response;

public class LoginResponse extends Response {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
