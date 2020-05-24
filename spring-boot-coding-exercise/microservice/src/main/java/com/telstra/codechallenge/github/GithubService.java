package com.telstra.codechallenge.github;

import com.telstra.codechallenge.model.Repository;
import com.telstra.codechallenge.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GithubService {

    private static final Logger log = LoggerFactory.getLogger(GithubService.class);

    @Autowired
    private Gateway gateway;

    public List<Repository> getHottestRepositories(int noOfRepositories) {
        log.info("getHottestRepositories() called");
        return parseHottestRepositories(gateway.githubServiceCall("desc"), noOfRepositories);
    }

    public List<User> getOldestUserAccounts(int noOfRepositories) {
        //assumption oldest user means less stars
        log.info("getOldestUserAccounts() called");
        return parseOldestUserAccounts(gateway.githubServiceCall("asc"), noOfRepositories);
    }

    private List<Repository> parseHottestRepositories(List<Map<String, Object>> jsonObjects, int noOfRepositories) {
        Validator.inputValidation(jsonObjects, noOfRepositories);
        List<Map<String, Object>> requiredJSONObjects = jsonObjects.subList(0, noOfRepositories);
        List<Repository> repositoryList = new ArrayList<>();
        Repository repository;
        for (Map<String, Object> jsonObject : requiredJSONObjects) {
            repository = new Repository();
            repository.setHtmlUrl((String) jsonObject.get("html_url"));
            if (jsonObject.get("watchers_count") != null)
                repository.setWatchersCount((Integer) jsonObject.get("watchers_count"));
            repository.setLanguage((String) jsonObject.get("language"));
            repository.setDescription((String) jsonObject.get("description"));
            repository.setName((String) jsonObject.get("name"));
            repositoryList.add(repository);
        }
        return repositoryList;
    }

    private List<User> parseOldestUserAccounts(List<Map<String, Object>> jsonObjects, int noOfRepositories) {
        Validator.inputValidation(jsonObjects, noOfRepositories);
        List<Map<String, Object>> requiredJSONObjects = jsonObjects.subList(0, noOfRepositories);
        List<User> userList = new ArrayList<>();
        User user;
        for (Map<String, Object> jsonObject : requiredJSONObjects) {
            user = new User();
            user.setHtmlUrl((String) jsonObject.get("html_url"));
            if(jsonObject.get("id") != null)
                user.setId((int) jsonObject.get("id"));
            user.setLogin((String) jsonObject.get("login"));
            userList.add(user);
        }
        return userList;
    }
}
