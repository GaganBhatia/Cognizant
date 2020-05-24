package com.telstra.codechallenge.github;

import com.telstra.codechallenge.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class Gateway {

    @Value("${github.url}")
    private String githubUrl;

    @Autowired
    private RestTemplate restTemplate;

    //@HystrixCommand(fallbackMethod = "fallbackMethodHottestRepositories")
    public List<Map<String, Object>> githubServiceCall(String order) {
        StringBuilder url = new StringBuilder("").append(githubUrl).append("&order=").append(order);
        Map<String, Object> result = restTemplate.getForObject(url.toString(), Map.class);
        return (List<Map<String, Object>>) result.get("items");
    }

    public List<Repository> fallbackMethodHottestRepositories(String q, String sort, String order) {
        return null;
    }

}
