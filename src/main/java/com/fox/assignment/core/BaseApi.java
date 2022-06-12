package com.fox.assignment.core;

import com.fox.assignment.Config;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public interface BaseApi {
    static final Logger log = LogManager.getLogger(BaseApi.class);

    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    MethodType method = null;
    Object body = null;
    ContentType contentType=null;
    String baseUri=null;
    Map<String, Object> queryParams = new HashMap<String, Object>();
    Map<String, Object> formURLEncoded = new HashMap<String, Object>();
    Map<String, Object> params = new HashMap<String, Object>();
    String basePath=null;
    String cookie=null;
    Map<String, Object> headers = new HashMap<String, Object>();
    Response response=null;
    String authName=null;
    String authPassword=null;

    enum MethodType {
        POST, GET, PUT, DELETE, PATCH
    }

    /**
     * Sends the constructed api.
     * @return instance of Response object.
     */
    default Response execute() {
        RequestSpecification requestSpecification = requestSpecBuilder.addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter()).build();
        Response response;
        if (this.authName != null && this.authPassword != null) {
            PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
            basicAuth.setUserName(this.authName);
            basicAuth.setPassword(this.authPassword);
            requestSpecBuilder.setAuth(basicAuth);
        }
        EncoderConfig ec = new EncoderConfig();
        ec.appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.defaultParser = Parser.JSON;
        RestAssuredConfig config = getConfig();

        switch (method) {
            case GET:
                response = given().config(config).spec(requestSpecification).when().get();
                break;
            case POST:
                response = given().config(config).spec(requestSpecification).when().post();
                break;
            case PUT:
                response = given().config(config).spec(requestSpecification).when().put();
                break;
            case DELETE:
                response = given().config(config).spec(requestSpecification).when().delete();
                break;
            case PATCH:
                response = given().config(config).spec(requestSpecification).when().patch();
                break;
            default:
                throw new RuntimeException("API method not specified");
        }
        response = response;
        return response;
    }

    default Response getResponse(){
        return this.response;
    }

    default RestAssuredConfig getConfig(){
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(Integer.parseInt(Config.HTTP_SOCKET_TIMEOUT))
                .setConnectionRequestTimeout(Integer.parseInt(Config.HTTP_SOCKET_TIMEOUT))
                .setSocketTimeout(Integer.parseInt(Config.HTTP_SOCKET_TIMEOUT))
                .build();

        HttpClientConfig httpClientFactory = HttpClientConfig.httpClientConfig()
                .httpClientFactory(() -> HttpClientBuilder.create()
                        .setDefaultRequestConfig(requestConfig)
                        .build());

        RestAssuredConfig config = RestAssured
                .config()
                .httpClient(httpClientFactory);
        config.encoderConfig(new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(true));
        return config;
    }

}
