package com.telstra.codechallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    private int id;
    private String login;
    @JsonProperty("html_url")
    private String htmlUrl;
}
