package com.fox.assignment.api.register;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fox.assignment.ApiBody;
import com.fox.assignment.core.BaseApi;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;

public class Register extends BaseApi {
    RegisterResponse registerResponse = null;

    /**
     * Creates an API object for Register, with default values.
     */
    public Register() {
        basePath = "/register";
        setBodyFromJsonFile(ApiBody.REGISTER.getPath());
    }

    /**
     * Get POJO response
     *
     * @return
     * @throws JsonProcessingException
     */
    public RegisterResponse getRegisterResponse() throws IOException {
        if (registerResponse == null)
            try {
                registerResponse = getResponse(RegisterResponse.class);
            } catch (JsonProcessingException ignore) {
            }
        return registerResponse;
    }

    public void setEmail(String email) {
        updateRequestBody("$.email", email);
    }

    public void setPassword(String password) {
        updateRequestBody("$.password", password);
    }

    public void setGender(String gender) {
        updateRequestBody("$.gender", gender);
    }

    public void setFirstName(String firstName) {
        updateRequestBody("$.firstName", firstName);
    }

    public void setLastName(String lastName) {
        updateRequestBody("$.lastName", lastName);
    }

    public String getEmail() {
        return JsonPath.parse(body).read("$.email");
    }

    public String getPassword() {
        return JsonPath.parse(body).read("$.password");
    }

    public String getGender() {
        return JsonPath.parse(body).read("$.gender");
    }

    public String getFirstName() {
        return JsonPath.parse(body).read("$.firstName");
    }

    public String getLastName() {
        return JsonPath.parse(body).read("$.lastName");
    }
}
