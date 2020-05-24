package com.telstra.codechallenge.github;

import com.telstra.codechallenge.exception.MicroserviceException;
import com.telstra.codechallenge.model.Repository;
import com.telstra.codechallenge.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

public class GithubServiceTest {

    @InjectMocks
    private GithubService service;

    @Mock
    private Gateway gateway;
    private Repository repository;
    private User user;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        repository = createRepository();
        user = createUser();
    }

    @Test
    public void getHottestRepositoriesSuccess() {
        Map<String, Object> mapObject = new LinkedHashMap<>();
        mapObject.put("items", createRepository());
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(mapObject);
        Mockito.when(gateway.githubServiceCall(ArgumentMatchers.anyString())).thenReturn(mapList);
        List<Repository> actualResult = service.getHottestRepositories(1);
        Assertions.assertEquals(actualResult.size(), mapList.size());
    }

    @Test
    public void getHottestRepositoriesFailed() {
        Map<String, Object> mapObject = new LinkedHashMap<>();
        mapObject.put("items", createRepository());
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(mapObject);
        Mockito.when(gateway.githubServiceCall(ArgumentMatchers.anyString())).thenReturn(mapList);
        Assertions.assertThrows(MicroserviceException.class, () -> {
            service.getHottestRepositories(4);
        });
    }

    @Test
    public void getOldestUserAccountsSuccess() {
        Map<String, Object> mapObject = new LinkedHashMap<>();
        mapObject.put("items", createUser());
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(mapObject);
        Mockito.when(gateway.githubServiceCall(ArgumentMatchers.anyString())).thenReturn(mapList);
        List<User> actualResult = service.getOldestUserAccounts(1);
        Assertions.assertEquals(actualResult.size(), mapList.size());
    }

    @Test
    public void getOldestUserAccountsFailed() {
        Map<String, Object> mapObject = new LinkedHashMap<>();
        mapObject.put("items", createUser());
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(mapObject);
        Mockito.when(gateway.githubServiceCall(ArgumentMatchers.anyString())).thenReturn(mapList);
        Assertions.assertThrows(MicroserviceException.class, () -> {
            service.getOldestUserAccounts(4);
        });
    }

    private Repository createRepository() {
        repository = new Repository();
        repository.setName("Github");
        repository.setDescription("For testing");
        repository.setLanguage("Java");
        repository.setHtmlUrl("http://github.com");
        repository.setWatchersCount(5);
        return repository;
    }

    private User createUser() {
        user = new User();
        user.setLogin("login");
        user.setId(1);
        user.setHtmlUrl("http://localhost:8080");
        return user;
    }
}
