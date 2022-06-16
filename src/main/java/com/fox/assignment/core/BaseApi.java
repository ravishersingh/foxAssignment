package com.fox.assignment.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseApi {
    static final Logger log = LogManager.getLogger(BaseApi.class);

    RequestSpecification spec = RestAssured.given();
    protected MethodType method = null;
    protected String body = null;
    protected Class RequestPojo = null;
    protected ContentType contentType= ContentType.JSON;;
    protected String baseUri="https://api3.fox.com/v2.0";
    protected String basePath=null;
    protected Map<String, Object> headers = new HashMap<String, Object>();
    protected Response response=null;

    {// Setting default values
        method=MethodType.POST;
        headers.put("x-api-key", "DEFAULT");
    }

    protected void setBodyFromJsonFile(String jsonFilePath){
        try {
            body = new String(Files.readAllBytes(Paths.get(jsonFilePath)), StandardCharsets.UTF_8);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    protected enum MethodType {
        POST, GET, PUT, DELETE, PATCH
    }

    /**
     * Sends the constructed api.
     * @return instance of Response object.
     */
    public Response execute() {
        build(); // Build Request
        spec.log().all(); // Print Request
        switch (method) {
            case GET:
                response = given().spec(spec).when().get();
                break;
            case POST:
                response = given().spec(spec).when().post();
                break;
            default:
                throw new RuntimeException("API method not specified in framework");
        }
        response = response;
        System.out.println("Response:");
        response.prettyPrint(); // Print Response
        return response;
    }

    /**
     * Get Rest Assured Response after triggering the API
     * @return
     */
    public Response getResponse(){
        return this.response;
    }

    /**
     * Converts Response Json body to POJO
     * @param clazz
     * @param <T>
     * @return
     * @throws JsonProcessingException
     */
    protected <T> T getResponse(Class<T> clazz) throws IOException {
        return Jackson.json2pojo(response.getBody().asPrettyString(), clazz);
    }

    /**
     * Build the Request for execution.
     */
    private void build(){
        spec.baseUri(baseUri);
        spec.basePath(basePath);
        spec.contentType(contentType);
        spec.headers(headers);
        spec.body(body);
    }

    /**
     * Update Json Request parameters.
     * @param path JAQL query
     * @param value
     */
    protected void updateRequestBody(String path, String value){
        body = com.jayway.jsonpath.JsonPath.parse(body).set(path, value).jsonString();
    }

}
