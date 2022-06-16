package com.fox.assignment.api.login;

public class LoginResponse {
    private String accessToken;
    private Long tokenExpiration;
    private String accountType;
    private String device;
    private Object deviceId;
    private String brand;
    private Boolean hasEmail;
    private Boolean hasSocialLogin;
    private Boolean isVerified;
    private String ipAddress;
    private String platform;
    private String profileId;
    private String userType;
    private String userAgent;
    private String dma;
    private String gender;
    private String viewerId;
    private Object birthdate;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean newsLetter;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(Long tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Object getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Object deviceId) {
        this.deviceId = deviceId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getHasEmail() {
        return hasEmail;
    }

    public void setHasEmail(Boolean hasEmail) {
        this.hasEmail = hasEmail;
    }

    public Boolean getHasSocialLogin() {
        return hasSocialLogin;
    }

    public void setHasSocialLogin(Boolean hasSocialLogin) {this.hasSocialLogin = hasSocialLogin;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getDma() {
        return dma;
    }

    public void setDma(String dma) {
        this.dma = dma;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getViewerId() {
        return viewerId;
    }

    public void setViewerId(String viewerId) {
        this.viewerId = viewerId;
    }

    public Object getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Object birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getNewsLetter() {
        return newsLetter;
    }

    public void setNewsLetter(Boolean newsLetter) {
        this.newsLetter = newsLetter;
    }
}