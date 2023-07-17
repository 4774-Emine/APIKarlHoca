package pojo.restfullbooker;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTokenResponse {

    @JsonProperty("token")
    private String token;

    public String getToken() {
        return token;
    }


    @Override
    public String toString() {
        return "CreateTokenResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}