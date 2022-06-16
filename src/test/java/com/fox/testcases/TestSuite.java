package com.fox.testcases;

import com.fox.assignment.api.GenderCode;
import com.fox.assignment.api.login.Login;
import com.fox.assignment.api.login.LoginResponse;
import com.fox.assignment.api.register.Register;
import com.fox.assignment.api.register.RegisterResponse;
import com.fox.assignment.api.reset.Reset;
import com.fox.assignment.api.reset.ResetResponse;
import com.fox.assignment.core.StringHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestSuite {
    String email, pwd;
    @Test(description = "Register with random email password")
    public void TC1() throws IOException {
        // Actions
        Register registerApi = new Register();
        registerApi.setEmail(email = StringHelper.getRandomEmail());
        registerApi.setPassword(pwd = StringHelper.getRandomAlphanumericInUpperCase(6));
        registerApi.setFirstName(StringHelper.getRandomAlphanumericInUpperCase(4)); // AlphaNumeric
        registerApi.setLastName(StringHelper.getRandomAlphanumericInUpperCase(4)); // AlphaNumeric
        registerApi.setGender(GenderCode.m.name());
        Response response = registerApi.execute();

        //Assertions
        Assert.assertEquals(response.getStatusCode(), 200);
        RegisterResponse pojo = registerApi.getRegisterResponse();
        Assert.assertNotNull(pojo);
        if (pojo != null) {
            Assert.assertNotNull(pojo.getAccessToken());
            Assert.assertNotNull(pojo.getTokenExpiration());
            Assert.assertEquals(pojo.getAccountType(), "identity");
            Assert.assertEquals(pojo.getDevice(), "all");
            Assert.assertEquals(pojo.getDeviceId(), "null");
            Assert.assertEquals(pojo.getBrand(), "fox");
            Assert.assertEquals(pojo.getHasEmail(), true);
            Assert.assertEquals(pojo.getHasSocialLogin(), true);
            Assert.assertEquals(pojo.getIsVerified(), true);
            Assert.assertNotNull(pojo.getIpAddress());
            Assert.assertNotNull(pojo.getPlatform());
            Assert.assertNotNull(pojo.getProfileId());
            Assert.assertEquals(pojo.getUserType(), "email");
            Assert.assertNotNull(pojo.getUserAgent());
            Assert.assertNotNull(pojo.getDma());
            Assert.assertEquals(pojo.getGender(), GenderCode.m.name());
            Assert.assertNotNull(pojo.getViewerId());
            Assert.assertNull(pojo.getBirthdate());
            Assert.assertEquals(pojo.getEmail(), registerApi.getEmail());
            Assert.assertEquals(pojo.getFirstName(), registerApi.getFirstName());
            Assert.assertEquals(pojo.getLastName(), registerApi.getLastName());
        }
    }

    @Test(description = "Login with new credentials with a registered account")
    public void TC2() throws IOException {
        // Actions
        Login loginApi = new Login();
        loginApi.setEmail(email);
        loginApi.setPassword(pwd);
        Response response = loginApi.execute();

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200);
        LoginResponse pojo = loginApi.getLoginResponse();
        Assert.assertNotNull(pojo);
        if (pojo != null) {
            Assert.assertNotNull(pojo.getAccessToken());
            Assert.assertNotNull(pojo.getTokenExpiration());
            Assert.assertEquals(pojo.getAccountType(), "identity");
            Assert.assertEquals(pojo.getDevice(), "all");
            Assert.assertEquals(pojo.getDeviceId(), "null");
            Assert.assertEquals(pojo.getBrand(), "fox");
            Assert.assertEquals(pojo.getHasEmail(), true);
            Assert.assertEquals(pojo.getHasSocialLogin(), true);
            Assert.assertEquals(pojo.getIsVerified(), true);
            Assert.assertNotNull(pojo.getIpAddress());
            Assert.assertNotNull(pojo.getPlatform());
            Assert.assertNotNull(pojo.getProfileId());
            Assert.assertEquals(pojo.getUserType(), "email");
            Assert.assertNotNull(pojo.getUserAgent());
            Assert.assertNotNull(pojo.getDma());
            Assert.assertEquals(pojo.getGender(), GenderCode.m.name());
            Assert.assertNotNull(pojo.getViewerId());
            Assert.assertNull(pojo.getBirthdate());
            Assert.assertEquals(pojo.getEmail(), email);
            Assert.assertNotNull(pojo.getFirstName());
            Assert.assertNotNull(pojo.getLastName());
        }
    }

    @Test(description = "Login with credentials for an unregistered account")
    public void TC3() throws IOException {
            // Actions
            Login loginApi = new Login();
            loginApi.setEmail("abc@xyz.com");
            loginApi.setPassword("123456");
            Response response = loginApi.execute();

            // Assertions
            Assert.assertEquals(response.getStatusCode(), 404);
            LoginResponse pojo = loginApi.getLoginResponse();
            Assert.assertNotNull(pojo);
    }

    @Test(description = "Reset password for a registered account")
    public void TC4() throws IOException {
        // Actions
        Reset resetApi = new Reset();
        resetApi.setEmail(email);
        resetApi.execute();

        //Assertions
        Assert.assertEquals(resetApi.getResponse().getStatusCode(), 200);
        ResetResponse pojo = resetApi.getResetResponse();
        Assert.assertNotNull(pojo);
        if (pojo != null) {
            Assert.assertEquals(pojo.getMessage(), "Reset Email Sent");
            Assert.assertEquals(pojo.getDetail(), "Please check your inbox");
        }
    }

    @Test(description = "Reset password for a registered account within 3 minutes")
    public void TC5() throws IOException {
        // Actions
        Reset resetApi = new Reset();
        resetApi.setEmail(email);
        resetApi.execute();

        //Assertions
        Assert.assertEquals(resetApi.getResponse().getStatusCode(), 400);
        ResetResponse pojo = resetApi.getResetResponse();
        Assert.assertNotNull(pojo);
        if (pojo != null) {
            Assert.assertEquals(pojo.getMessage(), "Bad Request");
            Assert.assertEquals(pojo.getDetail(), "rate limit exceeded for request parameters");
        }
    }

    @Test(description = "Reset password for a registered account post 3 minutes")
    public void TC6() throws IOException, InterruptedException {
        System.out.println("Waiting for 3 minutes...");
        Thread.sleep(181000); // Wait for 3 minutes and 1 seconds
        // Actions
        Reset resetApi = new Reset();
        resetApi.setEmail(email);
        resetApi.execute();

        //Assertions
        Assert.assertEquals(resetApi.getResponse().getStatusCode(), 200);
        ResetResponse pojo = resetApi.getResetResponse();
        Assert.assertNotNull(pojo);
        if (pojo != null) {
            Assert.assertEquals(pojo.getMessage(), "Reset Email Sent");
            Assert.assertEquals(pojo.getDetail(), "Please check your inbox");
        }
    }

    @Test(description = "Register with invalid email")
    public void TC7() throws IOException {
        // Actions
        Register registerApi = new Register();
        registerApi.setEmail("123@123");
        registerApi.setPassword(StringHelper.getRandomAlphanumericInUpperCase(6));
        registerApi.setFirstName(StringHelper.getRandomAlphaInUpperCase(4));
        registerApi.setLastName(StringHelper.getRandomAlphaInUpperCase(4));
        registerApi.setGender(GenderCode.m.name());
        Response response = registerApi.execute();

        //Assertions
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test(description = "Register with password length less than 6 characters")
    public void TC8() throws IOException {
        // Actions
        Register registerApi = new Register();
        registerApi.setEmail(StringHelper.getRandomEmail());
        registerApi.setPassword(StringHelper.getRandomAlphanumericInUpperCase(5));
        registerApi.setFirstName(StringHelper.getRandomAlphaInUpperCase(4));
        registerApi.setLastName(StringHelper.getRandomAlphaInUpperCase(4));
        registerApi.setGender(GenderCode.m.name());
        Response response = registerApi.execute();

        //Assertions
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test(description = "Register with invalid gender")
    public void TC9() throws IOException {
        // Actions
        Register registerApi = new Register();
        registerApi.setEmail(StringHelper.getRandomEmail());
        registerApi.setPassword(StringHelper.getRandomAlphanumericInUpperCase(6));
        registerApi.setFirstName(StringHelper.getRandomAlphaInUpperCase(4));
        registerApi.setLastName(StringHelper.getRandomAlphaInUpperCase(4));
        registerApi.setGender("x"); // Giving invalid gender
        Response response = registerApi.execute();

        //Assertions
        Assert.assertEquals(response.getStatusCode(), 400);
    }

}
