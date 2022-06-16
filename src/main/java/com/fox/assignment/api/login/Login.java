package com.fox.assignment.api.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fox.assignment.ApiBody;
import com.fox.assignment.core.BaseApi;

import java.io.IOException;

public class Login extends BaseApi {
    public Login(){
        basePath="/login";
        setBodyFromJsonFile(ApiBody.LOGIN.getPath());
    }

    /**
     * Get POJO response
     * @return
     * @throws JsonProcessingException
     */
    public LoginResponse getLoginResponse() throws IOException {
        return getResponse(LoginResponse.class);
    }

    public void setEmail(String email) {
        updateRequestBody("$.email", email);
    }

    public void setPassword(String password) {
        updateRequestBody("$.password", password);
    }

}
