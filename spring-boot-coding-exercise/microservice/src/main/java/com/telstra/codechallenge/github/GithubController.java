package com.telstra.codechallenge.github;

import com.telstra.codechallenge.exception.MicroserviceException;
import com.telstra.codechallenge.model.Repository;
import com.telstra.codechallenge.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/microservices")
public class GithubController {

    @Autowired
    private GithubService service;

    @ExceptionHandler(MicroserviceException.class)
    void handleProductException(MicroserviceException prductException, HttpServletResponse response) throws IOException {
        response.sendError(prductException.getStatus(), prductException.getMessage());
    }

    @GetMapping("/repositories/{records}")
    private ResponseEntity<List<Repository>> getHottestRepositories(@PathVariable("records") int records) {
        return new ResponseEntity<>(service.getHottestRepositories(records), HttpStatus.OK);
    }

    @GetMapping("/accounts/{records}")
    private ResponseEntity<List<User>> getOldestUserAccounts(@PathVariable("records") int records) {
        return new ResponseEntity<>(service.getOldestUserAccounts(records), HttpStatus.OK);
    }
}
