package com.fox.assignment.api.reset;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fox.assignment.ApiBody;
import com.fox.assignment.core.BaseApi;

import java.io.IOException;

public class Reset extends BaseApi {
    ResetResponse resetResponse=null;
    public Reset(){
        basePath="/reset";
        setBodyFromJsonFile(ApiBody.RESET.getPath());
    }

    /**
     * Get POJO response
     * @return
     * @throws JsonProcessingException
     */
    public ResetResponse getResetResponse() throws IOException {
        if(resetResponse==null)
            try {
                resetResponse = getResponse(ResetResponse.class);
            } catch (JsonProcessingException ignore){}
        return resetResponse;
    }

    /**
     * Get POJO response
     * @return
     * @throws JsonProcessingException
     */
    public ResetResponse_400 getResetResponse_400() throws IOException {
            try {
                return getResponse(ResetResponse_400.class);
            } catch (JsonProcessingException ignore){return null;}
    }

    public void setEmail(String email) {
        updateRequestBody("$.email", email);
    }

}
