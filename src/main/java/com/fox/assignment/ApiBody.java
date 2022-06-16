package com.fox.assignment;

public enum ApiBody {
    REGISTER("src/main/resources/ApiBody/Register.json"),
    LOGIN("src/main/resources/ApiBody/Login.json"),
    RESET("src/main/resources/ApiBody/Reset.json")
    ;

    public String path;
    ApiBody(String path){
        this.path=path;
    }

    public String getPath(){
        return this.path;
    }
}
