package com.telstra.codechallenge.github;

import com.telstra.codechallenge.exception.MicroserviceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class Validator {

    private static final Logger log = LoggerFactory.getLogger(Validator.class);

    public static void inputValidation(List<Map<String, Object>> jsonObjects, int noOfRepositories) {
        if (noOfRepositories <= 0 || jsonObjects.isEmpty() || noOfRepositories > jsonObjects.size()) {
            log.error("number of repositories is invalid");
            throw new MicroserviceException(HttpStatus.BAD_REQUEST.value(), "number of repositories is invalid");
        }
    }
}
