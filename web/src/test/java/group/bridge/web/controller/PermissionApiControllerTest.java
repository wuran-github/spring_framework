package group.bridge.web.controller;

import group.bridge.web.WebApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PermissionApiControllerTest {
    private URL base;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Before
    public void setUp() throws MalformedURLException {
        String url = "http://localhost:"+port+"/api/permission/";
        this.base = new URL(url);
//        System.out.println(ShiroUtil.parseHash("admin","admin"));
    }
    @Test
    public void permissionAdd(){
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                base.toString()+"/add",null,String.class);
        System.out.println(responseEntity.getBody());
    }
}